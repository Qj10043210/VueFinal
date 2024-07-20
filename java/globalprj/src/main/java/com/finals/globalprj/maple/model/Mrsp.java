package com.finals.globalprj.maple.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="mrsp")
public class Mrsp {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    @Column(name="pname",length = 45)
    private String pname;

    @Column(name="rwin",columnDefinition = "INT DEFAULT '0'")
    private int rwin;

    @Column(name="rlose",columnDefinition = "INT DEFAULT '0'")
    private int rlose;

    @Column(name="rdraw",columnDefinition = "INT DEFAULT '0'")
    private int rdraw;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getRwin() {
        return rwin;
    }

    public void setRwin(int rwin) {
        this.rwin = rwin;
    }

    public int getRlose() {
        return rlose;
    }

    public void setRlose(int rlose) {
        this.rlose = rlose;
    }

    public int getRdraw() {
        return rdraw;
    }

    public void setRdraw(int rdraw) {
        this.rdraw = rdraw;
    }

    
    
}
