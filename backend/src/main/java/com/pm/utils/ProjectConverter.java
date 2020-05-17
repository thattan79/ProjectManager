package com.pm.utils;


import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.dto.UserDto;
import com.pm.entity.Project;
import com.pm.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectConverter {

    @Resource
    private TaskConverter taskConverter;

    @Resource
    private UserConverter userConverter;

    public Project createProjectFromDto(ProjectDto projectDto) {
        final Project project = new Project();
        project.setProjectTitle(projectDto.getProjectTitle());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setPriority(projectDto.getPriority());
        if (projectDto.getProjectId() > 0) {
            project.setProjectId(projectDto.getProjectId());
        }
        return project;
    }

    public ProjectDto convertProjectToDto(Project project) {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectTitle(project.getProjectTitle());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setPriority(project.getPriority());
        if (project.getProjectId() > 0) {
            projectDto.setProjectId(project.getProjectId());
        }
        List<User> users = project.getUsers();
        if (users != null && !users.isEmpty()) {
            List<UserDto> userDtos = userConverter.convertUserListToDtoList(users);
            projectDto.setUserDtos(userDtos);
        }
        return projectDto;
    }

    public List<ProjectDto> convertProjectListToDtoList(List<Project> projects) {
        List<ProjectDto> projectDtos = new ArrayList<>();
        projects.stream().map(project -> {
            final ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectTitle(project.getProjectTitle());
            projectDto.setStartDate(project.getStartDate());
            projectDto.setEndDate(project.getEndDate());
            projectDto.setPriority(project.getPriority());
            if (project.getProjectId() > 0) {
                projectDto.setProjectId(project.getProjectId());
            }
            if (project.getTasks() != null && !project.getTasks().isEmpty()) {
                List<TaskDto> taskDtos = taskConverter.convertTaskListToDtoList(project.getTasks());
                projectDto.setTaskDtos(taskDtos);
            }
            projectDtos.add(projectDto);
            return projectDtos;
        }).collect(Collectors.toList());
        return projectDtos;
    }
}
