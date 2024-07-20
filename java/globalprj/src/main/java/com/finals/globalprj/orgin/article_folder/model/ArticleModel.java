package com.finals.globalprj.orgin.article_folder.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finals.globalprj.orgin.board_folder.model.BoardModel;
import com.finals.globalprj.orgin.user_folder.model.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "articledatas")
public class ArticleModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_no")
    private int articleNo;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY를 추가하여 지연 로딩 설정.많은 article이 하나의 보드에 연결 될 수 있음
    @JoinColumn(name="board_no", referencedColumnName="board_no")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Jackson 라이브러리가 Hibernate 프록시 객체의 직렬화 문제를 피하도록 
    private BoardModel board;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY를 추가하여 지연 로딩 설정.많은 article이 하나의 유저에 연결 될 수 있음
    @JoinColumns({
        @JoinColumn(name="user_no", referencedColumnName="user_no"),
        @JoinColumn(name="user_ban", referencedColumnName="user_ban")
    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Jackson 라이브러리가 Hibernate 프록시 객체의 직렬화 문제를 피하도록 
    private UserModel user;

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
        this.articleCreated = LocalDateTime.now();
    }

    public ArticleModel() {
    }
    
    public ArticleModel(BoardModel board, UserModel user, String articleTitle, String articleContent) {
        this.board = board;
        this.user = user;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }   
    

    public ArticleModel(String articleTitle, String articleContent, LocalDateTime articleAdjusted) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleAdjusted = articleAdjusted;
    }

    public ArticleModel(BoardModel board, UserModel user, String articleTitle, String articleContent,
            LocalDateTime articleAdjusted) {
        this.board = board;
        this.user = user;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleAdjusted = articleAdjusted;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public BoardModel getBoard() {
        return board;
    }

    public void setBoard(BoardModel board) {
        this.board = board;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
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
// {
//     "board": {
//       "boardNo": 1
//     },
//     "user": {
//       "userNo": 1,
//       "userBan": 0
//     },
//     "articleTitle": "New Article",
//     "articleContent": "This is the content of the new article."
//      시간은 new Date().toISOString();
//   }
// 이런 식으로 데이터를 넘기면 돼