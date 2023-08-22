package kr.or.iei.adminPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="adminPage")
public class AdminPageController {
    
    @GetMapping(value="/cssTest")
    public String cssTest() {
        return "adminPage/Donghyo_adminPageList";
    }//cssTest()
    
}//AdminPageController
