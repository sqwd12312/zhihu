package com.example.zhihu;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.zhihu.Interface.FilterListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qny on 2020/6/30.
 */

public class SearchAdapter extends BaseAdapter implements Filterable{

    private List<String> list = new ArrayList<String>();

    private Context context;

    private MyFilter filter = null;

    private FilterListener listener = null;

    public SearchAdapter(List<String> list, Context context, FilterListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.search_item,null);
            holder = new ViewHolder();
            holder.searchBtn = (Button) convertView.findViewById(R.id.search_btn);
            holder.searchContent = (TextView)convertView.findViewById(R.id.search_content);
            holder.deleteBtn = (Button)convertView.findViewById(R.id.delete_btn);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.searchContent.setText(list.get(position));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new MyFilter(list);
        }
        return filter;
    }

    //创建内部类MyFilter继承Filter类，并重写相关方法，实现数据的过滤
    class MyFilter extends Filter{

        private List<String> original = new ArrayList<String>();

        public MyFilter(List<String> original) {
            this.original = original;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (TextUtils.isEmpty(constraint)){
                results.values = original;
                results.count = original.size();
            }else{
                List<String> mList = new ArrayList<String>();
                for (String s : original){
                    //按照过滤规则进行过滤
                    //规则：出现的搜素内容包含搜索词
                    if(s.trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())){
                        mList.add(s);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }

        //该方法用来刷新用户界面，根据过滤后的数据重新展示列表
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (List<String>) results.values;
            // 如果接口对象不为空，那么调用接口中的方法获取过滤后的数据，具体的实现在new这个接口的时候重写的方法里执行
            if (listener != null){
                listener.getFilterData(list);
            }
            // 刷新数据源显示
            notifyDataSetChanged();
        }
    }


    //控件缓存类
    class ViewHolder{
        Button searchBtn;
        TextView searchContent;
        Button deleteBtn;
    }
}
