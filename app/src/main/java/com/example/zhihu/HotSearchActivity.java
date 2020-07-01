package com.example.zhihu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.example.zhihu.Interface.FilterListener;
import com.example.zhihu.bean.HotSearch;
import com.example.zhihu.bean.HotSearchType;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import java.util.ArrayList;
import java.util.List;

public class HotSearchActivity extends AppCompatActivity implements View.OnClickListener{

    private List<HotSearchType> searchTypes = new ArrayList<>();

    private List<HotSearch> hotSearches = new ArrayList<>();

    private ListView searchContentLV;

    private List<String> searchContentTest = new ArrayList<String>();

    private SearchAdapter searchAdapter;

    private EditText searchET;

    private Button backBtn;

    //需要被其他类调用，所以定义成static，否则再生成HotSearchActivity实例并调用时会出现空指针
    private static RecyclerView hotSearchRecyclerView;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_search);
        //初始化控件
        initControl();
        //添加测试用的搜索内容
        setData();
        //建立数据库连接
        Connector.getDatabase();
        //初始化热搜类型的数据
        initSearchType();
        //第一次进入该活动时默认显示“热搜”当中的内容
        queryHotDataById(searchTypes.get(0).getId());
        //给最开始的存放搜索内容的ListView添加点击事件
        setItemClick(searchContentTest);
        //给搜索框注册监听事件
        setSearchListeners();

    }

    //初始化控件
    private void initControl(){
        searchContentLV = (ListView)findViewById(R.id.search_content_list_view);

        recyclerView = (RecyclerView)findViewById(R.id.search_sort_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HotSearchTypeAdapter adapter = new HotSearchTypeAdapter(searchTypes);
        recyclerView.setAdapter(adapter);

        hotSearchRecyclerView = (RecyclerView)findViewById(R.id.hot_search_recyclerview);

        searchET = (EditText)findViewById(R.id.search_edit);

        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
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


    //数据初始化并设置adapter
    private void setData(){
        initData();
        searchAdapter = new SearchAdapter(searchContentTest, this, new FilterListener() {
            @Override
            public void getFilterData(List<String> list) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                setItemClick(list);
            }
        });
        searchContentLV.setAdapter(searchAdapter);
    }

    //给listView添加item的单击事件
    protected void setItemClick(final List<String> filter_lists) {
        searchContentLV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 点击对应的item时，弹出toast提示所点击的内容
                Toast.makeText(HotSearchActivity.this, filter_lists.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //初始化存放搜索内容的listView中的数据
    private void initData() {
        searchContentTest.add("看着飞舞的尘埃   掉下来");
        searchContentTest.add("没人发现它存在");
        searchContentTest.add("多自由自在");
        searchContentTest.add("可世界都爱热热闹闹");
        searchContentTest.add("容不下   我百无聊赖");
        searchContentTest.add("不应该   一个人 发呆");
        searchContentTest.add("只有我   守着安静的沙漠");
        searchContentTest.add("等待着花开");
        searchContentTest.add("只有我   看着别人的快乐");
        searchContentTest.add("看着飞舞的尘埃   掉下来");
        searchContentTest.add("没人发现它存在");
        searchContentTest.add("多自由自在");
        searchContentTest.add("可世界都爱热热闹闹");
        searchContentTest.add("容不下   我百无聊赖");
        searchContentTest.add("不应该   一个人 发呆");
        searchContentTest.add("只有我   守着安静的沙漠");
        searchContentTest.add("等待着花开");
        searchContentTest.add("只有我   看着别人的快乐");
    }


    //给搜索框注册监听事件
    private void setSearchListeners(){
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //判断搜索框是否内容
                if (s.length() != 0){
                    //隐藏热搜类型的RecyclerView控件
                    recyclerView.setVisibility(View.GONE);
                    //隐藏热搜的RecyclerView控件
                    hotSearchRecyclerView.setVisibility(View.GONE);
                    //显示符合搜索内容的ListView控件
                    searchContentLV.setVisibility(View.VISIBLE);
                    // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                    if(searchAdapter != null){
                        searchAdapter.getFilter().filter(s);
                    }
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    hotSearchRecyclerView.setVisibility(View.VISIBLE);
                    searchContentLV.setVisibility(View.VISIBLE);
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

    //初始化热搜类型的数据
    private void initSearchType(){
        //判断数据库有无热搜类型的数据
        //如有，则直接使用，如无，则添加再使用
        List<HotSearchType> hotSearchTypes = DataSupport.findAll(HotSearchType.class);
        if (hotSearchTypes.size() == 0){
            //向数据库添加热搜类型的数据
            saveTypeData();
            //再进行一次查询
            List<HotSearchType> hotSearchTypes2 = DataSupport.findAll(HotSearchType.class);
            searchTypes = hotSearchTypes2;
        }else {
            searchTypes = hotSearchTypes;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
        }
    }
}
