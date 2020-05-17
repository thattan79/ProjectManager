package com.pm.service;

import com.pm.dto.ProjectDto;

import java.util.List;

public interface IProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    List<ProjectDto> findAllProjects();

    List<ProjectDto> findAllProjectByInput(String input);

    ProjectDto findProjectById(Long id);

    ProjectDto deleteProject(Long id);
}
