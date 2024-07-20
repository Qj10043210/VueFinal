package com.finals.globalprj.maple.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="mgame")
public class Mgame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;

    @Column(name="pname",length = 45)
    private String pname;

    @Column(name="btype")
    private int btype;

    @Column(name="rolr",length = 1)
    private String rolr;

    @Column(name="ghand",length = 45)
    private String ghand;

    @Column(name="gnum")
    private int gnum;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getBtype() {
        return btype;
    }

    public void setBtype(int btype) {
        this.btype = btype;
    }

    public String getRolr() {
        return rolr;
    }

    public void setRolr(String rolr) {
        this.rolr = rolr;
    }

    public String getGhand() {
        return ghand;
    }

    public void setGhand(String ghand) {
        this.ghand = ghand;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    
    
}
