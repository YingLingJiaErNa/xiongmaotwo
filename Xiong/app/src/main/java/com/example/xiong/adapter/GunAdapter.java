package com.example.xiong.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.bean.GunBean;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/21.
 */

public class GunAdapter extends BaseAdapter<GunBean.ListBean> {
    public GunAdapter(Context context, int layoutId, List<GunBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, GunBean.ListBean listBean) {
        holder.setText(R.id.tv,listBean.getTitle());
        holder.setText(R.id.tv2,listBean.getBrief());
        ImageView iv = holder.itemView.findViewById(R.id.iv);
        Glide.with(context).load(listBean.getImage()).into(iv);
    }
}
