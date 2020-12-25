package com.example.fragmentdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BFragment extends BaseFragment {
    public static BFragment newInstance() {
        BFragment fragment = new BFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.b_fragment_layout;

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("sss", "BFragment initView");
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Log.e("sss", "BFragment onVisible");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        Log.e("sss", "BFragment onInvisible");
    }

    @Override
    protected void deInitView() {
        Log.e("sss", "BFragment deInitView");
    }

    @Override
    protected void initData() {
        Log.e("sss", "BFragment initData");
    }


    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}