package com.finals.globalprj.maple;

import java.time.LocalDateTime;

/**
 * Mall
 */
public class Mall {

    private int id;   
    private String name;    
    private String password;  
    private LocalDateTime lastlogin;    
    private LocalDateTime createdat;    
    private boolean banned;    
    private String banreason;    
    private String banby;
    private int bid;
    private String pname;
    private int btype;
    private String btitle;
    private String bcotext;
    private LocalDateTime bcreatedat;
    private boolean bplaying;
    private int lroom;
    private int gid;
    private String rolr;
    private String ghand;
    private int gnum;
    private int lid;
    private String ltype;
    private LocalDateTime lcreatedat;
    private int pid;
    private int plevel;
    private double pmaxhp;
    private double pcurhp;
    private int pstr;
    private int pdex;
    private int pint;
    private int pluk;
    private double pexp;
    private int pbalance;
    private LocalDateTime pcreatedat;
    private int plocation;
    private int phair;
    private int pface;
    private int ppoint;
    private int roid;
    private boolean rore;
    private int rid;
    private int rwin;
    private int rlose;
    private int rdraw;

    private String hl;
	private String hr;
	private String namel;
	private String namer;
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
    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
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
    public int getLid() {
        return lid;
    }
    public void setLid(int lid) {
        this.lid = lid;
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
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
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
    public int getRoid() {
        return roid;
    }
    public void setRoid(int roid) {
        this.roid = roid;
    }
    public boolean isRore() {
        return rore;
    }
    public void setRore(boolean rore) {
        this.rore = rore;
    }
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
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
    public String getHl() {
        return hl;
    }
    public void setHl(String hl) {
        this.hl = hl;
    }
    public String getHr() {
        return hr;
    }
    public void setHr(String hr) {
        this.hr = hr;
    }
    public String getNamel() {
        return namel;
    }
    public void setNamel(String namel) {
        this.namel = namel;
    }
    public String getNamer() {
        return namer;
    }
    public void setNamer(String namer) {
        this.namer = namer;
    }

    
    
    
}