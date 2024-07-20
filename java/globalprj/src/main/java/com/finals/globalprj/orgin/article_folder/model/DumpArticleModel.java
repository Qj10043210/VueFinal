package com.finals.globalprj.orgin.article_folder.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "dumparticledatas")
public class DumpArticleModel {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dumparticle_no")
    private int dumparticleNo;

    @Column(name="article_no", columnDefinition = "INT")
    private int articleNo;

    @Column(name="board_no", columnDefinition = "INT")
    private int boardNo;

    @Column(name="user_no", columnDefinition = "INT")
    private int userNo;
    
    @Column(name="user_ban", columnDefinition = "INT UNSIGNED")
    private int userBan;
    
    @Column(name="article_title", columnDefinition = "VARCHAR(100)")
    private String articleTitle;

    @Lob //대용량 데이터
    @Column(name="article_content", columnDefinition = "LONGTEXT")
    private String articleContent;

    @Column(name="article_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime articleCreated;

    @Column(name="article_adjusted", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private LocalDateTime articleAdjusted;

    @PrePersist
    protected void onCreate() {
        articleAdjusted = LocalDateTime.now();
    }
    
    public DumpArticleModel() {
    }    

    public DumpArticleModel(int articleNo, int boardNo, int userNo, int userBan, String articleTitle,
            String articleContent, LocalDateTime articleCreated) {
        this.articleNo = articleNo;
        this.boardNo = boardNo;
        this.userNo = userNo;
        this.userBan = userBan;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCreated = articleCreated;
    }

    public int getDumparticleNo() {
        return dumparticleNo;
    }

    public void setDumparticleNo(int dumparticleNo) {
        this.dumparticleNo = dumparticleNo;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getUserBan() {
        return userBan;
    }

    public void setUserBan(int userBan) {
        this.userBan = userBan;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public LocalDateTime getArticleCreated() {
        return articleCreated;
    }

    public void setArticleCreated(LocalDateTime articleCreated) {
        this.articleCreated = articleCreated;
    }

    public LocalDateTime getArticleAdjusted() {
        return articleAdjusted;
    }

    public void setArticleAdjusted(LocalDateTime articleAdjusted) {
        this.articleAdjusted = articleAdjusted;
    }



    
}
