package com.example.xiong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiong.App;
import com.example.xiong.utils.TUtil;

/**
 * Created by 赵辉 on 2017/12/19.
 */

public abstract class BaseFragment<P extends BasePresenter,M extends BaseModel> extends Fragment {
    public P mPresemter1;
    public M mModel1;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.baseFragment = this;
        view = inflater.inflate(getLayoutIdFragment(), container, false);
        initData();
        return view;
    }
    public void initData(){
        mPresemter1 = TUtil.getT(this,0);
        mModel1 = TUtil.getT(this,1);
        if (this instanceof BaseView) {
            mPresemter1.setVM(mModel1,this);
        }
        initView(view);
    };
    protected abstract int getLayoutIdFragment();
    protected abstract void initView(View view);
}
