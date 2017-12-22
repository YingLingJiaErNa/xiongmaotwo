package com.example.xiong.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.bean.XiongBean;
import com.example.xiong.bean.XiongBean2;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/20.
 */

public class Xiong1Adapter extends BaseAdapter<XiongBean2.LiveBean> {

    public Xiong1Adapter(Context context, int layoutId, List<XiongBean2.LiveBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, XiongBean2.LiveBean liveBean) {
        holder.setText(R.id.item_recycler3_title,liveBean.getTitle());
        ImageView item_recycler3_img = holder.itemView.findViewById(R.id.item_recycler3_img);
        Glide.with(context).load(liveBean.getImage()).into(item_recycler3_img);
    }
}
