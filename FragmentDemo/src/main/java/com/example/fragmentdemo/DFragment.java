package com.example.fragmentdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DFragment extends BaseFragment {
    public static DFragment newInstance() {
        DFragment fragment = new DFragment();
        return fragment;
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.d_fragment_layout;

    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("sss", "DFragment initView");
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Log.e("sss", "DFragment onVisible");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        Log.e("sss", "DFragment onInvisible");
    }

    @Override
    protected void deInitView() {
        Log.e("sss", "DFragment deInitView");
    }

    @Override
    protected void initData() {
        Log.e("sss", "DFragment initData");
    }


    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}