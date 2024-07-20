package com.finals.globalprj.maple.services;

import java.sql.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finals.globalprj.maple.repository.*;

import com.finals.globalprj.maple.model.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class Slog {
    
    @Autowired Rlog rl;
    @Autowired Rrooms rro;
    
    @Autowired
    private HttpSession httpSession;

    public void clearRoom(String pname){
        rro.deleteAllByPname(pname);
    }

    public void clearLog(String ltype, String pname){
        rl.deleteByLtypeAndPname(ltype, pname);
        // need to set ltype as ENTER
    }
    public void clearLogPlayer(String pname){
        rl.deleteByPname(pname);
    }

    public void clearLogAll(String ltype, int lroom){
        rl.deleteByLtypeAndLroom(ltype, lroom);
        // need to set ltype as ENTER
    }
    public void leaveRoom(Mlog mlog){        
        clearLog(mlog.getLtype(), mlog.getPname());
        clearRoom(mlog.getPname());
    }
    public String joinRoom(Mrooms logInfo, HttpSession session){ 
        String resOther = "";
        clearRoom(logInfo.getPname());
        int a = rro.countByLroom(logInfo.getLroom());
        List<Mrooms> result = new ArrayList<>();        
        if(logInfo.getRolr()==null||logInfo.getRolr().isBlank()||logInfo.getRolr().isEmpty()){
            logInfo.setRolr("L");
            sessionUpdate("newbie", "L");
        }
        if(a==1){
            result = rro.findByLroom(logInfo.getLroom());
            Mrooms temp = result.get(0);
            resOther =(String) temp.getRolr();
            if(temp.getRolr().equals("L")){
                logInfo.setRolr("R");
                sessionUpdate("newbie", "R");
            }else if(temp.getRolr().equals("R")){
                logInfo.setRolr("L");
                
            }
        }
        rro.save(logInfo); //lroom, rolr, btype, pname
        return resOther;        
    }
    public void readySitu(Mrooms mlog){
        rro.readySitu(mlog.isRore(), mlog.getPname());
    }

    public void recordIn(Mlog mlog){
        mlog.setLtype("ENTER");
        rl.save(mlog);
        // btype, lroom, pname,
    }
    public void recordOut(Mlog mlog){
        mlog.setLtype("EXIT");
        rl.save(mlog);
        leaveRoom(mlog);
    }
    public List<Mrooms> updatedReady(Mrooms mrooms){
        return rro.findByLroomAndBtype(mrooms.getLroom(),mrooms.getBtype());
    }
    public int checkRecentN(int lroom){
        return rl.checkRecentN(lroom);
    }

    public String checkRecentName(int lroom){
        return rl.checkRecentName(lroom);
    }    

    public String checkRecentType(int lroom){
        return rl.checkRecentType(lroom);
    }

    public List<Mlog> checkRecentT(int lroom){
        return rl.checkRecentT(lroom);
        // return lid, ltype
    }

    public String checkRecent(Mlog source){
        clearLog("ENTER", source.getPname());
        int cf = checkRecentN(source.getLroom());
        String result = "";
        if(cf>0){
            String cs = checkRecentType(source.getLroom());
            if(cs.equals("EXIT")){result="Y";}
            else if(cs.equals("ENTER")){
                List<Mlog> newlogList = new ArrayList<>();
                Mlog newlog = new Mlog();
                newlogList = checkRecentT(source.getLroom());
                newlog = newlogList.get(0);
                LocalDateTime ct = (LocalDateTime) newlog.getLcreatedat();
                int cn = (int) newlog.getLid();
                LocalDateTime currenDateTime = LocalDateTime.now();
                Duration duration = Duration.between(ct, currenDateTime);
                if (duration.toMinutes() > 1) {
                    clearLogAll("ENTER",source.getLroom());
                    updateType(cn);
                    result = "Y";
                }else{
                    result = "S";
                }
            }
            else{
                result="E";
            }
        }else{result = "Y";}
        return result;
    }

    public void updateType(int lid){
        rl.updateType(lid);
    }

    public void sessionUpdate(String sessionName, String sessionValue) {
		httpSession.setAttribute(sessionName, sessionValue);		
	}

}
