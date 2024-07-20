package com.finals.globalprj.maple.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.finals.globalprj.maple.Mall;
import com.finals.globalprj.maple.model.*;

import com.finals.globalprj.maple.repository.*;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Sgame {
	@Autowired
	Rboard rb;

	@Autowired
	Rlog rl;

	@Autowired
	Raccounts ra;

	@Autowired
	Rplayers rp;

	@Autowired
	Rrsp rr;

	@Autowired
	Rgame rg;

	@Autowired
	Rrooms rro;

	public Mplayers getScoreRSPP(String lid){
		rr.checkScoreRSP(lid);
		List<Mplayers> pps = new ArrayList<>();
		pps = rp.getUserInfo(lid);
		Mplayers pp = new Mplayers();
		pp=pps.get(0);
		return pp;
	}

	public Mrsp getScoreRSPR(String lid){
		List<Mrsp> rsps = new ArrayList<>();
		rsps = rr.getScoreRSP(lid);
		Mrsp rp = new Mrsp();			
		rp=rsps.get(0);
		return rp;
	}

	public List<Mall> getScoreRSP(String lid) {
		rr.checkScoreRSP(lid);
		List<Mrsp> rsps = new ArrayList<>();
		List<Mplayers> pps = new ArrayList<>();
		pps = rp.getUserInfo(lid);
		rsps = rr.getScoreRSP(lid);		
		Mrsp rp = new Mrsp();
		Mplayers pp = new Mplayers();
		pp=pps.get(0);
		rp=rsps.get(0);
		Mall all = new Mall();
		all.setPid(pp.getPid());
		all.setName(pp.getName());
		all.setPname(pp.getPname());
		all.setPlevel(pp.getPlevel());
		all.setPmaxhp(pp.getPmaxhp());
		all.setPcurhp(pp.getPcurhp());
		all.setPstr(pp.getPstr());
		all.setPdex(pp.getPdex());
		all.setPint(pp.getPint());
		all.setPluk(pp.getPluk());
		all.setPexp(pp.getPexp());
		all.setPbalance(pp.getPbalance());
		all.setPcreatedat(pp.getPcreatedat());
		all.setPlocation(pp.getPlocation());
		all.setPhair(pp.getPhair());
		all.setPface(pp.getPface());
		all.setPpoint(pp.getPpoint());		
		all.setRid(rp.getRid());		
		all.setRwin(rp.getRwin());
		all.setRlose(rp.getRlose());
		all.setRdraw(rp.getRdraw());
		List<Mall> newAll = new ArrayList<>();
		newAll.add(all);
		return newAll;
	}

	public String getResultRSP(Mall leftright) {
		String leftHand = leftright.getHl();
		String rightHand = leftright.getHr();

		if (leftHand.equals(rightHand)) {
			return "draw";
		} else if ((leftHand.equals("R") && rightHand.equals("P")) ||
				(leftHand.equals("P") && rightHand.equals("S")) ||
				(leftHand.equals("S") && rightHand.equals("R"))) {
			return "rw";
		} else if ((leftHand.equals("R") && rightHand.equals("S")) ||
				(leftHand.equals("P") && rightHand.equals("R")) ||
				(leftHand.equals("S") && rightHand.equals("P"))) {
			return "lw";
		} else {
			return "fail";
		}

	}

	public List<Mgame> giveHand(Mgame sources) {
		return rg.giveHand(sources.getGnum());
	}

	public void insertHand(Mgame sources) {
		rg.insertHand(sources.getPname(), sources.getBtype(), sources.getRolr(), sources.getGhand(),sources.getGnum());
	}

	public void deleteHand(String pname) {
		rg.deleteHand(pname);
	}

	public List<Mrooms> getCoonectedUser(Mrooms sources) {
		return rro.getCoonectedUser(sources.getLroom(),sources.getBtype());
	}

	public void loadReadyPlayer(){
		List<Mrooms> mav = new ArrayList<>();
		mav = rro.loadReadyPlayer();
		if(mav != null){
			for(Mrooms ma : mav){
				String pname = ma.getPname();
				rb.readyBoard(pname);
			}
		}
		
	}
}
