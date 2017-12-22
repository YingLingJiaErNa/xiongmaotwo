package com.example.xiong.bean;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/19.
 */

public class ZongBean {

    private List<TabBean> tab;

    public List<TabBean> getTab() {
        return tab;
    }

    public void setTab(List<TabBean> tab) {
        this.tab = tab;
    }

    public static class TabBean {
        /**
         * title : 首页
         * noimage :
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/12/15/1450172469187_603.jpg
         * url : http://www.ipanda.com/kehuduan/shouye/index.json
         */

        private String title;
        private String noimage;
        private String image;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNoimage() {
            return noimage;
        }

        public void setNoimage(String noimage) {
            this.noimage = noimage;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
