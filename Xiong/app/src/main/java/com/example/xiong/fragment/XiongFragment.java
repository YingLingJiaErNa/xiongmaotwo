package com.example.xiong.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiong.R;
import com.example.xiong.adapter.XiongAdapter;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.bean.XiongBean;
import com.example.xiong.bean.XiongBean2;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class XiongFragment extends BaseFragment<Presenter, Model> implements Contract.View {


    private TabLayout tab;
    private ViewPager pager;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> mlist = new ArrayList<>();
    private List<XiongBean.TablistBean> tablist;

    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_xiong;
    }

    @Override
    protected void initView(View view) {
        mPresemter1.getDataFromModel("http://www.ipanda.com/kehuduan/PAGE14501772263221982/index.json");
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager = (ViewPager) view.findViewById(R.id.pager);
    }

    @Override
    public void show(String ss) {
        Gson gson = new Gson();
        Type type = new TypeToken<XiongBean>() {
        }.getType();
        XiongBean o = gson.fromJson(ss, type);
        tablist = o.getTablist();
        for (int i = 0; i < tablist.size(); i++) {
            list.add(tablist.get(i).getTitle());
        }
        initFragment();
    }

    private void initFragment() {
        mlist.clear();
        ZhiBoFragment zhiBoFragment = new ZhiBoFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("url", tablist.get(0).getUrl());
        zhiBoFragment.setArguments(bundle1);

        mlist.add(zhiBoFragment);
        for (int i = 1; i < tablist.size(); i++) {
            NewsFragment newfragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("murl", tablist.get(i).getTitle());
            newfragment.setArguments(bundle);
            mlist.add(newfragment);
        }
        final XiongAdapter mAdapetr = new XiongAdapter(getActivity().getSupportFragmentManager(), list, mlist);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pager.setAdapter(mAdapetr);
                tab.setupWithViewPager(pager);
            }
        });
    }
}
