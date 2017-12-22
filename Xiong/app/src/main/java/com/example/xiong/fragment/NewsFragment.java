package com.example.xiong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiong.R;
import com.example.xiong.base.BaseFragment;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment<Presenter, Model> implements Contract.View {

    private TextView asdf;

    @Override
    protected int getLayoutIdFragment() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View view) {
        asdf = (TextView) view.findViewById(R.id.asdf);
        Bundle bundle = getArguments();
        String index = bundle.getString("murl");
        asdf.setText(index);
    }

    @Override
    public void show(String ss) {

    }
}
