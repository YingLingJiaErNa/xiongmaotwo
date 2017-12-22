package com.example.xiong.adapter;

import android.content.Context;
import android.view.View;
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

public class Recycler3Adapter extends BaseAdapter<ShouBean.DataBean.PandaliveBean.ListBean> {
    public Recycler3Adapter(Context context, int layoutId, List<ShouBean.DataBean.PandaliveBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ShouBean.DataBean.PandaliveBean.ListBean listBean) {
        holder.setText(R.id.item_recycler3_title,listBean.getTitle());
        ImageView item_recycler3_img = holder.itemView.findViewById(R.id.item_recycler3_img);
        Glide.with(context).load(listBean.getImage()).into(item_recycler3_img);
    }
}
