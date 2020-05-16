package com.pm.controller;

import com.pm.dto.*;
import com.pm.service.IProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private IProjectService projectServiceImpl;

    @PostMapping("/create")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        return projectServiceImpl.createProject(projectDto);
    }

    @GetMapping("/findAllProjects")
    public List<ProjectDto> findAllProjects() {
        return projectServiceImpl.findAllProjects();
    }

    @GetMapping("/findAllProjectByInput/{input}")
    public List<ProjectDto> findAllProjectByInput(@PathVariable("input") String input) {
        return projectServiceImpl.findAllProjectByInput(input);
    }

    @GetMapping("/findProjectById/{id}")
    public ProjectDto findProjectById(@PathVariable("id") Long id) {
        return projectServiceImpl.findProjectById(id);
    }

    @PostMapping("/delete/{id}")
    public ProjectDto deleteProject(@PathVariable("id") Long id) {
        return projectServiceImpl.deleteProject(id);
    }
}
