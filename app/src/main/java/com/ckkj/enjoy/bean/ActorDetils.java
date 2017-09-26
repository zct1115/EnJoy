package com.ckkj.enjoy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HiWin10 on 2017/9/21 0021.
 */

public class ActorDetils implements Serializable {

    /**
     * mobile_url : https://movie.douban.com/celebrity/1002708/mobile
     * aka_en : ["Gollum (昵称)"]
     * name : 安迪·瑟金斯
     * works : [{"roles":["演员","配音"],"subject":{"rating":{"max":10,"average":9.5,"stars":"50","min":0},"genres":["动画","喜剧"],"title":"辛普森一家  第十四季","casts":[{"alt":"https://movie.douban.com/celebrity/1041148/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/5835.jpg","large":"http://img7.doubanio.com/img/celebrity/large/5835.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/5835.jpg"},"name":"丹·卡斯泰兰尼塔","id":"1041148"},{"alt":"https://movie.douban.com/celebrity/1049754/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/17298.jpg","large":"http://img3.doubanio.com/img/celebrity/large/17298.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/17298.jpg"},"name":"朱莉·卡夫娜","id":"1049754"},{"alt":"https://movie.douban.com/celebrity/1041172/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/5836.jpg","large":"http://img3.doubanio.com/img/celebrity/large/5836.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/5836.jpg"},"name":"南茜·卡特莱特","id":"1041172"}],"collect_count":777,"original_title":"The Simpsons ","subtype":"tv","directors":[{"alt":"https://movie.douban.com/celebrity/1032493/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/36456.jpg","large":"http://img3.doubanio.com/img/celebrity/large/36456.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/36456.jpg"},"name":"大卫·斯沃曼","id":"1032493"},{"alt":"https://movie.douban.com/celebrity/1362986/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"马克·柯克兰","id":"1362986"},{"alt":"https://movie.douban.com/celebrity/1295326/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1489649908.44.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1489649908.44.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1489649908.44.jpg"},"name":"吉姆·里尔顿","id":"1295326"},{"alt":"https://movie.douban.com/celebrity/1363299/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"迈克·B·安德森","id":"1363299"},{"alt":"https://movie.douban.com/celebrity/1363107/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"斯蒂文·迪恩·摩尔","id":"1363107"},{"alt":"https://movie.douban.com/celebrity/1362977/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"鲍勃·安德森","id":"1362977"},{"alt":"https://movie.douban.com/celebrity/1360645/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"皮特·米歇尔斯","id":"1360645"},{"alt":"https://movie.douban.com/celebrity/1363466/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"迈克尔·玻尔奇诺","id":"1363466"},{"alt":"https://movie.douban.com/celebrity/1363310/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"马修·纳斯奇克","id":"1363310"},{"alt":"https://movie.douban.com/celebrity/1363718/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"南茜·克鲁斯","id":"1363718"},{"alt":"https://movie.douban.com/celebrity/1363318/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"兰斯·克雷默","id":"1363318"},{"alt":"https://movie.douban.com/celebrity/1363465/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"克里斯·克莱门茨","id":"1363465"},{"alt":"https://movie.douban.com/celebrity/1337975/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"劳伦·麦克穆伦","id":"1337975"}],"year":"2002","images":{"small":"http://img3.doubanio.com/spic/s7412489.jpg","large":"http://img3.doubanio.com/lpic/s7412489.jpg","medium":"http://img3.doubanio.com/mpic/s7412489.jpg"},"alt":"https://movie.douban.com/subject/4919246/","id":"4919246"}},{"roles":["演员"],"subject":{"rating":{"max":10,"average":9.3,"stars":"50","min":0},"genres":["历史","纪录片"],"title":"艺术的力量","casts":[{"alt":null,"avatars":null,"name":"Simon Schama","id":null},{"alt":"https://movie.douban.com/celebrity/1022620/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/13151.jpg","large":"http://img7.doubanio.com/img/celebrity/large/13151.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/13151.jpg"},"name":"安德鲁·加菲尔德","id":"1022620"},{"alt":"https://movie.douban.com/celebrity/1002708/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1375081883.31.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1375081883.31.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1375081883.31.jpg"},"name":"安迪·瑟金斯","id":"1002708"}],"collect_count":6261,"original_title":"Simon Schama's Power of Art","subtype":"tv","directors":[{"alt":null,"avatars":null,"name":"Carl Hindmarch","id":null}],"year":"2006","images":{"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2045286523.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2045286523.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2045286523.webp"},"alt":"https://movie.douban.com/subject/2063068/","id":"2063068"}},{"roles":["演员"],"subject":{"rating":{"max":10,"average":9.1,"stars":"45","min":0},"genres":["剧情","动作","奇幻"],"title":"指环王3：王者无敌","casts":[{"alt":"https://movie.douban.com/celebrity/1054520/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/29922.jpg","large":"http://img7.doubanio.com/img/celebrity/large/29922.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/29922.jpg"},"name":"维果·莫腾森","id":"1054520"},{"alt":"https://movie.douban.com/celebrity/1054395/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/51597.jpg","large":"http://img3.doubanio.com/img/celebrity/large/51597.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/51597.jpg"},"name":"伊利亚·伍德","id":"1054395"},{"alt":"https://movie.douban.com/celebrity/1031818/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/11727.jpg","large":"http://img3.doubanio.com/img/celebrity/large/11727.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/11727.jpg"},"name":"西恩·奥斯汀","id":"1031818"}],"collect_count":377894,"original_title":"The Lord of the Rings: The Return of the King","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1040524/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/40835.jpg","large":"http://img7.doubanio.com/img/celebrity/large/40835.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/40835.jpg"},"name":"彼得·杰克逊","id":"1040524"}],"year":"2003","images":{"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p1910825503.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p1910825503.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p1910825503.webp"},"alt":"https://movie.douban.com/subject/1291552/","id":"1291552"}},{"roles":["演员"],"subject":{"rating":{"max":10,"average":9.1,"stars":"45","min":0},"genres":["剧情"],"title":"殊途同归 第一季","casts":[{"alt":"https://movie.douban.com/celebrity/1006990/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/13017.jpg","large":"http://img3.doubanio.com/img/celebrity/large/13017.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/13017.jpg"},"name":"克里斯托弗·埃克莱斯顿","id":"1006990"},{"alt":"https://movie.douban.com/celebrity/1027264/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1599.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1599.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1599.jpg"},"name":"麦肯锡·克鲁克","id":"1027264"},{"alt":"https://movie.douban.com/celebrity/1041212/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/17788.jpg","large":"http://img3.doubanio.com/img/celebrity/large/17788.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/17788.jpg"},"name":"茱丽叶特·斯蒂文森","id":"1041212"}],"collect_count":8411,"original_title":"Accused","subtype":"tv","directors":[{"alt":"https://movie.douban.com/celebrity/1276496/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"戴维·布莱尔","id":"1276496"},{"alt":"https://movie.douban.com/celebrity/1289570/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/32159.jpg","large":"http://img3.doubanio.com/img/celebrity/large/32159.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/32159.jpg"},"name":"理查德·莱克斯顿","id":"1289570"}],"year":"2010","images":{"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2170094410.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2170094410.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2170094410.webp"},"alt":"https://movie.douban.com/subject/5313428/","id":"5313428"}},{"roles":["演员"],"subject":{"rating":{"max":10,"average":9,"stars":"45","min":0},"genres":["剧情","喜剧","动画"],"title":"辛普森一家  第一季","casts":[{"alt":"https://movie.douban.com/celebrity/1041148/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/5835.jpg","large":"http://img7.doubanio.com/img/celebrity/large/5835.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/5835.jpg"},"name":"丹·卡斯泰兰尼塔","id":"1041148"},{"alt":"https://movie.douban.com/celebrity/1049754/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/17298.jpg","large":"http://img3.doubanio.com/img/celebrity/large/17298.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/17298.jpg"},"name":"朱莉·卡夫娜","id":"1049754"},{"alt":"https://movie.douban.com/celebrity/1041172/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/5836.jpg","large":"http://img3.doubanio.com/img/celebrity/large/5836.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/5836.jpg"},"name":"南茜·卡特莱特","id":"1041172"}],"collect_count":18017,"original_title":"The Simpsons ","subtype":"tv","directors":[{"alt":"https://movie.douban.com/celebrity/1032493/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/36456.jpg","large":"http://img3.doubanio.com/img/celebrity/large/36456.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/36456.jpg"},"name":"大卫·斯沃曼","id":"1032493"},{"alt":"https://movie.douban.com/celebrity/1027204/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1264.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1264.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1264.jpg"},"name":"布拉德·伯德","id":"1027204"}],"year":"1989","images":{"small":"http://img3.doubanio.com/spic/s3204836.jpg","large":"http://img3.doubanio.com/lpic/s3204836.jpg","medium":"http://img3.doubanio.com/mpic/s3204836.jpg"},"alt":"https://movie.douban.com/subject/1418197/","id":"1418197"}}]
     * gender : 男
     * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/1375081883.31.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1375081883.31.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1375081883.31.jpg"}
     * id : 1002708
     * aka : ["安迪·塞基斯","安迪·瑟克斯"]
     * name_en : Andy Serkis
     * born_place : 英国,伦敦,赖斯利普
     * alt : https://movie.douban.com/celebrity/1002708/
     */

    private String mobile_url;
    private String name;
    private String gender;
    private AvatarsBean avatars;
    private String id;
    private String name_en;
    private String born_place;
    private String alt;
    private List<String> aka_en;
    private List<WorksBean> works;
    private List<String> aka;

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getBorn_place() {
        return born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<String> getAka_en() {
        return aka_en;
    }

    public void setAka_en(List<String> aka_en) {
        this.aka_en = aka_en;
    }

    public List<WorksBean> getWorks() {
        return works;
    }

    public void setWorks(List<WorksBean> works) {
        this.works = works;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class AvatarsBean implements Serializable{
        /**
         * small : http://img7.doubanio.com/img/celebrity/small/1375081883.31.jpg
         * large : http://img7.doubanio.com/img/celebrity/large/1375081883.31.jpg
         * medium : http://img7.doubanio.com/img/celebrity/medium/1375081883.31.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class WorksBean implements Serializable{
        /**
         * roles : ["演员","配音"]
         * subject : {"rating":{"max":10,"average":9.5,"stars":"50","min":0},"genres":["动画","喜剧"],"title":"辛普森一家  第十四季","casts":[{"alt":"https://movie.douban.com/celebrity/1041148/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/5835.jpg","large":"http://img7.doubanio.com/img/celebrity/large/5835.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/5835.jpg"},"name":"丹·卡斯泰兰尼塔","id":"1041148"},{"alt":"https://movie.douban.com/celebrity/1049754/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/17298.jpg","large":"http://img3.doubanio.com/img/celebrity/large/17298.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/17298.jpg"},"name":"朱莉·卡夫娜","id":"1049754"},{"alt":"https://movie.douban.com/celebrity/1041172/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/5836.jpg","large":"http://img3.doubanio.com/img/celebrity/large/5836.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/5836.jpg"},"name":"南茜·卡特莱特","id":"1041172"}],"collect_count":777,"original_title":"The Simpsons ","subtype":"tv","directors":[{"alt":"https://movie.douban.com/celebrity/1032493/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/36456.jpg","large":"http://img3.doubanio.com/img/celebrity/large/36456.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/36456.jpg"},"name":"大卫·斯沃曼","id":"1032493"},{"alt":"https://movie.douban.com/celebrity/1362986/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"马克·柯克兰","id":"1362986"},{"alt":"https://movie.douban.com/celebrity/1295326/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1489649908.44.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1489649908.44.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1489649908.44.jpg"},"name":"吉姆·里尔顿","id":"1295326"},{"alt":"https://movie.douban.com/celebrity/1363299/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"迈克·B·安德森","id":"1363299"},{"alt":"https://movie.douban.com/celebrity/1363107/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"斯蒂文·迪恩·摩尔","id":"1363107"},{"alt":"https://movie.douban.com/celebrity/1362977/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"鲍勃·安德森","id":"1362977"},{"alt":"https://movie.douban.com/celebrity/1360645/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"皮特·米歇尔斯","id":"1360645"},{"alt":"https://movie.douban.com/celebrity/1363466/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"迈克尔·玻尔奇诺","id":"1363466"},{"alt":"https://movie.douban.com/celebrity/1363310/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"马修·纳斯奇克","id":"1363310"},{"alt":"https://movie.douban.com/celebrity/1363718/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"南茜·克鲁斯","id":"1363718"},{"alt":"https://movie.douban.com/celebrity/1363318/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"兰斯·克雷默","id":"1363318"},{"alt":"https://movie.douban.com/celebrity/1363465/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"克里斯·克莱门茨","id":"1363465"},{"alt":"https://movie.douban.com/celebrity/1337975/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"劳伦·麦克穆伦","id":"1337975"}],"year":"2002","images":{"small":"http://img3.doubanio.com/spic/s7412489.jpg","large":"http://img3.doubanio.com/lpic/s7412489.jpg","medium":"http://img3.doubanio.com/mpic/s7412489.jpg"},"alt":"https://movie.douban.com/subject/4919246/","id":"4919246"}
         */

        private SubjectBean subject;
        private List<String> roles;

        public SubjectBean getSubject() {
            return subject;
        }

        public void setSubject(SubjectBean subject) {
            this.subject = subject;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public static class SubjectBean implements Serializable{
            /**
             * rating : {"max":10,"average":9.5,"stars":"50","min":0}
             * genres : ["动画","喜剧"]
             * title : 辛普森一家  第十四季
             * casts : [{"alt":"https://movie.douban.com/celebrity/1041148/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/5835.jpg","large":"http://img7.doubanio.com/img/celebrity/large/5835.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/5835.jpg"},"name":"丹·卡斯泰兰尼塔","id":"1041148"},{"alt":"https://movie.douban.com/celebrity/1049754/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/17298.jpg","large":"http://img3.doubanio.com/img/celebrity/large/17298.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/17298.jpg"},"name":"朱莉·卡夫娜","id":"1049754"},{"alt":"https://movie.douban.com/celebrity/1041172/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/5836.jpg","large":"http://img3.doubanio.com/img/celebrity/large/5836.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/5836.jpg"},"name":"南茜·卡特莱特","id":"1041172"}]
             * collect_count : 777
             * original_title : The Simpsons
             * subtype : tv
             * directors : [{"alt":"https://movie.douban.com/celebrity/1032493/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/36456.jpg","large":"http://img3.doubanio.com/img/celebrity/large/36456.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/36456.jpg"},"name":"大卫·斯沃曼","id":"1032493"},{"alt":"https://movie.douban.com/celebrity/1362986/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"马克·柯克兰","id":"1362986"},{"alt":"https://movie.douban.com/celebrity/1295326/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1489649908.44.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1489649908.44.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1489649908.44.jpg"},"name":"吉姆·里尔顿","id":"1295326"},{"alt":"https://movie.douban.com/celebrity/1363299/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"迈克·B·安德森","id":"1363299"},{"alt":"https://movie.douban.com/celebrity/1363107/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"斯蒂文·迪恩·摩尔","id":"1363107"},{"alt":"https://movie.douban.com/celebrity/1362977/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"鲍勃·安德森","id":"1362977"},{"alt":"https://movie.douban.com/celebrity/1360645/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"皮特·米歇尔斯","id":"1360645"},{"alt":"https://movie.douban.com/celebrity/1363466/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"迈克尔·玻尔奇诺","id":"1363466"},{"alt":"https://movie.douban.com/celebrity/1363310/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"马修·纳斯奇克","id":"1363310"},{"alt":"https://movie.douban.com/celebrity/1363718/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"南茜·克鲁斯","id":"1363718"},{"alt":"https://movie.douban.com/celebrity/1363318/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"兰斯·克雷默","id":"1363318"},{"alt":"https://movie.douban.com/celebrity/1363465/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"克里斯·克莱门茨","id":"1363465"},{"alt":"https://movie.douban.com/celebrity/1337975/","avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"劳伦·麦克穆伦","id":"1337975"}]
             * year : 2002
             * images : {"small":"http://img3.doubanio.com/spic/s7412489.jpg","large":"http://img3.doubanio.com/lpic/s7412489.jpg","medium":"http://img3.doubanio.com/mpic/s7412489.jpg"}
             * alt : https://movie.douban.com/subject/4919246/
             * id : 4919246
             */

            private RatingBean rating;
            private String title;
            private int collect_count;
            private String original_title;
            private String subtype;
            private String year;
            private ImagesBean images;
            private String alt;
            private String id;
            private List<String> genres;
            private List<CastsBean> casts;
            private List<DirectorsBean> directors;

            public RatingBean getRating() {
                return rating;
            }

            public void setRating(RatingBean rating) {
                this.rating = rating;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCollect_count() {
                return collect_count;
            }

            public void setCollect_count(int collect_count) {
                this.collect_count = collect_count;
            }

            public String getOriginal_title() {
                return original_title;
            }

            public void setOriginal_title(String original_title) {
                this.original_title = original_title;
            }

            public String getSubtype() {
                return subtype;
            }

            public void setSubtype(String subtype) {
                this.subtype = subtype;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public ImagesBean getImages() {
                return images;
            }

            public void setImages(ImagesBean images) {
                this.images = images;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<String> getGenres() {
                return genres;
            }

            public void setGenres(List<String> genres) {
                this.genres = genres;
            }

            public List<CastsBean> getCasts() {
                return casts;
            }

            public void setCasts(List<CastsBean> casts) {
                this.casts = casts;
            }

            public List<DirectorsBean> getDirectors() {
                return directors;
            }

            public void setDirectors(List<DirectorsBean> directors) {
                this.directors = directors;
            }

            public static class RatingBean implements Serializable{
                /**
                 * max : 10
                 * average : 9.5
                 * stars : 50
                 * min : 0
                 */

                private int max;
                private double average;
                private String stars;
                private int min;

                public int getMax() {
                    return max;
                }

                public void setMax(int max) {
                    this.max = max;
                }

                public double getAverage() {
                    return average;
                }

                public void setAverage(double average) {
                    this.average = average;
                }

                public String getStars() {
                    return stars;
                }

                public void setStars(String stars) {
                    this.stars = stars;
                }

                public int getMin() {
                    return min;
                }

                public void setMin(int min) {
                    this.min = min;
                }
            }

            public static class ImagesBean implements Serializable{
                /**
                 * small : http://img3.doubanio.com/spic/s7412489.jpg
                 * large : http://img3.doubanio.com/lpic/s7412489.jpg
                 * medium : http://img3.doubanio.com/mpic/s7412489.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }

            public static class CastsBean implements Serializable{
                /**
                 * alt : https://movie.douban.com/celebrity/1041148/
                 * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/5835.jpg","large":"http://img7.doubanio.com/img/celebrity/large/5835.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/5835.jpg"}
                 * name : 丹·卡斯泰兰尼塔
                 * id : 1041148
                 */

                private String alt;
                private AvatarsBeanX avatars;
                private String name;
                private String id;

                public String getAlt() {
                    return alt;
                }

                public void setAlt(String alt) {
                    this.alt = alt;
                }

                public AvatarsBeanX getAvatars() {
                    return avatars;
                }

                public void setAvatars(AvatarsBeanX avatars) {
                    this.avatars = avatars;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public static class AvatarsBeanX implements Serializable{
                    /**
                     * small : http://img7.doubanio.com/img/celebrity/small/5835.jpg
                     * large : http://img7.doubanio.com/img/celebrity/large/5835.jpg
                     * medium : http://img7.doubanio.com/img/celebrity/medium/5835.jpg
                     */

                    private String small;
                    private String large;
                    private String medium;

                    public String getSmall() {
                        return small;
                    }

                    public void setSmall(String small) {
                        this.small = small;
                    }

                    public String getLarge() {
                        return large;
                    }

                    public void setLarge(String large) {
                        this.large = large;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }
                }
            }

            public static class DirectorsBean implements Serializable{
                /**
                 * alt : https://movie.douban.com/celebrity/1032493/
                 * avatars : {"small":"http://img3.doubanio.com/img/celebrity/small/36456.jpg","large":"http://img3.doubanio.com/img/celebrity/large/36456.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/36456.jpg"}
                 * name : 大卫·斯沃曼
                 * id : 1032493
                 */

                private String alt;
                private AvatarsBeanXX avatars;
                private String name;
                private String id;

                public String getAlt() {
                    return alt;
                }

                public void setAlt(String alt) {
                    this.alt = alt;
                }

                public AvatarsBeanXX getAvatars() {
                    return avatars;
                }

                public void setAvatars(AvatarsBeanXX avatars) {
                    this.avatars = avatars;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public static class AvatarsBeanXX implements Serializable{
                    /**
                     * small : http://img3.doubanio.com/img/celebrity/small/36456.jpg
                     * large : http://img3.doubanio.com/img/celebrity/large/36456.jpg
                     * medium : http://img3.doubanio.com/img/celebrity/medium/36456.jpg
                     */

                    private String small;
                    private String large;
                    private String medium;

                    public String getSmall() {
                        return small;
                    }

                    public void setSmall(String small) {
                        this.small = small;
                    }

                    public String getLarge() {
                        return large;
                    }

                    public void setLarge(String large) {
                        this.large = large;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }
                }
            }
        }
    }
}
