package com.example.zhihu.bean;

/**
 * Created by qny on 2020/7/2.
 */


//关注类
public class Follow {
    private int id;
    //作者头像
    private int authorHead;
    //作者昵称
    private String authorNickName;
    //发布时间
    private String publicationDate;
    //发布类型
    private String publicationType;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //文章图片
    private int image;
    //同意数
    private int agreeNumber;
    //评论数
    private int commentNumber;

    public int getAuthorHead() {
        return authorHead;
    }

    public void setAuthorHead(int authorHead) {
        this.authorHead = authorHead;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(String publicationType) {
        this.publicationType = publicationType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Follow(int authorHead, String authorNickName, String publicationDate, String publicationType, String title, String content, int image, int agreeNumber, int commentNumber) {
        this.authorHead = authorHead;
        this.authorNickName = authorNickName;
        this.publicationDate = publicationDate;
        this.publicationType = publicationType;
        this.title = title;
        this.content = content;
        this.image = image;
        this.agreeNumber = agreeNumber;
        this.commentNumber = commentNumber;
    }
}
