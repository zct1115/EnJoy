package com.ckkj.enjoy.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.ckkj.enjoy.app.AppApplication;
import com.ckkj.enjoy.app.AppContent;
import com.ckkj.enjoy.bean.Song;
import com.ckkj.enjoy.bean.SongDetailInfo;
import com.ckkj.enjoy.bean.SongUpdateInfo;
import com.ckkj.enjoy.bean.UpdateViewPagerBean;
import com.ckkj.enjoy.client.NetworkUtil;
import com.ckkj.enjoy.database.DaoManager;
import com.ckkj.enjoy.database.music.MusicUtils;
import com.ckkj.enjoy.ui.music.LastPlayMusicActivity;
import com.ckkj.enjoy.ui.music.PlayingActivity;
import com.enjoy.entity.LastMusic;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;

/**
 * Created by zct11 on 2017/9/27.
 */

public class MediaPlayService extends Service {

    //音乐列表 获取的是音乐详情 还需要进行网络请求 获取在线播放的url
    private List<SongDetailInfo> musiclist=new ArrayList<>();

    private List<LastMusic> lastMusics=new ArrayList<>();


    //播放音乐对象
    private SongDetailInfo songDetailInfo;
    //MediaPlayer
    private MediaPlayer mediaPlayer;
    //播放的时间
    private int currentTime=0;
    //播放的数量
    private int num=0;
    //播放歌曲在列表中的索引
    private int position = 0;
    //当前歌曲的时长
    private int duration = 0;
    //AudioManager类位于android.Media 包中，该类提供访问控制音量和钤声模式的操作
    private AudioManager audioManager;
    //播放全部
    private boolean isPlayAll=false;
    //线程判断
    private boolean flag = true;
    //创建单线程池
    private ExecutorService es = Executors.newSingleThreadExecutor();
    //播放状态 默认是列表循环
    private int play_mode = AppContent.PLAYING_MODE_REPEAT_ALL;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MediaBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=getMediaPlayer(AppApplication.getAppContext());
        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);

    }

    private MediaPlayer getMediaPlayer(Context context) {
        MediaPlayer mediaplayer=new MediaPlayer();
        /*android sdk*/
        if (Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.KITKAT){
            return mediaplayer;
        }
        try {
            Class<?> cMediaTimeProvider = Class.forName("android.media.MediaTimeProvider");
            Class<?> cSubtitleController = Class.forName("android.media.SubtitleController");
            Class<?> iSubtitleControllerAnchor = Class.forName("android.media.SubtitleController$Anchor");
            Class<?> iSubtitleControllerListener = Class.forName("android.media.SubtitleController$Listener");

            Constructor constructor = cSubtitleController.getConstructor(new Class[]{Context.class, cMediaTimeProvider, iSubtitleControllerListener});

            Object subtitleInstance = constructor.newInstance(context, null, null);

            Field f = cSubtitleController.getDeclaredField("mHandler");

            f.setAccessible(true);
            try {
                f.set(subtitleInstance, new Handler());
            } catch (IllegalAccessException e) {
                return mediaplayer;
            } finally {
                f.setAccessible(false);
            }

            Method setsubtitleanchor = mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor);

            setsubtitleanchor.invoke(mediaplayer, subtitleInstance, null);
            //Log.e("", "subtitle is setted :p");
        } catch (Exception e) {
        }

        return mediaplayer;
    }

    public class MediaBinder extends Binder {
        public MediaPlayService getMediaPlayService(){
            return MediaPlayService.this;
        }
    }

    //创建线程 动态更新歌曲播放的进度
    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                int position = mediaPlayer.getCurrentPosition();
                //发送广播 通知PlayActivity界面更新UI
                Intent intent = new Intent();
                intent.putExtra("max",duration);
                intent.putExtra("progress", position);
                intent.setAction("com.lvr.progress");
                sendBroadcast(intent);
                SystemClock.sleep(500);
            }
        }
    };

    /**
     * 播放
     */
    public void playSong(int position, boolean isLocal) {
        this.position = position;
        num++;
        SongDetailInfo info = musiclist.get(position);
        if (songDetailInfo == null || !info.getSonginfo().getSong_id().equals(songDetailInfo.getSonginfo().getSong_id())) {
            //不是同一首歌
            if (songDetailInfo != null) {
                songDetailInfo.setOnClick(false);
            }
            songDetailInfo = info;
        }

        if (!songDetailInfo.isOnClick()) {
            songDetailInfo.setOnClick(true);
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            play(info.getBitrate().getFile_link(), isLocal);

        } else {
            startPlayingActivity(songDetailInfo);

        }
    }

    public void playlastSong(int position, boolean isLocal) {
        this.position = position;
        MusicUtils utils=new MusicUtils(AppApplication.getAppContext());
        lastMusics=utils.getlist();
        LastMusic info = lastMusics.get(position);
        if(info!=null){
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            playlast(info.getFile_link(), isLocal);
            startlastPlayingActivity(info);

        }

    }

    private void startlastPlayingActivity(LastMusic lastMusic) {
        Intent intent = new Intent(MediaPlayService.this, PlayingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //时长
        int duration = 0;
        //当前位置
        int currentTime = 0;
        //歌曲名称
        String title = lastMusic.getTitle();
        //演唱者
        String author = lastMusic.getAuthor();
        //封面
        String picUrl = lastMusic.getPic_premium();
        boolean playing = isPlaying();
        intent.putExtra("isPlaying",playing);
        intent.putExtra("duration", duration);
        intent.putExtra("position",position);
        intent.putExtra("curPostion", currentTime);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("picUrl", picUrl);
        startActivity(intent);
    }

    /**
     * 开启PlayingActivity
     *
     * @param info 对应的音乐信息
     */
    private void startPlayingActivity(SongDetailInfo info) {

        Intent intent = new Intent(MediaPlayService.this, PlayingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //时长
        int duration = 0;
        //当前位置
        int currentTime = 0;
        //歌曲名称
        String title = info.getSonginfo().getTitle();
        //演唱者
        String author = info.getSonginfo().getAuthor();
        //封面
        String picUrl = info.getSonginfo().getPic_premium();
        boolean playing = isPlaying();
        intent.putExtra("isPlaying",playing);
        intent.putExtra("duration", duration);
        intent.putExtra("position",position);
        intent.putExtra("curPostion", currentTime);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("picUrl", picUrl);
        MusicUtils musicUtils=new MusicUtils(AppApplication.getAppContext());
        LastMusic lastMusic=new LastMusic();
        lastMusic.setAlbum_id(info.getSonginfo().getAlbum_id());
        lastMusic.setAlbum_no(info.getSonginfo().getAlbum_no());
        lastMusic.setAlbum_title(info.getSonginfo().getAlbum_title());
        lastMusic.setAll_artist_id(info.getSonginfo().getAll_artist_id());
        lastMusic.setAll_artist_ting_uid(info.getSonginfo().getAll_artist_ting_uid());
        lastMusic.setAll_rate(info.getSonginfo().getAll_rate());
        lastMusic.setArtist_id(info.getSonginfo().getArtist_id());
        lastMusic.setAuthor(info.getSonginfo().getAuthor());
        lastMusic.setBitrate_fee(info.getSonginfo().getBitrate_fee());
        lastMusic.setCharge(info.getSonginfo().getCharge());
        lastMusic.setCopy_type(info.getSonginfo().getCopy_type());
        lastMusic.setHas_mv(info.getSonginfo().getHas_mv());
        lastMusic.setHas_mv_mobile(info.getSonginfo().getHas_mv_mobile());
        lastMusic.setHavehigh(info.getSonginfo().getHavehigh());
        lastMusic.setSong_id(info.getSonginfo().getSong_id());
        lastMusic.setIs_first_publish(info.getSonginfo().getIs_first_publish());
        lastMusic.setKorean_bb_song(info.getSonginfo().getKorean_bb_song());
        lastMusic.setPic_huge(info.getSonginfo().getPic_huge());
        lastMusic.setTitle(info.getSonginfo().getTitle());
        lastMusic.setTing_uid(info.getSonginfo().getTing_uid());
        lastMusic.setSpecial_type(info.getSonginfo().getSpecial_type());
        lastMusic.setSong_source(info.getSonginfo().getSong_source());
        lastMusic.setResource_type_ext(info.getSonginfo().getResource_type_ext());
        lastMusic.setPlay_type(info.getSonginfo().getPlay_type());
        lastMusic.setPic_small(info.getSonginfo().getPic_small());
        lastMusic.setPic_radio(info.getSonginfo().getPic_radio());
        lastMusic.setRelate_status(info.getSonginfo().getRelate_status());
        lastMusic.setPiao_id(info.getSonginfo().getPiao_id());
        lastMusic.setPic_big(info.getSonginfo().getPic_big());
        lastMusic.setLrclink(info.getSonginfo().getLrclink());
        lastMusic.setPic_premium(info.getSonginfo().getPic_premium());
        lastMusic.setLearn(info.getSonginfo().getLearn());
        lastMusic.setToneid(info.getSonginfo().getToneid());
        lastMusic.setFile_link(info.getBitrate().getFile_link());
        Log.d("MediaPlayService", "musicUtils.search(songDetailInfo.getSonginfo().getSong_id()):" + musicUtils.search(songDetailInfo.getSonginfo().getSong_id()));
        if(musicUtils.search(songDetailInfo.getSonginfo().getSong_id())==true){
            musicUtils.insertMusic(lastMusic);
        }
        Log.d("MediaPlayService", "musicUtils.getlist():" + musicUtils.getlist().size());
        EventBus.getDefault().post(new Song());
        startActivity(intent);

    }
    /**
     * 给PlayingActivity传送SongUpdateInfo列表
     */
    public List<SongUpdateInfo> getPlayingList(){
        List<SongUpdateInfo> list = new ArrayList<>();
        for(int i=0;i<musiclist.size();i++){
            SongDetailInfo info = musiclist.get(i);
            SongUpdateInfo updateInfo = new SongUpdateInfo();
            updateInfo.setAuthor(info.getSonginfo().getAuthor());
            updateInfo.setTitle(info.getSonginfo().getTitle());
            updateInfo.setPicUrl(info.getSonginfo().getPic_premium());
            list.add(updateInfo);
        }
        return list;
    }

    /**
     * 音乐播放
     *
     * @param musicUrl
     */
    private void play(String musicUrl, boolean isLocal) {
        //给予无网络提示
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getAppContext())) {
            if (!isLocal) {
                Toast.makeText(AppApplication.getAppContext(), "没有网络了哟，请检查网络设置", Toast.LENGTH_SHORT).show();
            }
        }
        if (null == mediaPlayer) return;
        if (requestFocus()) {
            try {
                currentTime = 0;
                mediaPlayer.setDataSource(musicUrl);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnCompletionListener(completionListener);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        currentTime = mp.getCurrentPosition();
                        duration = mp.getDuration();
                        //发送广播 通知PlayActivity界面更新UI
                        es.execute(progressRunnable);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    } /**
     * 音乐播放
     *
     * @param musicUrl
     */
    private void playlast(String musicUrl, boolean isLocal) {
        //给予无网络提示
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getAppContext())) {
            if (!isLocal) {
                Toast.makeText(AppApplication.getAppContext(), "没有网络了哟，请检查网络设置", Toast.LENGTH_SHORT).show();
            }
        }
        if (null == mediaPlayer) return;
        if (requestFocus()) {
            try {
                currentTime = 0;
                mediaPlayer.setDataSource(musicUrl);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnCompletionListener(lastcompletionListener);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        currentTime = mp.getCurrentPosition();
                        duration = mp.getDuration();
                        //发送广播 通知PlayActivity界面更新UI
                        es.execute(progressRunnable);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



    /**
     * 播放全部
     *
     * @param musicUrl
     */
    private void playForPlayAll(String musicUrl, boolean isLocal) {
        //给予无网络提示
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getAppContext())) {
            if (!isLocal) {
                Toast.makeText(AppApplication.getAppContext(), "没有网络了哟，请检查网络设置", Toast.LENGTH_SHORT).show();
            }
        }
        if (null == mediaPlayer) return;
        if (requestFocus()) {
            try {
                currentTime = 0;
                mediaPlayer.setDataSource(musicUrl);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnCompletionListener(completionListener);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        currentTime = mp.getCurrentPosition();
                        duration = mp.getDuration();
                        //发送广播 通知PlayActivity界面更新UI
                        es.execute(progressRunnable);
                        startPlayingActivity(songDetailInfo);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 播放全部
     */
    public void playAll(boolean isLocal) {
        setPlayAll(true);
        SongDetailInfo info = musiclist.get(0);
        if (songDetailInfo == null || !info.getSonginfo().getSong_id().equals(songDetailInfo.getSonginfo().getSong_id())) {
            //不是同一首歌
            songDetailInfo = info;
        }

        if (isPlayAll) {
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            songDetailInfo.setOnClick(true);
            playForPlayAll(songDetailInfo.getBitrate().getFile_link(), isLocal);

        }
    }


    /**
     * 获取音乐焦点
     *
     * @return
     */
    private boolean requestFocus() {
        // Request audio focus for playback
        int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    //音频焦点监听处理
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    //获取焦点 继续播放
                    resume();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    //永久失去 音频焦点
                    stop();
                    audioManager.abandonAudioFocus(onAudioFocusChangeListener);//放弃音频焦点
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    //暂时失去 音频焦点，并会很快再次获得。必须停止Audio的播放，但是因为可能会很快再次获得AudioFocus，这里可以不释放Media资源
                    pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    //TODO 暂时失去 音频焦点 ，但是可以继续播放，不过要在降低音量。
                    break;

            }
        }
    };

    MediaPlayer.OnCompletionListener lastcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer player) {

            if(play_mode==AppContent.PLAYING_MODE_REPEAT_ALL){
                //列表循环
                EventBus.getDefault().post(new UpdateViewPagerBean());
            }
            if(play_mode ==AppContent.PLAYING_MODE_REPEAT_CURRENT){
                //单曲循环
                playlastSong(position,false);
            }
            if(play_mode ==AppContent.PLAYING_MODE_SHUFFLE_NORMAL){
                //随机播放
                int temp = new Random().nextInt(lastMusics.size());
                position = temp;
                playlastSong(position,false);
            }


        }
    };

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer player) {
            System.out.println("音乐播放完毕");
            if(play_mode==AppContent.PLAYING_MODE_REPEAT_ALL){
                //列表循环
                songDetailInfo.setOnClick(false);
                EventBus.getDefault().post(new UpdateViewPagerBean());
            }
            if(play_mode ==AppContent.PLAYING_MODE_REPEAT_CURRENT){
                //单曲循环
                songDetailInfo.setOnClick(false);
                playSong(position,false);
            }
            if(play_mode ==AppContent.PLAYING_MODE_SHUFFLE_NORMAL){
                //随机播放
                songDetailInfo.setOnClick(false);
                int temp = new Random().nextInt(musiclist.size());
                position = temp;
                playSong(position,false);
            }


        }
    };

    /**
     * 设置播放模式
     * @param state 播放模式
     */
    public void setPlayMode(int state){
        play_mode = state;
    }

    /**
     * 获得当前的播放模式
     * @return 播放模式
     */
    public int getPlayMode(){
        return  play_mode;
    }



    /**
     * 向集合中添加待播放歌曲
     *
     * @param info
     */
    public void addMusicList(SongDetailInfo info) {
        musiclist.add(info);
    }


    /**
     * 清空播放集合
     */
    public void clearMusicList() {
        musiclist.clear();
    }


    /**
     * 设置是否播放全部
     *
     * @param isPlayAll
     */
    public void setPlayAll(boolean isPlayAll) {
        this.isPlayAll = isPlayAll;

    }

    /**
     * 暂停
     */
    public void pause() {
        if (null == mediaPlayer) return;
        if (mediaPlayer.isPlaying()) {
            currentTime = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();

        }
    }

    public void stop() {

    }

    /**
     * 音乐继续播放
     */
    public void resume() {
        if (null == mediaPlayer) return;
        mediaPlayer.start();
        //播放的同时，更新进度条
        if (currentTime > 0) {
            mediaPlayer.seekTo(currentTime);
        }

    }

    /**
     * 拖动Seekbar音乐跳转
     *
     * @param position
     */
    public void seekTo(int position) {
        if (null == mediaPlayer) return;
        mediaPlayer.seekTo(position);
//        if (position < duration) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            }
//        }
    }


    /**
     * 播放下一首
     */
    public void next(boolean isLocal) {
        System.out.println("next方法的调用");
        currentTime = 0;
        if (position < 0) {
            position = 0;
        }
        if (musiclist.size() > 0) {
            position++;
            if (position < musiclist.size()) {//当前歌曲的索引小于歌曲集合的长度

            } else {
                //循环从第一首开始播放
                position = 0;
            }
            playSong(position, isLocal);
            updatePlayingUI(songDetailInfo);
        }
    }

    /**
     * 更新正在播放歌曲的UI
     *
     * @param info 歌曲信息
     */
    private void updatePlayingUI(SongDetailInfo info) {
        flag = true;
        final SongUpdateInfo updateInfo = new SongUpdateInfo();
        updateInfo.setAuthor(info.getSonginfo().getAuthor());
        updateInfo.setTitle(info.getSonginfo().getTitle());
        updateInfo.setPicUrl(info.getSonginfo().getPic_premium());

        MusicUtils musicUtils=new MusicUtils(AppApplication.getAppContext());
        LastMusic lastMusic=new LastMusic();
        lastMusic.setAlbum_id(info.getSonginfo().getAlbum_id());
        lastMusic.setAlbum_no(info.getSonginfo().getAlbum_no());
        lastMusic.setAlbum_title(info.getSonginfo().getAlbum_title());
        lastMusic.setAll_artist_id(info.getSonginfo().getAll_artist_id());
        lastMusic.setAll_artist_ting_uid(info.getSonginfo().getAll_artist_ting_uid());
        lastMusic.setAll_rate(info.getSonginfo().getAll_rate());
        lastMusic.setArtist_id(info.getSonginfo().getArtist_id());
        lastMusic.setAuthor(info.getSonginfo().getAuthor());
        lastMusic.setBitrate_fee(info.getSonginfo().getBitrate_fee());
        lastMusic.setCharge(info.getSonginfo().getCharge());
        lastMusic.setCopy_type(info.getSonginfo().getCopy_type());
        lastMusic.setHas_mv(info.getSonginfo().getHas_mv());
        lastMusic.setHas_mv_mobile(info.getSonginfo().getHas_mv_mobile());
        lastMusic.setHavehigh(info.getSonginfo().getHavehigh());
        lastMusic.setSong_id(info.getSonginfo().getSong_id());
        lastMusic.setIs_first_publish(info.getSonginfo().getIs_first_publish());
        lastMusic.setKorean_bb_song(info.getSonginfo().getKorean_bb_song());
        lastMusic.setPic_huge(info.getSonginfo().getPic_huge());
        lastMusic.setTitle(info.getSonginfo().getTitle());
        lastMusic.setTing_uid(info.getSonginfo().getTing_uid());
        lastMusic.setSpecial_type(info.getSonginfo().getSpecial_type());
        lastMusic.setSong_source(info.getSonginfo().getSong_source());
        lastMusic.setResource_type_ext(info.getSonginfo().getResource_type_ext());
        lastMusic.setPlay_type(info.getSonginfo().getPlay_type());
        lastMusic.setPic_small(info.getSonginfo().getPic_small());
        lastMusic.setPic_radio(info.getSonginfo().getPic_radio());
        lastMusic.setRelate_status(info.getSonginfo().getRelate_status());
        lastMusic.setPiao_id(info.getSonginfo().getPiao_id());
        lastMusic.setPic_big(info.getSonginfo().getPic_big());
        lastMusic.setLrclink(info.getSonginfo().getLrclink());
        lastMusic.setPic_premium(info.getSonginfo().getPic_premium());
        lastMusic.setLearn(info.getSonginfo().getLearn());
        lastMusic.setToneid(info.getSonginfo().getToneid());
        lastMusic.setFile_link(info.getBitrate().getFile_link());
        Log.d("MediaPlayService", "musicUtils.search(songDetailInfo.getSonginfo().getSong_id()):" + musicUtils.search(songDetailInfo.getSonginfo().getSong_id()));
        if(musicUtils.search(songDetailInfo.getSonginfo().getSong_id())==true){
            musicUtils.insertMusic(lastMusic);
        }
        Log.d("MediaPlayService", "musicUtils.getlist():" + musicUtils.getlist().size());
        EventBus.getDefault().post(new Song());
        updateInfo.setIndex(position);
        System.out.println("发送更新UI的消息，索引为："+position);
        EventBus.getDefault().post(updateInfo);




    }

    /**
     * 播放上一首
     */
    public void pre(boolean isLocal) {
        currentTime = 0;
        if (position < 0) {
            position = 0;
        }
        if (musiclist.size() > 0) {
            position--;
            if (position >= 0) {//大于等于0的情况
                playSong(position, isLocal);
            } else {
                position = 0;
                songDetailInfo.setOnClick(false);
                playSong(position, isLocal);
            }
        }
        updatePlayingUI(songDetailInfo);
    }


    /**
     * 音乐是否播放
     *
     * @return
     */
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        es.shutdownNow();
        audioManager.abandonAudioFocus(onAudioFocusChangeListener);
    }



}
