package com.ckkj.enjoy.database.login;

import android.content.Context;

import com.ckkj.enjoy.database.DaoManager;
import com.enjoy.dao.LastMusicDao;
import com.enjoy.dao.LoginInfoDao;
import com.enjoy.entity.LastMusic;
import com.enjoy.entity.LoginInfo;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */

public class Login {
    private DaoManager daoManager;

    public Login(Context context) {
        daoManager=DaoManager.getInstance();
        daoManager.init(context);
    }

    public boolean insert(LoginInfo loginInfo){
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(loginInfo) != -1 ? true : false;
        return flag;
    }

    public void update(LoginInfo loginInfo){
        daoManager.getDaoSession().update(loginInfo);
    }
    public boolean delete(){
        boolean flag = false;
        try {
            daoManager.getDaoSession().deleteAll(LoginInfo.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<LoginInfo> getlist(){
        return daoManager.getDaoSession().loadAll(LoginInfo.class);
    }

    public long musicCount(){
        QueryBuilder<LoginInfo> builder=daoManager.getDaoSession().queryBuilder(LoginInfo.class);
        return builder.count();
    }
    public boolean search(String id){
        boolean flag = false;
        QueryBuilder<LoginInfo> builder=daoManager.getDaoSession().queryBuilder(LoginInfo.class);
        return flag;
    }
}
