package com.ckkj.enjoy.database.music;

import android.content.Context;

import com.ckkj.enjoy.database.DaoManager;
import com.enjoy.dao.LastMusicDao;
import com.enjoy.entity.LastMusic;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by zct11 on 2017/10/15.
 */

public class MusicUtils {
    private DaoManager daoManager;

    public MusicUtils(Context context) {
        daoManager=DaoManager.getInstance();
        daoManager.init(context);
    }

    public boolean insertMusic(LastMusic lastMusic){
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(lastMusic) != -1 ? true : false;
        return flag;
    }
    public boolean deleteMusic(){
        boolean flag = false;
        try {
            daoManager.getDaoSession().deleteAll(LastMusic.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<LastMusic> getlist(){
         return daoManager.getDaoSession().loadAll(LastMusic.class);
    }

    public long musicCount(){
        QueryBuilder<LastMusic> builder=daoManager.getDaoSession().queryBuilder(LastMusic.class);
        return builder.count();
    }
    public boolean search(String id){
        boolean flag = false;
       QueryBuilder<LastMusic> builder=daoManager.getDaoSession().queryBuilder(LastMusic.class);
        List<LastMusic> data= builder.where(LastMusicDao.Properties.Song_id.eq(id)).list();
        if(data.size()==0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }
}
