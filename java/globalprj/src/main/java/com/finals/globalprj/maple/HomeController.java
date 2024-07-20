package com.finals.globalprj.maple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/maple/home")
    public String homePage() {
        System.out.println("test중");
        return "home"; // 타임리프 파일명 (확장자 .html은 생략)
    }
    @GetMapping("/maple/index")
    public String indexPage() {
        System.out.println("index로옮ㄱ미");
        return "index"; // 타임리프 파일명 (확장자 .html은 생략)
    }
    @GetMapping("/maple/gameopen")
    public String gameopen(@RequestParam("data") String data, 
            @RequestParam("names") String names, 
            HttpSession session){
                try {
                    if (session.getAttribute("loginedId") != null) {
                        session.setAttribute("joinedName", names);
                        return ""+data;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "index";
                }
                return "index";
            }

    @GetMapping("/maple/room")
    public String room(@RequestParam("num") String num){
        try {
            return "room"+num;
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
    }
    @GetMapping("/maple/dead")
    public String dead(HttpSession session){
        try {
            session.invalidate();
            return "dead";
        } catch (Exception e) {
            e.printStackTrace();
            return "dead";
        }
    }
}
