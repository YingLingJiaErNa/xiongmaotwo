package com.example.xiong.base;

/**
 * Created by 赵辉 on 2017/12/18.
 */
public class BasePresenter<M, V> {
    public M myModel;
    public V myView;
    public void setVM(M myModel, V myView) {
        this.myModel = myModel;
        this.myView= myView;
    }
}
