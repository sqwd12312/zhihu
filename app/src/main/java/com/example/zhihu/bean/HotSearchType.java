package com.example.zhihu.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by qny on 2020/6/26.
 */

public class HotSearchType extends DataSupport{

    private int id;

    private String searchName;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotSearchType(String searchName) {
        this.searchName = searchName;
    }

    public HotSearchType() {
    }
}
