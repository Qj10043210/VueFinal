package com.finals.globalprj.maple.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finals.globalprj.maple.model.*;

import com.finals.globalprj.maple.repository.*;

import jakarta.transaction.Transactional;

/**
 * SClient
 */
@Service
@Transactional
public class SClient {

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

    public String checkId(String reqId, String reqPwd) {
        int result = ra.checkId(reqId);
        if (result == 0) {
            return "0";
        } else {
            int result2 = ra.checkPwd(reqId, reqPwd);
            return result2 > 0 ? "1" : "2";
        }
    }

    public void signUp(String reqId, String reqPwd) {
        Maccounts newContent = new Maccounts();
        newContent.setName(reqId);
        newContent.setPassword(reqPwd);
        ra.save(newContent);
    }

    public List<Mplayers> tochar(String lid) {
        List<Mplayers> newContents;
        try {
            newContents = rp.toChar(lid);
            if (newContents == null || newContents.isEmpty()) {
                newContents = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            newContents = new ArrayList<>();
        }
        return newContents;
    }

    public String checkName(String reqName) {
        int result = rp.checkName(reqName);
        return String.valueOf(result);
    }

    public void postPlayer(Mplayers player) {
        System.out.println(player.getPstr());
        System.out.println("힘");
        System.out.println();
        double pmaxhp = getMaxhpWithStr(player.getPstr());
        player.setPmaxhp(pmaxhp);
        player.setPcurhp(pmaxhp);
        System.out.println(pmaxhp);
        rp.postPlayer(player.getName(),  player.getPcurhp(),
        player.getPdex(), player.getPface(), player.getPhair(), player.getPint(),
        player.getPluk(), player.getPmaxhp(), player.getPname(),
        player.getPstr());
        // name, pname, pmaxhp, pcurhp, pstr, pdex, pint, pluk, phair, pface
    }

    public String goodbye(String reqName) {
        int result = rp.goodbye(reqName);
        return result > 0 ? "q" : "n";

    }

    public List<Mplayers> getUserInfo(String pname) {
        List<Mplayers> newContents = new ArrayList<>();
        if (rp.getUserInfo(pname) != null) {
            newContents = rp.getUserInfo(pname);
        }
        return newContents;
    }

    public double getMaxhpWithStr(Integer pstr) {
        return 50 + pstr + pstr * Math.log10(pstr);
    }

    public double getExpBonus(Integer pluk) {
        return 1 + pluk * Math.log10(pluk);
    }

    public long giveDamage(Integer pdex) {
        return Math.round(1 + pdex + pdex * Math.log10(pdex) * Math.log10(pdex));
    }

    public double takeDamage(long damage, String pname) {
        List<Mplayers> newContents = getUserInfo(pname);
        try {
            int pint = newContents.get(0).getPint();
            double pcurhp = newContents.get(0).getPcurhp();
            long redDam = pint >= damage ? 0 : damage - pint;
            double result = pcurhp > redDam ? (double) pcurhp - redDam : 0;
            rp.updateHp(result, pname);
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    public void youWhat(String pname, String string) {
        switch (string) {
            case "rwin":
                rr.updateScoreWin(pname);
                int pluk = rp.getLuck(pname);
                double EXP = getExpBonus(pluk);
                rp.updateExp(EXP, pname);
                break;
            case "rlose":
                rr.updateScoreLose(pname);
                break;
            case "rdraw":
                rr.updateScoreDraw(pname);
                break;
        }

    }

    public void removePlayer(String pname) {
        int result = rp.goodbye(pname);
    }
}
