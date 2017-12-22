package com.example.xiong.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.bean.MaoBean2;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/21.
 */

public class MaoAdapter2 extends BaseAdapter<MaoBean2.ListBean> {
    public MaoAdapter2(Context context, int layoutId, List<MaoBean2.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, MaoBean2.ListBean listBean) {
        holder.setText(R.id.tv2,listBean.getTitle());
        ImageView iv = holder.itemView.findViewById(R.id.iv);
        Glide.with(context).load(listBean.getPicurl()).into(iv);
    }
}

