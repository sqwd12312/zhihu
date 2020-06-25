package com.example.zhihu.bean;

/**
 * Created by qny on 2020/6/22.
 */

public class Recommend {

    private String title;

    private int HeadImgId;

    private String authorNickName;

    private String aboutAuthor;

    private String content;

    private int agreeNumber;

    private int commentNumber;

    public Recommend(String title, int headImgId, String authorNickName, String aboutAuthor, String content, int agreeNumber, int commentNumber) {
        this.title = title;
        HeadImgId = headImgId;
        this.authorNickName = authorNickName;
        this.aboutAuthor = aboutAuthor;
        this.content = content;
        this.agreeNumber = agreeNumber;
        this.commentNumber = commentNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHeadImgId() {
        return HeadImgId;
    }

    public void setHeadImgId(int headImgId) {
        HeadImgId = headImgId;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAgreeNumber() {
        return agreeNumber;
    }

    public void setAgreeNumber(int agreeNumber) {
        this.agreeNumber = agreeNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }
}
