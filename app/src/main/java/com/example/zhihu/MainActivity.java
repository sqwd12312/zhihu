package com.example.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView bottomNavigationView;

    private TabLayout mTabLayout;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initControl();
        //默认显示“推荐”fragment
        replaceFragment(new RecommendFragment());
        mContext = this;
        //初始化底部导航栏
        bottomNavigationView = new Init().initControl(mContext,MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //m默认选中"首页"导航单元
        bottomNavigationView.selectTab(0);
    }

    //初始化控件
    private void initControl(){
        //设置三个tab
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("关注"));
        mTabLayout.addTab(mTabLayout.newTab().setText("推荐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("热榜"));
        //默认选中"推荐"tab
        mTabLayout.getTabAt(1).select();
        //设置mTabLayout点击监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("关注")) {
                    replaceFragment(new FollowFragment());
                }else if (tab.getText().equals("推荐")){
                    replaceFragment(new RecommendFragment());
                }else if (tab.getText().equals("热榜")){
                    replaceFragment(new HotListFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //替换fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment,fragment);
        transaction.commit();
    }
}



















