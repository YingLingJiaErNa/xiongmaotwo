package com.example.xiong.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.adapter.XiongAdapter;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.base.BaseModel;
import com.example.xiong.base.BasePresenter;
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
public class ZhiBoFragment extends BaseFragment<Presenter, Model> implements Contract.View {


    private ImageView xmzb_zb_st;
    private TextView xmzb_zb_bt;
    private CheckBox xmzb_zb_sx;
    private LinearLayout linearLayout2;
    private TextView xmzb_xy;
    private TabLayout zblive_tab;
    private ViewPager zblive_pager;
    private boolean boo = true;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> mlist = new ArrayList<>();
    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_zhi_bo;
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        mPresemter1.getDataFromModel(url);
        xmzb_zb_st = (ImageView) view.findViewById(R.id.xmzb_zb_st);
        xmzb_zb_bt = (TextView) view.findViewById(R.id.xmzb_zb_bt);
        xmzb_zb_sx = (CheckBox) view.findViewById(R.id.xmzb_zb_sx);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        xmzb_xy = (TextView) view.findViewById(R.id.xmzb_xy);
        zblive_tab = (TabLayout) view.findViewById(R.id.zblive_tab);
        zblive_pager = (ViewPager) view.findViewById(R.id.zblive_pager);
        xmzb_zb_sx.setButtonDrawable(R.drawable.live_china_detail_down);
        xmzb_zb_sx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    xmzb_xy.setVisibility(View.GONE);
                    xmzb_zb_sx.setButtonDrawable(R.drawable.live_china_detail_down);
                } else {
                    xmzb_zb_sx.setButtonDrawable(R.drawable.live_china_detail_up);
                     xmzb_xy.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void show(String ss) {
        Gson gson = new Gson();
        Type type = new TypeToken<XiongBean2>(){}.getType();
        XiongBean2 o = gson.fromJson(ss, type);
        List<XiongBean2.LiveBean> live = o.getLive();
        xmzb_zb_bt.setText(live.get(0).getTitle());
        xmzb_xy.setText(live.get(0).getBrief());
        Glide.with(getActivity()).load(live.get(0).getImage()).into(xmzb_zb_st);
        List<XiongBean2.BookmarkBean.MultipleBean> multiple = o.getBookmark().getMultiple();
        List<XiongBean2.BookmarkBean.WatchTalkBean> watchTalk = o.getBookmark().getWatchTalk();
        if (boo) {
            list.add(multiple.get(0).getTitle());
            list.add(watchTalk.get(0).getTitle());
            Xmzb_DuoFragment xmzb_duoFragment = new Xmzb_DuoFragment();
            Bundle bundle = new Bundle();
            String url = multiple.get(0).getUrl();
            bundle.putString("url",url);
            xmzb_duoFragment.setArguments(bundle);
            mlist.add(xmzb_duoFragment);
            mlist.add(new Xmzb_BianFragment());
            boo = false;
        }
        XiongAdapter xiongAdapter = new XiongAdapter(getActivity().getSupportFragmentManager(),list,mlist);
        zblive_pager.setAdapter(xiongAdapter);
        zblive_tab.setupWithViewPager(zblive_pager);
    }

}
