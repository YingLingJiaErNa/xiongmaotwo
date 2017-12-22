package com.example.xiong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiong.App;
import com.example.xiong.Dan;
import com.example.xiong.Main2Activity;
import com.example.xiong.R;
import com.example.xiong.bean.ShouBean;
import com.example.xiong.bean.ZongBean;
import com.example.xiong.tmvp.Callbacks;
import com.example.xiong.tmvp.Contract;
import com.example.xiong.tmvp.Model;
import com.example.xiong.tmvp.Presenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.id.list;
import static com.example.xiong.R.id.pull;


/**
 * Created by 赵辉 on 2017/12/19.
 */

public class RecAdapter extends RecyclerView.Adapter {
    Context context;
    List<ZongBean.TabBean> tab;

    public RecAdapter(Context context, List<ZongBean.TabBean> tab) {
        this.context = context;
        this.tab = tab;
    }

    @Override
    public int getItemViewType(int position) {
        if (tab.get(position).getTitle().equals("首页")) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        } else if (position == 4) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
//        View view = new View(context,R.layout.homeitem1,null);
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.homeitem0, null, false);
            viewHolder = new ViewHolder(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.homeitem2, null, false);
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            String url = tab.get(position).getUrl();
            Model model = new Model();
            model.getDataFromNet(url, new Callbacks() {
                @Override
                public void succ(String s) {
                    Log.e("SSS", s);
                    Gson gson = new Gson();
                    Type type = new TypeToken<ShouBean>() {
                    }.getType();
                    ShouBean o = gson.fromJson(s, type);
                    List<ShouBean.DataBean> data = new ArrayList<ShouBean.DataBean>();
                    data.add(o.getData());
//                    ((ViewHolder) holder).rvhomeitem0.setAdapter(pullAdapter0);
                    ((ViewHolder) holder).rvhomeitem0.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//                    List<ZongBean.DataBean.AreaBean> area = new ArrayList<>();
//                    area.add(list.get(position).getArea());
//                    ((ViewHolder2) holder).rvhomeitem2.setAdapter(pullAdapter2);
//                    ((ViewHolder2) holder).rvhomeitem2.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return tab.size();
    }

//    @Override
//    public void show(String ss) {
//        Gson gson = new Gson();
//        Type type = new TypeToken<ShouBean>() {
//        }.getType();
//        final ShouBean o = gson.fromJson(ss, type);
//        App.baseActivity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                ShouBean.DataBean.PandaeyeBean pandaeye = o.getData().getPandaeye();
//
//            }
//        });
////        context.runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                List<ZongBean.TabBean> tab = o.getTab();
////                RecAdapter recAdapter = new RecAdapter(Main2Activity.this, tab);
////                pull.setAdapter(recAdapter);
////                pull.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
////            }
////        });
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvhomeitem0;

        public ViewHolder(View itemView) {
            super(itemView);
            rvhomeitem0 = itemView.findViewById(R.id.rvhomeitem0);
        }
    }
//
//    public class ViewHolder1 extends RecyclerView.ViewHolder {
//        private Banner banner;
//
//        public ViewHolder1(View itemView) {
//            super(itemView);
//            banner = itemView.findViewById(R.id.banner);
//        }
//    }
//
//    public class ViewHolder2 extends RecyclerView.ViewHolder {
//        private ImageView ivhomeitem3;
//        private TextView tvhomeitem3;
//        private RecyclerView rvhomeitem2;
//
//        public ViewHolder2(View itemView) {
//            super(itemView);
////            ivhomeitem3 = itemView.findViewById(R.id.ivhomeitem3);
////            tvhomeitem3 = itemView.findViewById(R.id.tvhomeitem3);
//            rvhomeitem2 = itemView.findViewById(R.id.rvhomeitem2);
//
//        }
//    }
}
