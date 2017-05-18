package com.example.bottom.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.example.bottom.R;
import com.example.bottom.widget.bottomnavigation.BottomNavigationBar;
import com.example.bottom.widget.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends FragmentActivity {

    private ViewPager viewpager;
    private BottomNavigationBar bottom_navigation_bar;
    //数据源的集合
    private List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(); //实例化数据
        initData();   //添加数据
        initEvent();    //设置点击事件
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        bottom_navigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
    }

    private void initData() {
        //给ViewPager设置数据
        list.add(new Fragment1());
        list.add(new Fragment2());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);

        //要先设计模式后再添加图标！
        //设置按钮模式  MODE_FIXED表示固定   MODE_SHIFTING表示转移
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_SHIFTING);
        //设置背景风格       BACKGROUND_STYLE_STATIC表示静态的  ，BACKGROUND_STYLE_RIPPLE表示涟漪的，也就是可以变化的 ，跟随setActiveColorResource里面d颜色变化
        bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        //添加并设置图标、图标的颜色和文字
        bottom_navigation_bar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Books").setActiveColorResource(R.color.blue))
                .initialise();

        //设置选中第一个添加的按钮
        bottom_navigation_bar.selectTab(0, false);
        //显示ViewPager中添加的第一个页面
        viewpager.setCurrentItem(0);


    }

    private void initEvent() {
        //监听ViewPager的滑动
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {  //页面滑动后，按钮改变
                bottom_navigation_bar.selectTab(i, false);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        //监听下面按钮的点击
        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewpager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });

    }


}