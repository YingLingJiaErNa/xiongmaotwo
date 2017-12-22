package com.example.xiong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiong.R;
import com.example.xiong.adapter.MyXmzb_Zb_DuoAdapter;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.bean.XiongBean2;
import com.example.xiong.bean.Xmzb_Zb_DuoBean;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Xmzb_DuoFragment extends BaseFragment<Presenter, Model> implements Contract.View {


    private RecyclerView xmzb_duo_recy;
    private String string;

    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_xmzb__duo;
    }

    @Override
    protected void initView(View view) {
        xmzb_duo_recy = (RecyclerView) view.findViewById(R.id.xmzb_duo_recy);
        Bundle arguments = getArguments();
        string = arguments.getString("url");
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresemter1.getDataFromModel(string);
    }

    @Override
    public void show(String ss) {
        Gson gson = new Gson();
        Type type = new TypeToken<Xmzb_Zb_DuoBean>(){}.getType();
        Xmzb_Zb_DuoBean o = gson.fromJson(ss, type);
        List<Xmzb_Zb_DuoBean.ListBean> list = o.getList();
        xmzb_duo_recy.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        MyXmzb_Zb_DuoAdapter myXmzb_zb_duoAdapter = new MyXmzb_Zb_DuoAdapter(getActivity(),R.layout.myrecy_item,list);
        xmzb_duo_recy.setAdapter(myXmzb_zb_duoAdapter);
    }

}
