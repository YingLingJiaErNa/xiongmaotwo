package com.example.xiong.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.xiong.R;
import com.example.xiong.bean.MaoBean;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/21.
 */

public class MaoAdapter extends BaseAdapter<MaoBean.DataBean.BigImgBean> {
    public MaoAdapter(Context context, int layoutId, List<MaoBean.DataBean.BigImgBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, MaoBean.DataBean.BigImgBean bigImgBean) {
        holder.setText(R.id.tv,bigImgBean.getTitle());
//        holder.setText(R.id.tv2,bigImgBean.getTitle());

    }
}
