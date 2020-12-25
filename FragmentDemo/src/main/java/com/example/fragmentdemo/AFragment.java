package com.example.fragmentdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AFragment extends BaseFragment {

    public static AFragment newInstance() {
        AFragment fragment = new AFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.a_fragment_layout;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("sss", "AFragment initView");
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Log.e("sss", "AFragment onVisible");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        Log.e("sss", "AFragment onInvisible");
    }

    @Override
    protected void deInitView() {
        Log.e("sss", "AFragment deInitView");
    }

    @Override
    protected void initData() {
        Log.e("sss", "AFragment initData");
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}