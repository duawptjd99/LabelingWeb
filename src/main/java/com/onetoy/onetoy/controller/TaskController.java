package com.onetoy.onetoy.controller;

import com.onetoy.onetoy.domain.Project.Project;
import com.onetoy.onetoy.service.LoginService;
import com.onetoy.onetoy.service.ProjectService;
import com.onetoy.onetoy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    @Autowired
    public TaskController( TaskService taskService,ProjectService projectService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }
    @GetMapping("/index/maintask")
    public String task(@RequestParam("name")String name, Model model){

        Project project = this.projectService.getSelectAllProject(this.projectService.getSelectProject(name));
        model.addAttribute("project",project);
        model.addAttribute("projectlist",this.projectService.getProjectNameList());

        return "task";
    }

    //@GetMapping("/i")

  //  @GetMapping("/index/task ")
}