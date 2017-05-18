#BottomNavigation使用详解
前几天看到GitHub中比较新的一个底部按钮的样式，看起来时比较受欢迎的，但是里面的代码没有详细介绍，我这里跟大家详细介绍下，并且原本项目中需要高版本运行，适合在Studio运行，我这里是兼容低版本的，适合非Studio中编译，但是也是要android5.0的SDK，并且导入v4和desige包。
GitHub中：
![1](http://i.imgur.com/AKy8sps.png)
效果：
![2](http://i.imgur.com/3lDto9U.gif)
我的项目中的程序效果：
![3](http://i.imgur.com/Nu4m9kq.gif)
我的项目是有两个Activity组成的，第一个Activity使用ViewPager和Fragment联动，当显示第二个碎片时，单击碎片二，可以跳转到整体显示的效果页面。
其中第一个Activity的代码是比较容易理解和实用的。
总的效果讲解：
![4](http://i.imgur.com/9bPeKGy.png)

#讲解和使用：
主要是调用BottomNavigationBar里面的方法，一般是在布局中写死，并用代码添加多个按钮，并设计显示的效果。
##(一)布局
```
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="vertical"
              xmlns:app="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
    <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            />
    <com.example.bottom.widget.bottomnavigation.BottomNavigationBar
            app:bnbInactiveColor="@color/orange"
            android:layout_gravity="bottom"
            android:id="@+id/bottom_navigation_bar"
            android:layout_width="match_parent"
            android:layout_height="50dp"/>
</LinearLayout>
```

##（二）代码讲解
```
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
        //设置背景风格      
	// BACKGROUND_STYLE_STATIC表示静态的  ，
	//BACKGROUND_STYLE_RIPPLE表示涟漪的，也就是可以变化的 ，跟随setActiveColorResource里面的颜色变化
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
		
	//联动显示
    private void initEvent() {
        //监听ViewPager的滑动
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) { 
				 //页面滑动后，按钮改变
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
                viewpager.setCurrentItem(position);//改变ViewPager的显示
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
```
当然还有一些其他方法，你可以点开类看看里面的公开方法，也可以看第二个Activity中调用到的方法。

其实用起来并不难嘛，如果是非Studio用户，建议使用我的项目中的代码，原作者中还是用到了desige包下的FloatingActionButton，这个就要求版本很高了，否则会发生系统资源文件缺少的错误。我这里导入了自定义的FloatingActionButton，功能比系统的要多一点，但是后面发现其实这个项目中FloatingActionButton没有什么具体功能效果，后面右注释掉布局那部分，想用的可以开出来显示，但是没啥后续效果（原本中的GitHub中项目也是一样的）！

有需要的可以下载我的源码查看，jar包也是在里面的。那些自定义的类和资源文件还是要拷贝的！

#共勉：只要方向是对的，再多困难的路也要走下去。
![8](http://i.imgur.com/N101KoQ.jpg)