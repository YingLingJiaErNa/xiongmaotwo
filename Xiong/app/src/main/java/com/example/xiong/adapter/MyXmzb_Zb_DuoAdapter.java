package com.example.xiong.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.bean.Xmzb_Zb_DuoBean;

import java.util.List;

/**
 * Created by 赵辉 on 2017/12/21.
 */

public class MyXmzb_Zb_DuoAdapter extends BaseAdapter<Xmzb_Zb_DuoBean.ListBean> {
    public MyXmzb_Zb_DuoAdapter(Context context, int layoutId, List<Xmzb_Zb_DuoBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Xmzb_Zb_DuoBean.ListBean listBean) {
        holder.setText(R.id.xc_te,listBean.getTitle());
        ImageView xc_image = holder.itemView.findViewById(R.id.xc_image);
        Glide.with(context).load(listBean.getImage()).into(xc_image);
    }
}
