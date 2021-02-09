package com.example.fragmentdemo;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout tableLayout;
    private ViewPager2 viewPager;
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

        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(this, fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                Log.e("sss", "onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e("sss", "onPageSelected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
//                Log.e("sss", "onPageScrollStateChanged " + state);
            }
        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tableLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                Log.e("sss", "onConfigureTab = " + tab + " position = " + position);

                tab.setText("tab " + position);
            }
        });

        tabLayoutMediator.attach();
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                Log.e("sss", "onPageSelected " + i);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//        tableLayout.setupWithViewPager(viewPager);
        tableLayout.getTabAt(0).setText("AFragment");
        tableLayout.getTabAt(1).setText("BFragment");
        tableLayout.getTabAt(2).setText("CFragment");
        tableLayout.getTabAt(3).setText("DFragment");
//        viewPager.setOffscreenPageLimit(-1);

//        viewPager.setCurrentItem(3);
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
