package com.example.zhihu;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhihu.bean.Follow;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by qny on 2020/7/2.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {

    private List<Follow> follows;

    static  class ViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView authorHead;
        TextView  authorNickName;
        TextView publicationDate;
        TextView publicationType;
        TextView title;
        TextView content;
        SimpleDraweeView image;
        TextView agreeNumber;
        TextView commentNumber;

        public ViewHolder(View view) {
            super(view);
            authorHead = (SimpleDraweeView)view.findViewById(R.id.author_head);
            authorNickName = (TextView)view.findViewById(R.id.author_nickname);
            publicationDate = (TextView)view.findViewById(R.id.publication_date);
            publicationType = (TextView)view.findViewById(R.id.publication_type);
            title = (TextView)view.findViewById(R.id.title);
            content = (TextView)view.findViewById(R.id.content);
            image = (SimpleDraweeView)view.findViewById(R.id.image);
            agreeNumber = (TextView)view.findViewById(R.id.agree_number);
            commentNumber = (TextView)view.findViewById(R.id.comment_number);
        }
    }

    public FollowAdapter(List<Follow> follows) {
        this.follows = follows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follow_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Follow follow = follows.get(position);
        //本地图片设置成圆角
        Uri uri = Uri.parse("res://com.example.zhihu/" + follow.getAuthorHead());
        holder.authorHead.setImageURI(uri);
        holder.authorNickName.setText(follow.getAuthorNickName());
        holder.publicationDate.setText(follow.getPublicationDate());
        holder.publicationType.setText(follow.getPublicationType());
        holder.title.setText(follow.getTitle());
        holder.content.setText(follow.getContent());
        //本地图片设置成圆角
        Uri uri2 = Uri.parse("res://com.example.zhihu/" + follow.getImage());
        holder.image.setImageURI(uri2);
        //可能会出问题，可能要写成String
        holder.agreeNumber.setText(follow.getAgreeNumber()+"");
        holder.commentNumber.setText(follow.getCommentNumber()+"");
    }

    @Override
    public int getItemCount() {
        return follows.size();
    }
}





















