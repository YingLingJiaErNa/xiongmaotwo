package com.example.xiong.tmvp;

import com.example.xiong.App;
import com.example.xiong.bean.ZongBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by TMVPHelper on 2017/12/18
 */
public class Presenter extends Contract.Presenter {

    @Override
    public void getDataFromModel(String url) {
        myModel.getDataFromNet(url, new Callbacks() {
            @Override
            public void succ(final String s) {
                App.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myView.show(s);
                    }
                });
            }
        });
    }
}