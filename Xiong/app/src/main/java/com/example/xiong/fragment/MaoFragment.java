package com.example.xiong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.adapter.MaoAdapter;
import com.example.xiong.adapter.MaoAdapter2;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.bean.GunBean;
import com.example.xiong.bean.MaoBean;
import com.example.xiong.bean.MaoBean2;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.id.list;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaoFragment extends BaseFragment<Presenter, Model> implements Contract.View {
    private PullToRefreshRecyclerView pull;
    private ImageView iv;

    @Override
    public void onStart() {
        super.onStart();
        mPresemter1.getDataFromModel("http://www.ipanda.com/kehuduan/news/index.json");
    }

    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_mao;
    }

    @Override
    protected void initView(View view) {
        pull = (PullToRefreshRecyclerView) view.findViewById(R.id.pull);
        View v = View.inflate(getActivity(),R.layout.gun,null);
        pull.addHeaderView(v);
        iv = v.findViewById(R.id.iv);
    }

    @Override
    public void show(String ss) {
        Gson gson = new Gson();
        Type type = new TypeToken<MaoBean>() {
        }.getType();
        MaoBean o = gson.fromJson(ss, type);
        List<MaoBean.DataBean.BigImgBean> bigImg = o.getData().getBigImg();
//        final MaoAdapter maoAdapter = new MaoAdapter(getActivity(),R.layout.gunitem,bigImg);
//        pull.setAdapter(maoAdapter);
//        pull.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        Glide.with(getActivity()).load(bigImg.get(0).getImage()).into(iv);
//        mPresemter1.getDataFromModel(o.getData().getListurl());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(o.getData().getListurl()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson1 = new Gson();
                Type type1 = new TypeToken<MaoBean2>(){}.getType();
                MaoBean2 o1 = gson1.fromJson(string, type1);
                List<MaoBean2.ListBean> list = o1.getList();
                final MaoAdapter2 maoAdapter2 = new MaoAdapter2(getActivity(),R.layout.gunitem,list);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                pull.setAdapter(maoAdapter2);
                pull.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

                    }
                });
            }
        });
    }
}
