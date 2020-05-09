package com.pm.service;

import com.pm.dto.ProjectDto;
import com.pm.dto.ProjectManagerDto;
import com.pm.entity.Project;

import java.util.List;
import java.util.Optional;


public interface IProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    List<ProjectDto> findAllProjects();

    List<ProjectDto> findAllProjectByInput(String input);

    ProjectDto findProjectById(Long id);

    ProjectDto deleteProject(Long id);
}
