package com.example.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

/**
 * Created by qny on 2020/7/1.
 */

public class HotListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_list_fragment,container,false);
        //初始化控件
        initControl(view);
        return view;

    }

    /*
    * 初始化控件
    */
    private void initControl(View view){
        //页面上的话题轮播图
        ViewFlipper vf = (ViewFlipper)view.findViewById(R.id.vf);
        vf.addView(View.inflate(getContext(), R.layout.view_discuss, null));
        vf.addView(View.inflate(getContext(), R.layout.view_discuss2, null));
    }
}
