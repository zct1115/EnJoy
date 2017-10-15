package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static  void main(String[]   args){
        Schema schema=new Schema(1,"com.enjoy.entity");
        addMusic(schema);
        MyInfo(schema);
        schema.setDefaultJavaPackageDao("com.enjoy.dao");
        try {
            new DaoGenerator().generateAll(schema,"E:\\android\\EnJoy\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void MyInfo(Schema schema) {
        Entity entity=schema.addEntity("LoginInfo");//创建数据库的表
        entity.addIdProperty();//主键 int
        entity.addStringProperty("username");
        entity.addStringProperty("userpasswd");
        entity.addStringProperty("movie_preference");
        entity.addStringProperty("music_preference");
        entity.addStringProperty("user_photo_url");
        entity.addStringProperty("nickname");
        entity.addStringProperty("sex");
        entity.addStringProperty("age");

    }

    private static void addMusic(Schema schema) {
        Entity entity=schema.addEntity("LastMusic");//创建数据库的表
        entity.addIdProperty();//主键 int
        entity.addIntProperty("special_type");//对应数据库的列
        entity.addStringProperty("pic_huge");
        entity.addStringProperty("resource_type");
        entity.addStringProperty("pic_premium");
        entity.addIntProperty("havehigh");
        entity.addStringProperty("author");
        entity.addStringProperty("toneid");
        entity.addIntProperty("has_mv");
        entity.addStringProperty("song_id");
        entity.addStringProperty("piao_id");
        entity.addStringProperty("artist_id");
        entity.addStringProperty("lrclink");
        entity.addStringProperty("relate_status");
        entity.addIntProperty("learn");
        entity.addStringProperty("pic_big");
        entity.addIntProperty("play_type");
        entity.addStringProperty("album_id");
        entity.addStringProperty("album_title");
        entity.addStringProperty("bitrate_fee");
        entity.addStringProperty("song_source");
        entity.addStringProperty("all_artist_id");
        entity.addStringProperty("all_artist_ting_uid");
        entity.addStringProperty("all_rate");
        entity.addIntProperty("charge");
        entity.addStringProperty("copy_type");
        entity.addIntProperty("is_first_publish");
        entity.addStringProperty("korean_bb_song");
        entity.addStringProperty("pic_radio");
        entity.addIntProperty("has_mv_mobile");
        entity.addStringProperty("title");
        entity.addStringProperty("pic_small");
        entity.addStringProperty("album_no");
        entity.addStringProperty("resource_type_ext");
        entity.addStringProperty("ting_uid");



    }
}
