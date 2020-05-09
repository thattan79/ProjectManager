package com.pm.service;

import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.dto.UserDto;
import com.pm.entity.Project;
import com.pm.entity.User;
import com.pm.repository.ProjectRepository;
import com.pm.repository.UserRepository;
import com.pm.utils.ProjectConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private ProjectConverter projectConverter;
    @Resource
    private UserRepository userRepository;

    @Transactional
    public ProjectDto createProject(ProjectDto projectDto) {
        final Project project = projectConverter.saveProject(projectDto);
        Optional<User> userOpt = userRepository.findById(projectDto.getUserId());
        User user = null;
        UserDto userDto = null;
        if (userOpt.isPresent()) {
            user = userOpt.get();
            project.addUser(user);
        }
        projectRepository.save(project);
        return projectDto;
    }

    @Transactional
    public List<ProjectDto> findAllProjects() {
        List<ProjectDto> projectDtos = projectConverter.convertProjectListToDtoList(projectRepository.findAll());
        setCountOfTask(projectDtos);
        return projectDtos;
    }

    public List<ProjectDto> findAllProjectByInput(String input) {
        if ("default".equals(input)) {
            return findAllProjects();
        } else if (!"undefined".equals(input)) {
            List<ProjectDto> projectDtos = projectConverter.convertProjectListToDtoList(projectRepository.findByProjectTitleContaining(input));
            setCountOfTask(projectDtos);
            return projectDtos;
        }
        return new ArrayList<>();
    }

    public ProjectDto findById(Long id) {
        Optional<Project> pr = projectRepository.findById(id);
        Project project = pr.get();
        ProjectDto dto = new ProjectDto();
        dto.setProjectTitle(project.getProjectTitle());
        dto.setPriority(project.getPriority());
        dto.setEndDate(project.getEndDate());
        dto.setStartDate(project.getStartDate());
        dto.setProjectId(project.getProjectId());
        return dto;
    }

    private void setCountOfTask(List<ProjectDto> projectDtos) {
        for (ProjectDto projectDto : projectDtos) {
            List<TaskDto> taskDtos = projectDto.getTaskDtos();
            if (taskDtos != null && taskDtos.size() > 0) {
                projectDto.setTotalNoOfTasks(taskDtos.size());
                int noOfCompletedTasks = 0;
                for (TaskDto taskDto : taskDtos) {
                    if ("COMPLETE".equals(taskDto.getStatus())) {
                        noOfCompletedTasks = noOfCompletedTasks + 1;
                    }
                    projectDto.setTotalNoOfCompletedTasks(noOfCompletedTasks);
                }
            }
        }
    }

    public ProjectDto deleteProject(Long id) {
        final Optional<Project> optProject = projectRepository.findById(id);
        if (optProject.isPresent()) {
            projectRepository.delete(optProject.get());
        }
        return new ProjectDto();
    }
}