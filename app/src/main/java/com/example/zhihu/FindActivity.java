package com.example.zhihu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

public class FindActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mContext = this;
        bottomNavigationView = new Init().initControl(mContext,FindActivity.this);
    }
}
