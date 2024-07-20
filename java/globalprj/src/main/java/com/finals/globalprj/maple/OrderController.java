package com.finals.globalprj.maple;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finals.globalprj.maple.services.*;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpSession;

import com.finals.globalprj.maple.model.*;
import com.finals.globalprj.maple.Mall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/maple/order")
public class OrderController {

    @Autowired
    SBoard sb;
    @Autowired
    SClient sc;
    @Autowired
    Sgame sg;
    @Autowired
    Slog sl;

    public long delayInMillis = 1500;

    @PostMapping("/client/login")
    public String login(@RequestParam("reqId") String reqId, @RequestParam("reqPwd") String reqPwd) {
        System.out.println("test");
        try {
            return sc.checkId(reqId, reqPwd);
        } catch (Exception e) {
            e.printStackTrace();

            return "3"; // Indicates an error
        }
        // 0:no id, 1 : login success, 2 : login fail, 3 : trouble
    }

    @PostMapping("/client/signin")
    public void signIn(@RequestParam("reqId") String reqId, @RequestParam("reqPwd") String reqPwd,
            HttpSession session) {
        try {
            session.setMaxInactiveInterval(60 * 60);
            session.setAttribute("loginedId", reqId);
            System.out.println(session.getAttribute("loginedId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/client/signup")
    public void signUp(@RequestParam("reqId") String reqId, @RequestParam("reqPwd") String reqPwd) {
        try {
            sc.signUp(reqId, reqPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/client/tochar")
    public List<Mplayers> tochar(HttpSession session) {
        System.out.println(session.getAttribute("loginedId"));
        List<Mplayers> mav = new ArrayList<>();
        try {
            if (session.getAttribute("loginedId") != null) {
                String lid = (String) session.getAttribute("loginedId");
                mav = sc.tochar(lid);

            } else {

            }
        } catch (Exception e) {

            e.printStackTrace();

        }
        return mav;

    }

    @PostMapping("/client/checkName")
    public String checkName(@RequestParam("reqName") String reqName) {
        try {
            return sc.checkName(reqName);
        } catch (Exception e) {

            e.printStackTrace();

            return "2"; // Indicates an error
        }
        // 0:no id, 1:there is duplicated id 2 : trouble
    }

    @PostMapping("/client/postPlayer")
    public void postPlayer(@RequestBody Mplayers datas, HttpSession session) {
        try {

            String lid = (String) session.getAttribute("loginedId");
            Mplayers newData = new Mplayers();
            newData.setName(lid);
            newData.setPdex(datas.getPdex());
            newData.setPint(datas.getPint());
            newData.setPluk(datas.getPluk());
            newData.setPstr(datas.getPstr());
            newData.setPhair(datas.getPhair());
            newData.setPface(datas.getPface());
            newData.setPname(datas.getPname());
            sc.postPlayer(newData);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    @PostMapping("/client/getUserInfo")
    public List<Mplayers> bringFaceHair(@RequestParam("pname") String p_name){
		
		List<Mplayers> mav = new ArrayList<>();
		try {
			mav = sc.getUserInfo(p_name);
		
		} catch (Exception e) {
	        
	        e.printStackTrace();
	     }
		
		return mav;
	}
    @PostMapping("/client/removePlayer")
    public void removePlayer(@RequestParam("pname") String p_name){
		try {
			sc.removePlayer(p_name);		
		} catch (Exception e) {	        
	        e.printStackTrace();
	     }
		
		
	}
    @PostMapping("/client/goodbye")
    public String goodbye(@RequestParam("reqName") String reqName) {
        try {
            return sc.goodbye(reqName);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return "n";
    }
    @PostMapping("/client/youLose")
    public void youLose(@RequestParam("pname") String p_name){
		sc.youWhat(p_name,"rlose");
	}
    @PostMapping("/client/youWin")
    public void youWin(@RequestParam("pname") String p_name){
		sc.youWhat(p_name,"rwin");
	}
    @PostMapping("/client/youDraw")
    public void youDraw(@RequestParam("pname") String p_name){
		sc.youWhat(p_name,"rdraw");
	}


    @GetMapping("/game/getScoreRSP")
    public List<Mall> getScoreRSP(HttpSession session) {
		List<Mall> mav = new ArrayList<>();
		try {
			if(session.getAttribute("joinedName")!=null){
				String lId = (String) session.getAttribute("joinedName");
				Mrsp rp = new Mrsp();
                Mplayers pp = new Mplayers();
                pp = sg.getScoreRSPP(lId);
                rp = sg.getScoreRSPR(lId);
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
        mav.add(all);
			}
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    	
	    }
		return mav;
		
	};
@PostMapping("/game/tryRoom")
public String tryRoom(@RequestBody Mlog sources, HttpSession session) {
    String result="";
    try {			
       result = sl.checkRecent(sources);
       if(result.equals("Y")) {
           session.setAttribute("newbie", "L");
       }else if(result.equals("S")) {
           session.setAttribute("newbie", "R");
       }
    } catch (Exception e) {
        
        e.printStackTrace();
        
        
    }
    return result;
};
@PostMapping("/game/enterRoom")
public String enterRoom(@RequestBody Mlog sources, HttpSession session) {		
    String result ="n";    
    try {
       session.setAttribute("loc", sources.getLroom());
       session.setAttribute("typ", sources.getBtype());
       Mrooms newRoom = new Mrooms();
       newRoom.setPname(sources.getPname());
       newRoom.setLroom(sources.getLroom());       
       sl.recordIn(sources);
       sl.joinRoom(newRoom, session);
       result ="w";
    } catch (Exception e) {        
        e.printStackTrace();
    }
    return result;
}
@PostMapping("/game/leaveRoom")
public void leaveRoom(@RequestBody Mrooms sources, HttpSession session) {
    try {
        Mlog newLog = new Mlog();
        newLog.setPname(sources.getPname());
        newLog.setLroom(sources.getLroom());
        newLog.setBtype(sources.getBtype());
        sl.recordOut(newLog);			
        sb.leaveBoard(newLog);        
    }catch (Exception e) {
        e.printStackTrace();
    }
}
@PostMapping("/game/outTimer")
public void outTimer(@RequestBody Mrooms sources, HttpSession session) {
    try {
        Mlog newLog = new Mlog();
        newLog.setPname(sources.getPname());
        newLog.setLroom(sources.getLroom());
        newLog.setBtype(sources.getBtype());
        sl.recordOut(newLog);			
        sb.leaveBoard(newLog);
        session.invalidate();
    }catch (Exception e) {
        e.printStackTrace();
    }
}
@PostMapping("/game/insertHand")
public void insertHand(@RequestBody Mgame sources) {
    try {
        sg.insertHand(sources);
    }catch (Exception e) {        
        e.printStackTrace();
    }
}
@PostMapping("/game/giveHand")
@Async
	public Map<String, Object> giveHand(@RequestBody Mgame sources) {
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
		try {
			Thread.sleep(delayInMillis);
			List<Mgame> temp = sg.giveHand(sources);			
			if(temp.size() > 1) {
				Mall temp2 = new Mall();
				if(temp.get(0).getRolr().equals("L")) {
					temp2.setHl(temp.get(0).getGhand());
					temp2.setHr(temp.get(1).getGhand());
					resultMap.put("leftHand", temp.get(0).getGhand());
					resultMap.put("rightHand", temp.get(1).getGhand());
					resultMap.put("leftMan", temp.get(0).getPname());
					resultMap.put("rightMan", temp.get(1).getPname());
				}else if(temp.get(0).getRolr().equals("R")) {
					temp2.setHl(temp.get(1).getGhand());
					temp2.setHr(temp.get(0).getGhand());
					resultMap.put("leftHand", temp.get(1).getGhand());
					resultMap.put("rightHand", temp.get(0).getGhand());
					resultMap.put("leftMan", temp.get(1).getPname());
					resultMap.put("rightMan", temp.get(0).getPname());
				}
				result = sg.getResultRSP(temp2);
				resultMap.put("result", result);
				
			}else {
				resultMap.put("result", "fail");
			}
			
		}catch (InterruptedException e) {
            Thread.currentThread().interrupt();

	        
	        
	    }
		return resultMap;
	}

    @PostMapping("/game/returnValueDamage")
    @Transactional
	public long returnValueDamaage(@RequestParam("pdex") int p_dex) {
		return sc.giveDamage(p_dex);
	}

    @PostMapping("/game/returnValueCurhp")
    @Transactional
	public Double returnValueCurHp(@RequestParam("pname") String p_name, @RequestParam("damage") long damage) {
		Double result = sc.takeDamage(damage, p_name);		
		return result; 
	}

    @PostMapping("/board/getBoard")
    public List<Mboard> getBoardRSP(@RequestParam("reqN") String reqNs) {
		List<Mboard> mav = new ArrayList<>();
		try {
			int reqN = Integer.parseInt(reqNs);
			mav = sb.getBoard(reqN);
		}
		catch (Exception e) {
        
    	e.printStackTrace();
    	
    }
	return mav;
	}

    @PostMapping("/board/updateBoard")
    public void updateBoard(@RequestBody Mboard sources) {
		try {			
	        sb.updateBoard(sources);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
	}
    @PostMapping("/board/getCoonectedUser")
    public List<Mrooms> getCoonectedUser(@RequestBody Mrooms sources) {
		List<Mrooms> nav = new ArrayList<>();
		try {			
	        nav = sg.getCoonectedUser(sources);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        
	        
	    }
		return nav;
	}

    @PostMapping("/lg/loadReadyPlayer")
    public void loadReadyPlayer(@RequestParam("emptyS") String empty) {		
		try {			
			sg.loadReadyPlayer();
		}
		catch (Exception e) {        
    	e.printStackTrace();    	
		}
	}

    @PostMapping("/lg/readySitu")
    public void readySitu(@RequestBody Mrooms loginfo) {
		
		String test = loginfo.getPname();
		
		if(test != null && test != "") {			
			sl.readySitu(loginfo);
		}
	}
    @PostMapping("/lg/updateReady")
    public List<Mrooms> updateReady(@RequestBody Mrooms sources) {
		List<Mrooms> nav = new ArrayList<>();
		try {			
			nav = sl.updatedReady(sources);
		}
		catch (Exception e) {
        
    	e.printStackTrace();
    	
		}
		return nav;
		
	}
}
