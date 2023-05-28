package com.onetoy.onetoy.controller;

import com.onetoy.onetoy.domain.Account;
import com.onetoy.onetoy.service.MainService;
import com.onetoy.onetoy.service.ProjectService;
import com.onetoy.onetoy.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final MainService mainService;
    private final ProjectService projectService;

    @Autowired
    public MainController(MainService mainService,ProjectService projectService) {
        this.mainService = mainService;
        this.projectService = projectService;
    }

    @GetMapping("/index")
    public String main(HttpSession session, Model model) {
        //로그인 정보 보내기
        model.addAttribute("accountname",this.mainService.checkAccount((Long) session.getAttribute("accountuid")).getName());
        //프로젝트 리스트 보내기
        model.addAttribute("projectlist",this.projectService.getProjectNameList());
        return "/index";
    }

    /////////Projects////////
    @GetMapping("/index/newproject")
    public String newProject(Model model) {

        model.addAttribute("projectlist",this.projectService.getProjectNameList());
        return "newproject";
    }

    @GetMapping("/index/accountmanagement")
    public String accountManagement(Model model)
    {  model.addAttribute("projectlist",this.projectService.getProjectNameList());
        return "accountManagement";
    }



}
