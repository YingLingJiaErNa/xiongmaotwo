package com.example.xiong.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiong.R;
import com.example.xiong.bean.JingCaiBean;
import com.example.xiong.bean.ShouBean;
import com.example.xiong.tmvp.Callbacks;
import com.example.xiong.tmvp.Model;
import com.example.xiong.utils.GlideImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.data;
import static com.example.xiong.R.id.item2_img;

/**
 * Created by 赵辉 on 2017/12/20.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    Context context;
    ShouBean.DataBean bean;
    Handler handler = new Handler(Looper.getMainLooper());

    public HomeAdapter(Context context, ShouBean.DataBean bean) {
        this.context = context;
        this.bean = bean;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        if (position == 1) {
            return 2;
        }
        if (position == 2) {
            return 3;
        }
        if (position == 3) {
            return 4;
        }
        if (position == 4) {
            return 5;
        }
        if (position == 5) {
            return 6;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item1, null);
            return new ViewHolder1(view);
        }
        if (viewType == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.item2, null);
            return new ViewHolder2(view);
        }
        if (viewType == 3) {
            View view = LayoutInflater.from(context).inflate(R.layout.item3, null);
            return new ViewHolder3(view);
        }
        if (viewType == 4) {
            View view = LayoutInflater.from(context).inflate(R.layout.item4, null);
            return new ViewHolder4(view);
        }
        if (viewType == 5) {
            View view = LayoutInflater.from(context).inflate(R.layout.item5, null);
            return new ViewHolder5(view);
        }
        if (viewType == 6) {
            View view = LayoutInflater.from(context).inflate(R.layout.item6, null);
            return new ViewHolder6(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder1) {
            final List<ShouBean.DataBean.BigImgBean> imaglist = bean.getBigImg();
            final List<String> list = new ArrayList();
            final List<String> list2 = new ArrayList();
            for (ShouBean.DataBean.BigImgBean s : imaglist) {
                list.add(s.getImage());
                list2.add(s.getTitle());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ((ViewHolder1) holder).banner.setImages(list)//添加图片集合或图片url集合
                            .setDelayTime(2000)//设置轮播间隔时间
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                            .setImageLoader(new GlideImage())//加载图片
                            .setBannerTitles(list2)
                            .setIndicatorGravity(BannerConfig.RIGHT)//设置指示器位置
                            .start();
                }
            });
        }
        if (holder instanceof ViewHolder2) {
            ShouBean.DataBean.PandaeyeBean pandaeye = bean.getPandaeye();
            Glide.with(context).load(pandaeye.getPandaeyelogo()).into(((ViewHolder2) holder).item2_img);
            ((ViewHolder2) holder).item2_tv1.setText(pandaeye.getItems().get(0).getBrief());
            ((ViewHolder2) holder).item2_tv11.setText(pandaeye.getItems().get(0).getTitle());
            ((ViewHolder2) holder).item2_tv2.setText(pandaeye.getItems().get(1).getBrief());
            ((ViewHolder2) holder).item2_tv22.setText(pandaeye.getItems().get(1).getTitle());
        }
        if (holder instanceof ViewHolder3) {
            List<ShouBean.DataBean.PandaliveBean.ListBean> list = bean.getPandalive().getList();
            ((ViewHolder3) holder).recycler.setLayoutManager(new GridLayoutManager(context, 3));
            ((ViewHolder3) holder).recycler.setAdapter(new Recycler3Adapter(context, R.layout.item_recycler3, list));
        }
        if (holder instanceof ViewHolder4) {
            String listurl = bean.getCctv().getListurl();
            new Model().getDataFromNet(listurl, new Callbacks() {
                @Override
                public void succ(String s) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<JingCaiBean>() {
                    }.getType();
                    JingCaiBean o = gson.fromJson(s, type);
                    final List<JingCaiBean.ListBean> jingcaiList = o.getList();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((ViewHolder4) holder).recycler4.setLayoutManager(new GridLayoutManager(context, 2));
                            ((ViewHolder4) holder).recycler4.setAdapter(new Recycler4Adapter(context, R.layout.item_recycler3, jingcaiList));
                        }
                    });
                }
            });

        }
        if (holder instanceof ViewHolder5) {
//            final Handler handler2=new Handler(Looper.getMainLooper());
            String listUrl = bean.getList().get(0).getListUrl();
            new Model().getDataFromNet(listUrl, new Callbacks() {
                @Override
                public void succ(String s) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<JingCaiBean>() {
                    }.getType();
                    JingCaiBean o = gson.fromJson(s, type);
                    final List<JingCaiBean.ListBean> jingcaiList = o.getList();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((ViewHolder5) holder).recycler5.setAdapter(new Recycler4Adapter(context, R.layout.item_recycler3, jingcaiList));
                            ((ViewHolder5) holder).recycler5.setLayoutManager(new GridLayoutManager(context, 1));
                        }
                    });
                }
            });

        }
        if (holder instanceof ViewHolder6) {
            final List<ShouBean.DataBean.ChinaliveBean.ListBeanX> listBeanXES = bean.getChinalive().getList();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ((ViewHolder6) holder).recycler6.setAdapter(new Recycler6Adapter(context, R.layout.item_recycler3, listBeanXES));
                    ((ViewHolder6) holder).recycler6.setLayoutManager(new GridLayoutManager(context, 3));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        private Banner banner;

        ViewHolder1(View view) {
            super(view);
            banner = view.findViewById(R.id.banner);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {

        private ImageView item2_img;
        private TextView item2_tv1;
        private TextView item2_tv11;
        private TextView item2_tv2;
        private TextView item2_tv22;

        ViewHolder2(View view) {
            super(view);
            item2_img = view.findViewById(R.id.item2_img);
            item2_tv1 = view.findViewById(R.id.item2_tv1);
            item2_tv11 = view.findViewById(R.id.item2_tv11);
            item2_tv2 = view.findViewById(R.id.item2_tv2);
            item2_tv22 = view.findViewById(R.id.item2_tv22);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        RecyclerView recycler;

        ViewHolder3(View view) {
            super(view);
            recycler = view.findViewById(R.id.recycler);
        }
    }

    static class ViewHolder4 extends RecyclerView.ViewHolder {
        RecyclerView recycler4;

        ViewHolder4(View view) {
            super(view);
            recycler4 = view.findViewById(R.id.recycler4);
        }
    }

    static class ViewHolder5 extends RecyclerView.ViewHolder {
        RecyclerView recycler5;

        ViewHolder5(View view) {
            super(view);
            recycler5 = view.findViewById(R.id.recycler5);
        }
    }

    static class ViewHolder6 extends RecyclerView.ViewHolder {
        RecyclerView recycler6;

        ViewHolder6(View view) {
            super(view);
            recycler6 = view.findViewById(R.id.recycler6);
        }
    }
}
