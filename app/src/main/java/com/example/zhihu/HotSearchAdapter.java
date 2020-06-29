package com.example.zhihu;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhihu.bean.HotSearch;

import java.util.List;

/**
 * Created by qny on 2020/6/29.
 */

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.ViewHolder>{

    private List<HotSearch> hotSearches;

    public HotSearchAdapter(List<HotSearch> hotSearches) {
        this.hotSearches = hotSearches;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rank;
        TextView title;
        TextView synopsis;

        public ViewHolder(View view) {
            super(view);
            rank = (TextView)view.findViewById(R.id.rank);
            title = (TextView)view.findViewById(R.id.title);
            synopsis = (TextView)view.findViewById(R.id.synopsis);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_search_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotSearch hotSearch = hotSearches.get(position);
        if (hotSearch.getRank() <= 3){
            holder.rank.setTextColor( Color.parseColor("#fa9614"));
        }
        holder.rank.setText(hotSearch.getRank()+"");
        holder.title.setText(hotSearch.getTitle());
        holder.synopsis.setText(hotSearch.getSynopsis());
    }

    @Override
    public int getItemCount() {
        return hotSearches.size();
    }


}
