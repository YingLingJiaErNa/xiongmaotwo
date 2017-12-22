package com.example.xiong.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.bean.ShouBean;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/20.
 */

public class Recycler6Adapter extends BaseAdapter<ShouBean.DataBean.ChinaliveBean.ListBeanX> {
    public Recycler6Adapter(Context context, int layoutId, List<ShouBean.DataBean.ChinaliveBean.ListBeanX> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ShouBean.DataBean.ChinaliveBean.ListBeanX listBeanX) {
        holder.setText(R.id.item_recycler3_title,listBeanX.getTitle());
        ImageView item_recycler3_img = holder.itemView.findViewById(R.id.item_recycler3_img);
        Glide.with(context).load(listBeanX.getImage()).into(item_recycler3_img);
    }
}
