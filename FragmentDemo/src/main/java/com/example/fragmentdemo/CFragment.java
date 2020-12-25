package com.example.fragmentdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CFragment extends BaseFragment {
    public static CFragment newInstance() {
        CFragment fragment = new CFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.c_fragment_layout;

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("sss", "CFragment initView");
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Log.e("sss", "CFragment onVisible");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        Log.e("sss", "CFragment onInvisible");
    }

    @Override
    protected void deInitView() {
        Log.e("sss", "CFragment deInitView");
    }

    @Override
    protected void initData() {
        Log.e("sss", "CFragment initData");
    }


    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}