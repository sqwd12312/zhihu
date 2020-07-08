package com.example.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

public class MemberActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        mContext = this;
        bottomNavigationView = new Init().initControl(mContext,MemberActivity.this);
    }

}
