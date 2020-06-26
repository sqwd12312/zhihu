package com.example.zhihu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.example.zhihu.bean.Recommend;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    private List<Recommend> recommendList = new ArrayList<>();

    private RecyclerView recyclerView ;

    private SwipeRefreshLayout recommendRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }

        //下拉刷新
        recommendRef = (SwipeRefreshLayout)findViewById(R.id.recommend_ref);
        recommendRef.setColorSchemeResources(R.color.colorPrimary);
        recommendRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestRecommendData();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recommend_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        requestRecommendData();



    }


    //网络请求“推荐”数据
    private void requestRecommendData(){
        new Thread() {
            public void run() {
                //这儿是耗时操作，完成之后更新UI；
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.31.88:8014/recommend.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        //更新UI
                        RecommendAdapter adapter = new RecommendAdapter(recommendList);
                        recyclerView.setAdapter(adapter);
                        //关闭刷新动画
                        recommendRef.setRefreshing(false);
                    }
                });
            }
        }.start();


    }


    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        recommendList = gson.fromJson(jsonData,new TypeToken<List<Recommend>>(){}.getType());
        for (Recommend recommend: recommendList) {
            recommend.setHeadImgId(R.drawable.author_head_portrait3);
        }
    }


}
