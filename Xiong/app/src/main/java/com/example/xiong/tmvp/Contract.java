package com.example.xiong.tmvp;

import com.example.xiong.App;
import com.example.xiong.base.BaseModel;
import com.example.xiong.base.BasePresenter;
import com.example.xiong.base.BaseView;
import com.example.xiong.bean.ZongBean;

import java.util.List;

import okhttp3.Callback;

/**
 * Created by 赵辉 on 2017/12/18.
 */

public interface Contract {

    interface View extends BaseView {
        void show(String ss);
//        void tab(List<ZongBean.TabBean> tab);
    }

    interface Model extends BaseModel {
        void getDataFromNet(String url, Callbacks callbacks);
    }

    abstract static class Presenter extends BasePresenter<Model, View> {
          abstract void getDataFromModel(String url);
    }
}