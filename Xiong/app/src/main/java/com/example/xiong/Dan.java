package com.example.xiong;

import com.example.xiong.tmvp.Callbacks;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 赵辉 on 2017/12/20.
 */

public class Dan {
    private static Dan dan;
    private OkHttpClient client;
    public Dan() {
        client = new OkHttpClient.Builder().build();
    }
    private static Dan getIntance(String url){
        if (dan == null) {
            synchronized (Dan.class){
                dan = new Dan();
            }
        }
        return dan;
    }
    public void qing(String url, final Callbacks callbacks){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callbacks.succ(response.body().string());
            }
        });
    }

}
