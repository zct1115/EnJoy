package com.ckkj.enjoy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ting on 2017/9/19.
 */

public class MovieDetils implements Serializable {

    /**
     * rating : {"max":10,"average":7.1,"stars":"35","min":0}
     * reviews_count : 727
     * wish_count : 38062
     * douban_site :
     * year : 2017
     * images : {"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2494093630.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2494093630.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2494093630.webp"}
     * alt : https://movie.douban.com/subject/25808075/
     * id : 25808075
     * mobile_url : https://movie.douban.com/subject/25808075/mobile
     * title : 猩球崛起3：终极之战
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/25808075
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/25808075/cinema/
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["剧情","动作","科幻"]
     * collect_count : 48254
     * casts : [{"alt":"https://movie.douban.com/celebrity/1002708/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1375081883.31.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1375081883.31.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1375081883.31.jpg"},"name":"安迪·瑟金斯","id":"1002708"},{"alt":"https://movie.douban.com/celebrity/1053560/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/501.jpg","large":"http://img7.doubanio.com/img/celebrity/large/501.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/501.jpg"},"name":"伍迪·哈里森","id":"1053560"},{"alt":"https://movie.douban.com/celebrity/1035639/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/7723.jpg","large":"http://img7.doubanio.com/img/celebrity/large/7723.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/7723.jpg"},"name":"史蒂夫·茨恩","id":"1035639"},{"alt":"https://movie.douban.com/celebrity/1341378/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1500260744.95.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1500260744.95.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1500260744.95.jpg"},"name":"卡琳·考诺娃","id":"1341378"}]
     * current_season : null
     * original_title : War for the Planet of the Apes
     * summary : 凯撒（安迪·瑟金斯 饰）领导的猿族将被迫与残暴的Colonel（伍迪·哈里森 饰）领导的人类军队上演一场生死大战。猿族在战斗中遭遇了前所未有的重创，由此激发了凯撒内心中黑暗的一面，心中燃起复仇的烈火。最终，凯撒与Colonel面对面进行了一场关乎猿族和人类命运的终极之战。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1045032/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/18161.jpg","large":"http://img7.doubanio.com/img/celebrity/large/18161.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/18161.jpg"},"name":"马特·里夫斯","id":"1045032"}]
     * comments_count : 22013
     * ratings_count : 45433
     * aka : ["猩球崛起：终极决战(台)","猿人争霸战：猩凶巨战(港)","猩球大战：猿力觉醒(豆友译名)","猩球崛起3","人猿猩球3","Planet of the Apes 3"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
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

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean implements Serializable{
        /**
         * max : 10
         * average : 7.1
         * stars : 35
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
         * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2494093630.webp
         * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2494093630.webp
         * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2494093630.webp
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
         * alt : https://movie.douban.com/celebrity/1002708/
         * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/1375081883.31.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1375081883.31.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1375081883.31.jpg"}
         * name : 安迪·瑟金斯
         * id : 1002708
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
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
    }

    public static class DirectorsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1045032/
         * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/18161.jpg","large":"http://img7.doubanio.com/img/celebrity/large/18161.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/18161.jpg"}
         * name : 马特·里夫斯
         * id : 1045032
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
             * small : http://img7.doubanio.com/img/celebrity/small/18161.jpg
             * large : http://img7.doubanio.com/img/celebrity/large/18161.jpg
             * medium : http://img7.doubanio.com/img/celebrity/medium/18161.jpg
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
