package com.example.zhihu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhihu.bean.HotSearchType;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class HotSearchActivity extends AppCompatActivity {

    private List<HotSearchType> searchTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_search);

        //建立数据库连接
        Connector.getDatabase();

        //判断数据库有无热搜类型的数据
        //如有，则直接使用，如无，则添加再使用
        List<HotSearchType> hotSearchTypes = DataSupport.findAll(HotSearchType.class);
        if (hotSearchTypes.size() == 0){
            saveTypeData();
            //再进行一次查询
            List<HotSearchType> hotSearchTypes2 = DataSupport.findAll(HotSearchType.class);
            searchTypes = hotSearchTypes2;
        }else {
            searchTypes = hotSearchTypes;
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.search_sort_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //纵向线性布局
//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);
        HotSearchTypeAdapter adapter = new HotSearchTypeAdapter(searchTypes);
        recyclerView.setAdapter(adapter);

    }

    private void saveTypeData(){
        for (int i = 0; i < 9;i++){
            HotSearchType type = new HotSearchType();
            type.setSearchName("热搜");
            type.save();
        }
    }
}
