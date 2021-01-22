package com.example.fragmentdemo;

import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        fragmentList.add(AFragment.newInstance());
        fragmentList.add(BFragment.newInstance());
        fragmentList.add(CFragment.newInstance());
        fragmentList.add(DFragment.newInstance());

        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.e("sss", "onPageSelected " + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tableLayout.getTabAt(0).setText("AFragment");
        tableLayout.getTabAt(1).setText("BFragment");
        tableLayout.getTabAt(2).setText("CFragment");
        tableLayout.getTabAt(3).setText("DFragment");
        tableLayout.setupWithViewPager(viewPager);

        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("sss", "onTabSelected " + tab.getPosition());
                currentItem = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
