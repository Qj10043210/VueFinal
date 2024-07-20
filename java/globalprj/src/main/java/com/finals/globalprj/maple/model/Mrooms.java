package com.finals.globalprj.maple.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="mrooms")
public class Mrooms {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roid;

    @Column(name="lroom")
    private int lroom;

    @Column(name="rolr",length = 1)
    private String rolr;

    @Column(name="rore",columnDefinition = "TINYINT(1) DEFAULT '0'")
    private boolean rore;

    @Column(name="btype")
    private int btype;

    @Column(name="pname",length = 45)
    private String pname;

    
    public Mrooms(int lroom, String rolr, int btype, String pname) {
        this.lroom = lroom;
        this.rolr = rolr;
        this.btype = btype;
        this.pname = pname;
    }

    public Mrooms() {
    }

    public int getRoid() {
        return roid;
    }

    public void setRoid(int roid) {
        this.roid = roid;
    }

    public int getLroom() {
        return lroom;
    }

    public void setLroom(int lroom) {
        this.lroom = lroom;
    }

    public String getRolr() {
        return rolr;
    }

    public void setRolr(String rolr) {
        this.rolr = rolr;
    }

    public boolean isRore() {
        return rore;
    }

    public void setRore(boolean rore) {
        this.rore = rore;
    }

    public int getBtype() {
        return btype;
    }

    public void setBtype(int btype) {
        this.btype = btype;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    


}
