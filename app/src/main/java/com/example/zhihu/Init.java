package com.example.zhihu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

/**
 * Created by qny on 2020/7/8.
 */

public class Init {

    private  BottomNavigationView bottomNavigationView;

    //初始化底部导航栏
    public  BottomNavigationView initControl(final Context context, final Activity activity){
        //初始化底部导航栏
        bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottomNavigation);
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", ContextCompat.getColor(context, R.color.red), R.drawable.home_page);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("会员", ContextCompat.getColor(context, R.color.orange), R.drawable.member);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("发现", ContextCompat.getColor(context, R.color.white), R.drawable.find);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("消息", ContextCompat.getColor(context, R.color.colorAccent), R.drawable.news);
        BottomNavigationItem bottomNavigationItem4 = new BottomNavigationItem
                ("我的", ContextCompat.getColor(context, R.color.black), R.drawable.my);
        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);
        bottomNavigationView.addTab(bottomNavigationItem4);
        //禁用背景变化
        bottomNavigationView.isColoredBackground(false);
//        bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.orange);
        //底部导航栏监听
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                if (index == 0){
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    activity.finish();
                }else if (index == 1){
                    Intent intent = new Intent(context,MemberActivity.class);
                    context.startActivity(intent);
                    activity.finish();
                }else if (index == 2){
                    Intent intent = new Intent(context,FindActivity.class);
                    context.startActivity(intent);
                    activity.finish();
                }else if (index == 3){
                    Intent intent = new Intent(context,MessageActivity.class);
                    context.startActivity(intent);
                    activity.finish();
                }else if (index == 4){
                    Intent intent = new Intent(context,MyInformationActivity.class);
                    context.startActivity(intent);
                    activity.finish();
                }
            }
        });
        return bottomNavigationView;
    }
}
