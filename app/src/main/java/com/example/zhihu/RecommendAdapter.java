package com.example.zhihu;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhihu.bean.Recommend;

import java.util.List;

/**
 * Created by qny on 2020/6/22.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private List<Recommend> mRecommendList;

    public RecommendAdapter(List<Recommend> recommendList){
        mRecommendList = recommendList;
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView recommendTitle;
        RoundImageView headImg;
        TextView nickName;
        TextView aboutAuthor;
        TextView content;
        TextView agreeNumber;
        TextView commentNumber;

        public ViewHolder(View view) {
            super(view);
            recommendTitle = (TextView)view.findViewById(R.id.recommend_title);
            headImg = (RoundImageView)view.findViewById(R.id.author_head_portrait);
            nickName = (TextView)view.findViewById(R.id.author_nickname);
            aboutAuthor = (TextView)view.findViewById(R.id.about_author);
            content = (TextView)view.findViewById(R.id.content);
            agreeNumber = (TextView)view.findViewById(R.id.agree_number);
            commentNumber = (TextView)view.findViewById(R.id.comment_number);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recommend recommend = mRecommendList.get(position);
        holder.recommendTitle.setText(recommend.getTitle());
        holder.headImg.setImageResource(recommend.getHeadImgId());
        holder.nickName.setText(recommend.getAuthorNickName());
        holder.aboutAuthor.setText(recommend.getAboutAuthor());
        holder.content.setText(recommend.getContent());
        holder.agreeNumber.setText(recommend.getAgreeNumber()+"");
        holder.commentNumber.setText(recommend.getCommentNumber()+"");
    }

    @Override
    public int getItemCount() {
        return mRecommendList.size();
    }


}
