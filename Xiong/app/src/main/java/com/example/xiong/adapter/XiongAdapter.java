package com.example.xiong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/20.
 */

public class XiongAdapter extends FragmentPagerAdapter {
    List<String> list;
    List<Fragment> list2;
    public XiongAdapter(FragmentManager fm, List<String> list, List<Fragment> list2) {
        super(fm);
        this.list = list;
        this.list2 = list2;
    }

    @Override
    public Fragment getItem(int position) {
        return list2.get(position);
    }

    @Override
    public int getCount() {
        return list2.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
