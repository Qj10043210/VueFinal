package com.finals.globalprj.maple.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.finals.globalprj.maple.Mall;
import com.finals.globalprj.maple.model.Mboard;
import com.finals.globalprj.maple.model.Mlog;
import com.finals.globalprj.maple.repository.Rboard;
import com.finals.globalprj.maple.repository.Rlog;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SBoard {
    
    @Autowired
    Rboard rb;

	@Autowired
	Rlog rl;

    public List<Mboard> getBoard(int reqN) {		
		return rb.getBoard(reqN);
	}

	public void updateBoard(Mboard sources) {
		int a = rb.checkSevenBoardCount();
		if(a>=7){
			rb.checkSevenBoard1();
		}else{
			rb.checkSevenBoard0();
		}
		rb.leaveBoard(sources.getPname());
		rb.save(sources);
		// pname, btype, btitle, lroom (new)
	}

	public void leaveBoard(Mlog sources) {
		rl.deleteByPname(sources.getPname());
		int result =rl.checkRecentN(sources.getLroom());
		if(result!=1){
			rb.leaveBoard(sources.getPname());		
		}else if(result == 1){
			String rt = (String) rl.checkRecentName(sources.getLroom());
			rb.leaveGiveBoard(rt, sources.getLroom());
		}
			
	}


}
