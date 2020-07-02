package com.example.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import com.example.zhihu.bean.Follow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qny on 2020/7/1.
 */

public class FollowFragment extends Fragment {

    private List<Follow> follows = new ArrayList<>();

    private RecyclerView followRecyclerView;

    private FollowAdapter followAdapter;

    private SwipeRefreshLayout followRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.follow_fragment,container,false);
        //初始化控件
        initControl(view);
        //网络请求“关注”的数据
        requestFollowData();
        return view;
    }

    //初始化控件
    private void initControl(View view){
        followRecyclerView = (RecyclerView)view.findViewById(R.id.follow_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        followRecyclerView.setLayoutManager(layoutManager);

        followRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.follow_ref);
        followRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //下拉刷新监听
        followRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFollowData();
            }
        });
    }

    //网络请求“关注”信息
    private void requestFollowData(){
        new Thread(){
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.31.88:8014/follow.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        followAdapter = new FollowAdapter(follows);
                        followRecyclerView.setAdapter(followAdapter);
                        followRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }.start();
    }

    //解析请求到的“关注”数据
    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        follows = gson.fromJson(jsonData,new TypeToken<List<Follow>>(){}.getType());
        for (Follow follow : follows){
            follow.setAuthorHead(R.drawable.author_head_portrait);
            follow.setImage(R.drawable.author_head_portrait);
        }
    }
}
