package com.example.zhihu;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by qny on 2020/6/18.
 */

public class TopLayout extends LinearLayout implements View.OnClickListener{

    private Button searchBtn;

    private TextView searchTv;

    private Context mContext;

    public TopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //拿到Context才能进行startActivity等操作
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.topshow,this);
        searchBtn = (Button) findViewById(R.id.search_button);
        searchTv = (TextView)findViewById(R.id.search_view);

        searchBtn.setOnClickListener(this);
        searchTv.setOnClickListener(this);

        Intent intent = new Intent(getContext(),HotSearchActivity.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_button:
            case R.id.search_view:
                Intent intent = new Intent(mContext,HotSearchActivity.class);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
