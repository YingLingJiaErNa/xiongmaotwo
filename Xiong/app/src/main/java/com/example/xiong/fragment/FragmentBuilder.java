package com.example.xiong.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.xiong.App;
import com.example.xiong.base.BaseFragment;

import static android.R.attr.fragment;


/**
 * Created by 赵辉 on 2017/12/19.
 */

public class FragmentBuilder {
    private static FragmentBuilder fragmentBuilder;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment fragment;

    public FragmentBuilder() {
    }

    public static FragmentBuilder getIntance() {
        if (fragmentBuilder == null) {
            synchronized (FragmentBuilder.class) {
                fragmentBuilder = new FragmentBuilder();
            }
        }
        return fragmentBuilder;
    }

    public FragmentBuilder init() {
        manager = App.baseActivity.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        return this;
    }

    public FragmentBuilder add(int containerId, Class<? extends BaseFragment> fragmentClass) {
        String simpleName = fragmentClass.getSimpleName();
        Log.e("FragmentBuilder", simpleName);
        fragment = manager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                transaction.add(containerId, fragment, simpleName);
                transaction.addToBackStack(simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (App.baseFragment != null) {
            transaction.hide(App.baseFragment);
        }
        transaction.show(fragment);
        return this;
    }

    public void buid() {
        App.baseFragment = (BaseFragment) fragment;
        transaction.commit();
    }
}
