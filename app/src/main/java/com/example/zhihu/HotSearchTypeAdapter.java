package com.example.zhihu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhihu.bean.HotSearchType;

import java.util.List;

/**
 * Created by qny on 2020/6/26.
 */

public class HotSearchTypeAdapter extends RecyclerView.Adapter<HotSearchTypeAdapter.ViewHolder>{

    private List<HotSearchType> searchTypes;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View hotSearchView;
        Button searchButton;

        public ViewHolder(View view) {
            super(view);
            hotSearchView = view;
            searchButton = (Button)view.findViewById(R.id.sort_btn);
        }
    }

    public HotSearchTypeAdapter(List<HotSearchType> hotSearchTypes){
        searchTypes = hotSearchTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_search_sort_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HotSearchType type = searchTypes.get(position);
                holder.searchButton.setTextColor(0xFF000000);
                Toast.makeText(v.getContext(), ""+type.getSearchName(), Toast.LENGTH_SHORT).show();
                HotSearchActivity activity = new HotSearchActivity();
                activity.queryHotDataById(type.getId());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotSearchType type = searchTypes.get(position);
        holder.searchButton.setText(type.getSearchName());
    }

    @Override
    public int getItemCount() {
        return searchTypes.size();
    }


}
