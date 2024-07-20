package com.finals.globalprj.maple.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="maccounts")
public class Maccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",nullable = false, length = 50)
    private String name;

    @Column(name="password", nullable = false, length = 128)
    private String password;

    @Column(name="lastlogin")
    private LocalDateTime lastlogin;

    @Column(name="creadtedat", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdat;

    @Column(name="banned",nullable = false, columnDefinition = "TINYINT(1) DEFAULT '0'")
    private boolean banned;

    @Column(name="banreason",columnDefinition = "TEXT")
    private String banreason;

    @Column(name="banby",length = 100)
    private String banby;
    
    @PrePersist
    protected void onCreate() {
        this.createdat = LocalDateTime.now();
    }
    

    public Maccounts() {
    }

    
    public Maccounts(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(LocalDateTime lastlogin) {
        this.lastlogin = lastlogin;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getBanreason() {
        return banreason;
    }

    public void setBanreason(String banreason) {
        this.banreason = banreason;
    }

    public String getBanby() {
        return banby;
    }

    public void setBanby(String banby) {
        this.banby = banby;
    }
    
}
