package com.example.xiong;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.xiong.adapter.HomeAdapter;
import com.example.xiong.base.BaseActivity;
import com.example.xiong.bean.ShouBean;
import com.example.xiong.fragment.FragmentBuilder;
import com.example.xiong.fragment.GunFragment;
import com.example.xiong.fragment.MaoFragment;
import com.example.xiong.fragment.XiongFragment;
import com.example.xiong.fragment.ZhuFragment;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends BaseActivity<Presenter, Model> implements Contract.View, View.OnClickListener {

    private View view;
    private RadioButton shou;
    private RadioButton xiong;
    private RadioButton gun;
    private RadioButton mao;
    private RadioButton zhi;
    private PullToRefreshRecyclerView pull;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        fra(ZhuFragment.class);
        shou = (RadioButton) findViewById(R.id.shou);
        shou.setOnClickListener(this);
        xiong = (RadioButton) findViewById(R.id.xiong);
        xiong.setOnClickListener(this);
        gun = (RadioButton) findViewById(R.id.gun);
        gun.setOnClickListener(this);
        mao = (RadioButton) findViewById(R.id.mao);
        mao.setOnClickListener(this);
        zhi = (RadioButton) findViewById(R.id.zhi);
        zhi.setOnClickListener(this);
//        mPresenter.getDataFromModel("http://www.ipanda.com/kehuduan/shouye/index.json");
    }

    @Override
    public void show(String ss) {
        Log.e("AAA",ss);
        Gson gson = new Gson();
        Type type = new TypeToken<ShouBean>() {
        }.getType();
        ShouBean o = gson.fromJson(ss, type);
        List<ShouBean.DataBean> data = new ArrayList<>();
        data.add(o.getData());
//        final HomeAdapter homeAdapter = new HomeAdapter(this,o.getData());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                pull.setAdapter(homeAdapter);
//                pull.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shou:
                initBar("首页");
                fra(ZhuFragment.class);
                break;
            case R.id.xiong:
                initBar("熊猫直播");
                fra(XiongFragment.class);
                break;
            case R.id.gun:
                initBar("滚滚视频");
                fra(GunFragment.class);
                break;
            case R.id.mao:
                initBar("熊猫播报");
                fra(MaoFragment.class);
                break;
            case R.id.zhi:
                initBar("直播中国");
                fra(ZhuFragment.class);
                break;
        }
    }
}
