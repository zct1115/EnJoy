package com.ckkj.enjoy.database;

import android.content.Context;


import com.enjoy.dao.DaoMaster;
import com.enjoy.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;


/**
 * Created by zct11 on 2017/8/17.
 */

public class DaoManager {

    public static final String DB_NAME = "Myenjoy.sqlite";//数据库名称
    private volatile static DaoManager daoManager;//多线程访问
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;


    /*使用单例模式操作数据库*/
    public static DaoManager getInstance() {
        DaoManager instance = null;
      //  if (daoManager == null) {
        synchronized (DaoManager.class) {
            if (instance == null) {
                instance = new DaoManager();
                daoManager = instance;
            }
            }
       // }
        return instance;
    }


    /*判断是否存在数据库，如果没有就创建数据库*/
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /*完成数据库的增加，删除修改查询，仅仅是一个接口*/
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {

                daoMaster = getDaoMaster();

            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /*打开输出日志的操作*/
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

    }

    /*关闭所有的操作*/
    public void closeConnect() {
        closeHelper();
        closeDaoSession();
        daoManager = null;

    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void init(Context context) {
        this.context = context;
    }


}
