package com.example.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.zhihu.bean.Recommend;
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

public class RecommendFragment extends Fragment {

    private List<Recommend> recommendList = new ArrayList<>();

    private RecyclerView recyclerView ;

    private SwipeRefreshLayout recommendRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_fragment,container,false);
        //初始化控件
        initControl(view);
        //通过网络请求“推荐”数据
        requestRecommendData();
        return view;

    }

    //初始化控件
    private void initControl(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.recommend_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recommendRef = (SwipeRefreshLayout)view.findViewById(R.id.recommend_ref);
        recommendRef.setColorSchemeResources(R.color.colorPrimary);
        //下拉刷新监听
        recommendRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestRecommendData();
            }
        });
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
                getActivity().runOnUiThread(new Runnable(){
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
