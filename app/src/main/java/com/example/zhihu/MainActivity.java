package com.example.zhihu;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    private Button recommendBtn;

    private Button followBtn;

    private Button hotListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recommendBtn = (Button)findViewById(R.id.recommend_btn);
        followBtn = (Button)findViewById(R.id.follow_btn);
        hotListBtn = (Button)findViewById(R.id.hot_list_btn);
        recommendBtn.setOnClickListener(this);
        followBtn.setOnClickListener(this);
        hotListBtn.setOnClickListener(this);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }

        //下拉刷新
//        recommendRef = (SwipeRefreshLayout)findViewById(R.id.recommend_ref);
//        recommendRef.setColorSchemeResources(R.color.colorPrimary);
//        recommendRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                requestRecommendData();
//            }
//        });
//
//        recyclerView = (RecyclerView)findViewById(R.id.recommend_recyclerview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        requestRecommendData();
//
        replaceFragment(new RecommendFragment());



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



















