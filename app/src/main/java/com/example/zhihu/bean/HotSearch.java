package com.example.zhihu.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by qny on 2020/6/29.
 */

public class HotSearch extends DataSupport{

    private int id;

    //热搜标题
    private String title;

    //热搜简介
    private String synopsis;

    //热搜所属类型Id
    private int searchTypeId;

    //热度排行名次
    private int Rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getSearchTypeId() {
        return searchTypeId;
    }

    public void setSearchTypeId(int searchTypeId) {
        this.searchTypeId = searchTypeId;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }
}
