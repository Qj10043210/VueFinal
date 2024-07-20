package com.finals.globalprj.maple.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="mlog")
public class Mlog {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lid;

    @Column(name="btype")
    private int btype;

    @Column(name="lroom")
    private int lroom;

    @Column(name="pname",length = 45)
    private String pname;

    @Column(name="ltype",columnDefinition = "ENUM('ENTER','EXIT')")
    private String ltype;

    @Column(name="lcreatedat",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lcreatedat;

    @PrePersist
    protected void onCreate() {
        this.lcreatedat = LocalDateTime.now();
    }
    
    public Mlog(int btype, int lroom, String pname, String ltype) {
        this.btype = btype;
        this.lroom = lroom;
        this.pname = pname;
        this.ltype = ltype;
    }


    public Mlog() {
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getBtype() {
        return btype;
    }

    public void setBtype(int btype) {
        this.btype = btype;
    }

    public int getLroom() {
        return lroom;
    }

    public void setLroom(int lroom) {
        this.lroom = lroom;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    public LocalDateTime getLcreatedat() {
        return lcreatedat;
    }

    public void setLcreatedat(LocalDateTime lcreatedat) {
        this.lcreatedat = lcreatedat;
    }

    
    
}
