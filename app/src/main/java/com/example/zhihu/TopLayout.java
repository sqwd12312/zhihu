package com.example.zhihu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by qny on 2020/6/18.
 */

public class TopLayout extends LinearLayout {
    public TopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.topshow,this);
    }
}
