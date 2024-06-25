package com.furkanreyhan.SimpleLogin.controller;

import com.furkanreyhan.SimpleLogin.entity.Project;
import com.furkanreyhan.SimpleLogin.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);

    }

    @PutMapping("/assignUser")
    public Project assignUsersToProject(@RequestParam Long projectId, @RequestParam Long userId){
        return projectService.assignUsersToProject(projectId, userId);
    }


}
