package com.finals.globalprj.maple.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="mboard")
public class Mboard {
    
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @Column(name="pname",length = 45)
    private String pname;

    @Column(name="btype")
    private int btype;

    @Column(name="btitle",length = 100)
    private String btitle;

    @Column(name="bcotext",length = 200)
    private String bcotext;

    @Column(name="bcreatedat",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime bcreatedat;

    @Column(name="bplaying",columnDefinition = "TINYINT(1) DEFAULT '0'")
    private boolean bplaying;

    @Column(name="lroom")
    private int lroom;

  @PrePersist
    protected void onCreate() {
        this.bcreatedat = LocalDateTime.now();
    }

    

public Mboard() {
}



public Mboard(String pname, int btype, String btitle, int lroom) {
    this.pname = pname;
    this.btype = btype;
    this.btitle = btitle;
    this.lroom = lroom;
}



public int getBid() {
    return bid;
}



public void setBid(int bid) {
    this.bid = bid;
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



public String getBtitle() {
    return btitle;
}



public void setBtitle(String btitle) {
    this.btitle = btitle;
}



public String getBcotext() {
    return bcotext;
}



public void setBcotext(String bcotext) {
    this.bcotext = bcotext;
}



public LocalDateTime getBcreatedat() {
    return bcreatedat;
}



public void setBcreatedat(LocalDateTime bcreatedat) {
    this.bcreatedat = bcreatedat;
}



public boolean isBplaying() {
    return bplaying;
}



public void setBplaying(boolean bplaying) {
    this.bplaying = bplaying;
}



public int getLroom() {
    return lroom;
}



public void setLroom(int lroom) {
    this.lroom = lroom;
}




}
