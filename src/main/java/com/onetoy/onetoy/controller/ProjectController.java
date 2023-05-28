package com.onetoy.onetoy.controller;

import com.onetoy.onetoy.Form.ProjectForm;
import com.onetoy.onetoy.service.MainService;
import com.onetoy.onetoy.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/createproject")
    public String createProject(HttpSession session,ProjectForm projectForm) throws IOException {
       if( this.projectService.createProject((Long) session.getAttribute("accountuid"),projectForm.getProjectName(),projectForm.getSimpleExplain(),projectForm.getClassNameList()
        ,projectForm.getShapeList(),projectForm.getImageZipFile())){
           System.out.println("success");
       }else{
           System.out.println("fail");
       }
        return "redirect:/index";
    }
}
