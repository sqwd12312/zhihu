package com.example.zhihu;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button recommendBtn;

    private Button followBtn;

    private Button hotListBtn;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initControl();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }
        replaceFragment(new RecommendFragment());
    }

    //初始化控件
    private void initControl(){
        recommendBtn = (Button)findViewById(R.id.recommend_btn);
        followBtn = (Button)findViewById(R.id.follow_btn);
        hotListBtn = (Button)findViewById(R.id.hot_list_btn);
        recommendBtn.setOnClickListener(this);
        followBtn.setOnClickListener(this);
        hotListBtn.setOnClickListener(this);
        //初始化底部导航栏
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", ContextCompat.getColor(this, R.color.red), R.drawable.home_page);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("会员", ContextCompat.getColor(this, R.color.orange), R.drawable.member);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("发现", ContextCompat.getColor(this, R.color.white), R.drawable.find);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("消息", ContextCompat.getColor(this, R.color.colorAccent), R.drawable.news);
        BottomNavigationItem bottomNavigationItem4 = new BottomNavigationItem
                ("我的", ContextCompat.getColor(this, R.color.black), R.drawable.my);
        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);
        bottomNavigationView.addTab(bottomNavigationItem4);
        //禁用背景变化
        bottomNavigationView.isColoredBackground(false);
//        bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.orange);
        //导航栏监听
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                Toast.makeText(MainActivity.this, "Item " +index +" clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommend_btn:
                replaceFragment(new RecommendFragment());
                break;
            case R.id.follow_btn:
                replaceFragment(new FollowFragment());
                break;
            case R.id.hot_list_btn:
                replaceFragment(new HotListFragment());
            default:
                break;
        }
    }

    //替换fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment,fragment);
        transaction.commit();
    }
}



















