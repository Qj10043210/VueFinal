package com.finals.globalprj.orgin.board_folder.model;

import java.time.LocalDateTime;
import java.util.List;

import com.finals.globalprj.orgin.article_folder.model.ArticleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "boardsdatas")
public class BoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_no")
    private int boardNo;
    
    @Column(name="board_name",columnDefinition = "VARCHAR(45)")
    private String boardName;

    @Column(name="board_who_no",columnDefinition = "INT DEFAULT -1")
    private int boardWhoNo;

    @Column(name="board_expression",columnDefinition = "VARCHAR(100) DEFAULT ''")
    private String boardExpression;

   @Column(name = "board_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime boardCreated; 
    
    @Column(name="board_expression_term",columnDefinition = "VARCHAR(100) DEFAULT ''")
    private String boardExpressionTerm;

    @Column(name="board_terminated",columnDefinition = "INT DEFAULT 0")
    private int boardTerminated;

    @Column(name="board_style",columnDefinition = "LONGTEXT DEFAULT NULL")
    private String boardStyle;

    @PrePersist
    protected void onCreate() {
        boardCreated = LocalDateTime.now();
    }
    @Transient
    private int articleCount;

    public BoardModel() {
    }


    public BoardModel(String boardName, int boardWhoNo, String boardExpression, String boardExpressionTerm, String boardStyle) {
        this.boardName = boardName;
        this.boardWhoNo = boardWhoNo;
        this.boardExpression = boardExpression;
        this.boardExpressionTerm = boardExpressionTerm;
        this.boardStyle = boardStyle;
    }

    public BoardModel(String boardName, String boardExpression, String boardExpressionTerm, String boardStyle) {
        this.boardName = boardName;
        this.boardExpression = boardExpression;
        this.boardExpressionTerm = boardExpressionTerm;
        this.boardStyle = boardStyle;
    }

    public BoardModel(int boardTerminated){
        this.boardTerminated = boardTerminated;
    }


    public int getBoardNo() {
        return boardNo;
    }

    public void setBoadrNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getBoardWhoNo() {
        return boardWhoNo;
    }

    public void setBoardWhoNo(int boardWhoNo) {
        this.boardWhoNo = boardWhoNo;
    }

    public String getBoardExpression() {
        return boardExpression;
    }

    public void setBoardExpression(String boardExpression) {
        this.boardExpression = boardExpression;
    }

    public LocalDateTime getBoardCreated() {
        return boardCreated;
    }

    @OneToMany(mappedBy = "board")
    private List<ArticleModel> articles;
    
    public void setBoardCreated(LocalDateTime boardCreated) {
        this.boardCreated = boardCreated;
    }


    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }


    public String getBoardExpressionTerm() {
        return boardExpressionTerm;
    }


    public void setBoardExpressionTerm(String boardExpressionTerm) {
        this.boardExpressionTerm = boardExpressionTerm;
    }


    public int getArticleCount() {
        return articleCount;
    }


    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }


    public int getBoardTerminated() {
        return boardTerminated;
    }


    public void setBoardTerminated(int boardTerminated) {
        this.boardTerminated = boardTerminated;
    }

    public String getBoardStyle() {
        return boardStyle;
    }


    public void setBoardStyle(String boardStyle) {
        this.boardStyle = boardStyle;
    }

}
