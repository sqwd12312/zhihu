package com.example.zhihu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhihu.bean.HotSearch;
import com.example.zhihu.bean.HotSearchType;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class HotSearchActivity extends AppCompatActivity {

    private List<HotSearchType> searchTypes = new ArrayList<>();

    private List<HotSearch> hotSearches = new ArrayList<>();

    //需要被其他类调用，所以定义成static，否则再生成HotSearchActivity实例并调用时会出现空指针
    private static RecyclerView hotSearchRecyclerView;

    private RecyclerView recyclerView;

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

        recyclerView = (RecyclerView)findViewById(R.id.search_sort_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HotSearchTypeAdapter adapter = new HotSearchTypeAdapter(searchTypes);
        recyclerView.setAdapter(adapter);

        hotSearchRecyclerView = (RecyclerView)findViewById(R.id.hot_search_recyclerview);

        //第一次进入该活动时默认显示“热搜”内容
        queryHotDataById(searchTypes.get(0).getId());

        final EditText searchET = (EditText)findViewById(R.id.search_edit);

        //给搜索框注册监听事件，当框内有内容时隐藏两个recyclerView，无内容时显示
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    recyclerView.setVisibility(View.INVISIBLE);
                    hotSearchRecyclerView.setVisibility(View.INVISIBLE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    hotSearchRecyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    //保存热搜类型
    private void saveTypeData(){

        HotSearchType type = new HotSearchType();
        type.setSearchName("热搜");
        type.save();

        HotSearchType type2 = new HotSearchType();
        type2.setSearchName("数码");
        type2.save();

        HotSearchType type3 = new HotSearchType();
        type3.setSearchName("影视");
        type3.save();

        HotSearchType type4 = new HotSearchType();
        type4.setSearchName("科学");
        type4.save();

        HotSearchType type5 = new HotSearchType();
        type5.setSearchName("体育");
        type5.save();

        HotSearchType type6 = new HotSearchType();
        type6.setSearchName("游戏");
        type6.save();

        HotSearchType type7 = new HotSearchType();
        type7.setSearchName("百科");
        type7.save();

    }

    //保存热搜数据
    private void saveHotSearch(){
        for (int i = 0;i < searchTypes.size();i++){
            String title = searchTypes.get(i).getSearchName();
            int typeId = searchTypes.get(i).getId();
            HotSearch hotSearch = new HotSearch(title,"这是一篇点击量100的热点事件",typeId,100);
            hotSearch.save();
            HotSearch hotSearch2 = new HotSearch(title,"这是一篇点击量200的热点事件",typeId,200);
            hotSearch2.save();
            HotSearch hotSearch3 = new HotSearch(title,"这是一篇点击量300的热点事件",typeId,300);
            hotSearch3.save();
            HotSearch hotSearch4 = new HotSearch(title,"这是一篇点击量400的热点事件",typeId,400);
            hotSearch4.save();
            HotSearch hotSearch5 = new HotSearch(title,"这是一篇点击量500的热点事件",typeId,500);
            hotSearch5.save();
            HotSearch hotSearch6 = new HotSearch(title,"这是一篇点击量600的热点事件",typeId,600);
            hotSearch6.save();
            HotSearch hotSearch7 = new HotSearch(title,"这是一篇点击量700的热点事件",typeId,700);
            hotSearch7.save();
            HotSearch hotSearch8 = new HotSearch(title,"这是一篇点击量800的热点事件",typeId,800);
            hotSearch8.save();
            HotSearch hotSearch9 = new HotSearch(title,"这是一篇点击量900的热点事件",typeId,900);
            hotSearch9.save();
        }
    }

    public void queryHotDataById(int typeId){
        //判断数据库有无热搜的数据
        //如有，则直接使用，如无，则添加再使用
        List<HotSearch> hotSearches2 = DataSupport.order("click desc")
                .where("searchTypeId = ?",typeId+"")
                .limit(8)
                .find(HotSearch.class);
        if (hotSearches2.size() == 0){
            saveHotSearch();
            List<HotSearch> hotSearches3 = DataSupport.order("click desc")
                    .limit(8)
                    .find(HotSearch.class);
            hotSearches = hotSearches3;
            for (int i = 0;i < hotSearches.size();i++){
                hotSearches.get(i).setRank(i+1);
            }
        }else {
            hotSearches = hotSearches2;
            for (int i = 0;i < hotSearches.size();i++){
                hotSearches.get(i).setRank(i+1);
            }
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        hotSearchRecyclerView.setLayoutManager(gridLayoutManager);
        HotSearchAdapter hotSearchAdapter = new HotSearchAdapter(hotSearches);
        hotSearchRecyclerView.setAdapter(hotSearchAdapter);
    }
}
