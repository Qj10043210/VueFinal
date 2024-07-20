package com.finals.globalprj.orgin.user_folder.model;

import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.weaver.Lint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finals.globalprj.orgin.article_folder.model.ArticleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

// Jpa(jakarta)를 사용하여 테이블 및 컬럼을 맵핑을 간소화하고, 복잡한 sql 쿼리 작성을 지양. 
// 복잡한 쿼리 직접 작성->이미 제작된 인터페이스를 사용할 수 있게 됨. 물론 결국 sql 작성능력은 상승하진 않지만, 이렇게 인터페이스를 사용하는 법도 배워야지.
//----------------------------------------------------------------------------------------------
// DAO(DATA ACCESS OBJECT) -> REPOSITORY(미리 제작된 인터페이스 주로 사용) : CRUD 직접 시행
// VO(VALUE OBJECT) -> ENTITY, MODEL : VO는 담는 그릇으로 주로 사용되자만 ENTITY는 맵핑하여 사용함
//SERVICE : CONTROLLER와 DAO-REPOSITORY와 연결하는 역할. 데이터 엑세스 수행하고 결과를 가공하기도 함
// CONTROLLER : 클라이언트의 요청을 처리하는 계층, 사용자 입력을 받아 해당 요청을 처리
@Entity
@Table(name = "userdatas")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_no")
    private int userNo;

    @Column(name="user_id",columnDefinition = "VARCHAR(20)")
    private String userId;

    @Column(name="user_pwd", columnDefinition = "VARCHAR(50)")
    private String userPwd;

    @Column(name="user_name", columnDefinition = "VARCHAR(45)")
    private String userName;

    @Column(name="user_mail", columnDefinition = "VARCHAR(45)")
    private String userMail;

    @Column(name="user_currency", columnDefinition = "INT DEFAULT 0")
    private int userCurrency;

    @Column(name="user_currency_external", columnDefinition = "BIGINT DEFAULT 0")
    private long userCurrencyExternal;

    @Column(name="user_ban",columnDefinition = "INT UNSIGNED DEFAULT 0 COMMENT '0=normal\n1=error, but ban\n2=temp ban\n3=perm ban'")
    private int userBan;
    //0=normal--1=error, but ban--2=temp ban--3=perm ban
    @Column(name="user_ban_reason", columnDefinition = "LONGTEXT DEFAULT NULL")
    private String userBanReason;    
    
    @Column(name = "user_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime userCreated;

    @Column(name="user_power", columnDefinition = "INT DEFAULT '0'")
    private int userPower;
    
    public UserModel() {
    }
    public UserModel(String userId, String userPwd, String userName, String userMail) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userMail = userMail;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user") // 이 프로퍼티를 JSON 직렬화에서 제외하여 무한 루프를 방지
    private List<ArticleModel> articles;
    
    public int getUserNo() {
        return userNo;
    }



    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }



    public String getUserId() {
        return userId;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getUserPwd() {
        return userPwd;
    }



    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }



    public String getUserName() {
        return userName;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getUserMail() {
        return userMail;
    }



    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }



    public int getUserCurrency() {
        return userCurrency;
    }



    public void setUserCurrency(int userCurrency) {
        this.userCurrency = userCurrency;
    }



    public long getUserCurrencyExternal() {
        return userCurrencyExternal;
    }



    public void setUserCurrencyExternal(long userCurrencyExternal) {
        this.userCurrencyExternal = userCurrencyExternal;
    }



    public int getUserBan() {
        return userBan;
    }



    public void setUserBan(int userBan) {
        this.userBan = userBan;
    }



    public String getUserBanReason() {
        return userBanReason;
    }



    public void setUserBanReason(String userBanReason) {
        this.userBanReason = userBanReason;
    }



    public LocalDateTime getUserCreated() {
        return userCreated;
    }



    public void setUserCreated(LocalDateTime userCreated) {
        this.userCreated = userCreated;
    }

    public int getUserPower() {
        return userPower;
    }
    public void setUserPower(int userPower) {
        this.userPower = userPower;
    }
    
    @PrePersist
    protected void onCreate() {
        userCreated = LocalDateTime.now();
    }
}
