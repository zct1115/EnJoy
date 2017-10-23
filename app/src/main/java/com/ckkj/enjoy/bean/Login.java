package com.ckkj.enjoy.bean;

/**
 * Created by Administrator on 2017/10/22.
 */

public class Login {

    /**
     * desc : 登录成功
     * status : 1000
     * data : {"user":"zct","token":"286630a9a74ebe2c6605dea0f41b84e2","uid":"6"}
     */

    private String desc;
    private int status;
    private DataBean data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user : zct
         * token : 286630a9a74ebe2c6605dea0f41b84e2
         * uid : 6
         */

        private String user;
        private String token;
        private String uid;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
