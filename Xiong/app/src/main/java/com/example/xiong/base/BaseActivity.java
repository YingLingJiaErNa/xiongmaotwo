package com.example.xiong.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiong.App;
import com.example.xiong.R;
import com.example.xiong.fragment.FragmentBuilder;
import com.example.xiong.fragment.ZhuFragment;
import com.example.xiong.utils.TUtil;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.M;


/**
 * Created by 赵辉 on 2017/12/18.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public P mPresenter;
    public M mModel;
    private TextView tvtitle;
    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.titlebar);
        App.baseActivity = this;
        frame = (FrameLayout) findViewById(R.id.frame);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView){
            mPresenter.setVM(mModel, this);
        }
        initView();
    }
    public void initBar(String tvtitles) {
        tvtitle.setText(tvtitles);
    }
    public void fra(Class<? extends BaseFragment> f){
        FragmentBuilder.getIntance().init().add(R.id.frame,f).buid();
    }
    @Override
    public void setContentView(View view) {
        frame.removeAllViews();
        frame.addView(view);
//        FragmentBuilder.getIntance().init().add(R.id.frame,ZhuFragment.class).buid();
    }
    protected abstract int getLayoutResID();
    protected abstract void initView();
}
