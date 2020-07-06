//package com.example.zhihu;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
///**
// * Created by qny on 2020/6/23.
// */
//
//public class BottomLayout extends LinearLayout implements View.OnClickListener{
//
//    private Button homePage;
//    private Button member;
//    private Button find;
//    private Button news;
//    private Button my;
//    private TextView homePageTV;
//    private TextView memberTV;
//    private TextView findTV;
//    private TextView newsTV;
//    private TextView myTV;
//
//
//    public BottomLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.bottom_navigation,this);
//        homePage = (Button) findViewById(R.id.home_page);
//        member = (Button) findViewById(R.id.member);
//        find = (Button) findViewById(R.id.find);
//        news = (Button) findViewById(R.id.news);
//        my = (Button) findViewById(R.id.my);
//        homePageTV = (TextView)findViewById(R.id.home_page_tv);
//        memberTV = (TextView)findViewById(R.id.member_tv);
//        findTV = (TextView)findViewById(R.id.find_tv);
//        newsTV = (TextView)findViewById(R.id.news_tv);
//        myTV = (TextView)findViewById(R.id.my_tv);
//
//
//        homePage.setOnClickListener(this);
//        member.setOnClickListener(this);
//        find.setOnClickListener(this);
//        news.setOnClickListener(this);
//        my.setOnClickListener(this);
//        homePageTV.setOnClickListener(this);
//        memberTV.setOnClickListener(this);
//        findTV.setOnClickListener(this);
//        newsTV.setOnClickListener(this);
//        myTV.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.home_page:
//            case R.id.home_page_tv:
//                homePage.setBackgroundResource(R.drawable.home_page_click);
//                member.setBackgroundResource(R.drawable.member);
//                find.setBackgroundResource(R.drawable.find);
//                news.setBackgroundResource(R.drawable.news);
//                my.setBackgroundResource(R.drawable.my);
//                break;
//            case R.id.member:
//            case R.id.member_tv:
//                member.setBackgroundResource(R.drawable.member_click);
//                homePage.setBackgroundResource(R.drawable.home_page);
//                find.setBackgroundResource(R.drawable.find);
//                news.setBackgroundResource(R.drawable.news);
//                my.setBackgroundResource(R.drawable.my);
//                break;
//            case R.id.find:
//            case R.id.find_tv:
//                find.setBackgroundResource(R.drawable.find_click);
//                member.setBackgroundResource(R.drawable.member);
//                homePage.setBackgroundResource(R.drawable.home_page);
//                news.setBackgroundResource(R.drawable.news);
//                my.setBackgroundResource(R.drawable.my);
//                break;
//            case R.id.news:
//            case R.id.news_tv:
//                news.setBackgroundResource(R.drawable.news_click);
//                find.setBackgroundResource(R.drawable.find);
//                member.setBackgroundResource(R.drawable.member);
//                homePage.setBackgroundResource(R.drawable.home_page);
//                my.setBackgroundResource(R.drawable.my);
//                break;
//            case R.id.my:
//            case R.id.my_tv:
//                my.setBackgroundResource(R.drawable.my_click);
//                news.setBackgroundResource(R.drawable.news);
//                find.setBackgroundResource(R.drawable.find);
//                member.setBackgroundResource(R.drawable.member);
//                homePage.setBackgroundResource(R.drawable.home_page);
//                break;
//            default:
//                break;
//        }
//    }
//}
