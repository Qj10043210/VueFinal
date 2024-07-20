package com.finals.globalprj.maple.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="mplayers")
public class Mplayers {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name="name",nullable = false, length = 50)
    private String name;

    @Column(name="pname",nullable = false, length = 50)
    private String pname;

    @Column(name="plevel",nullable = false, columnDefinition = "INT DEFAULT '1'")
    private int plevel;

    @Column(name="pmaxhp",nullable = false, columnDefinition = "DOUBLE DEFAULT '50'")
    private double pmaxhp;

    @Column(name="pcurhp",nullable = false, columnDefinition = "DOUBLE DEFAULT '50'")
    private double pcurhp;

    @Column(name="pstr",nullable = false, columnDefinition = "INT DEFAULT '1'")
    private int pstr;

    @Column(name="pdex",nullable = false, columnDefinition = "INT DEFAULT '1'")
    private int pdex;

    @Column(name="pint",nullable = false, columnDefinition = "INT DEFAULT '1'")
    private int pint;

    @Column(name="pluk",nullable = false, columnDefinition = "INT DEFAULT '1'")
    private int pluk;

    @Column(name="pexp",columnDefinition = "DOUBLE DEFAULT '0'")
    private double pexp;

    @Column(name="pbalance",columnDefinition = "INT DEFAULT '10'")
    private int pbalance;

    @Column(name="pcreatedat",nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime pcreatedat;

    @Column(name="plocation",nullable = false, columnDefinition = "INT DEFAULT '1'")
    private int plocation;

    @Column(name="phair",nullable = false, columnDefinition = "INT DEFAULT '30000'")
    private int phair;

    @Column(name="pface",nullable = false, columnDefinition = "INT DEFAULT '20000'")
    private int pface;

    @Column(name="ppoint",columnDefinition = "INT DEFAULT '0'")
    private int ppoint;

    @PrePersist
    protected void onCreate() {
        this.pcreatedat = LocalDateTime.now();
    }
    

    public Mplayers(String name, String pname, double pmaxhp, double pcurhp, int pstr, int pdex, int pint, int pluk,
            int phair, int pface) {
        this.name = name;
        this.pname = pname;
        this.pmaxhp = pmaxhp;
        this.pcurhp = pcurhp;
        this.pstr = pstr;
        this.pdex = pdex;
        this.pint = pint;
        this.pluk = pluk;
        this.phair = phair;
        this.pface = pface;
    }


    public Mplayers() {
    }


    public int getPid() {
        return pid;
    }


    public void setPid(int pid) {
        this.pid = pid;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPname() {
        return pname;
    }


    public void setPname(String pname) {
        this.pname = pname;
    }


    public int getPlevel() {
        return plevel;
    }


    public void setPlevel(int plevel) {
        this.plevel = plevel;
    }


    public double getPmaxhp() {
        return pmaxhp;
    }


    public void setPmaxhp(double pmaxhp) {
        this.pmaxhp = pmaxhp;
    }


    public double getPcurhp() {
        return pcurhp;
    }


    public void setPcurhp(double pcurhp) {
        this.pcurhp = pcurhp;
    }


    public int getPstr() {
        return pstr;
    }


    public void setPstr(int pstr) {
        this.pstr = pstr;
    }


    public int getPdex() {
        return pdex;
    }


    public void setPdex(int pdex) {
        this.pdex = pdex;
    }


    public int getPint() {
        return pint;
    }


    public void setPint(int pint) {
        this.pint = pint;
    }


    public int getPluk() {
        return pluk;
    }


    public void setPluk(int pluk) {
        this.pluk = pluk;
    }


    public double getPexp() {
        return pexp;
    }


    public void setPexp(double pexp) {
        this.pexp = pexp;
    }


    public int getPbalance() {
        return pbalance;
    }


    public void setPbalance(int pbalance) {
        this.pbalance = pbalance;
    }


    public LocalDateTime getPcreatedat() {
        return pcreatedat;
    }


    public void setPcreatedat(LocalDateTime pcreatedat) {
        this.pcreatedat = pcreatedat;
    }


    public int getPlocation() {
        return plocation;
    }


    public void setPlocation(int plocation) {
        this.plocation = plocation;
    }


    public int getPhair() {
        return phair;
    }


    public void setPhair(int phair) {
        this.phair = phair;
    }


    public int getPface() {
        return pface;
    }


    public void setPface(int pface) {
        this.pface = pface;
    }


    public int getPpoint() {
        return ppoint;
    }


    public void setPpoint(int ppoint) {
        this.ppoint = ppoint;
    }


    
    
}

