package com.pm.service;

import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
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
@Transactional
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private ProjectConverter projectConverter;
    @Resource
    private UserRepository userRepository;

    public ProjectDto createProject(ProjectDto projectDto) {
        final Project project = projectConverter.createProjectFromDto(projectDto);
        Optional<User> userOpt = userRepository.findById(projectDto.getUserId());
        User user = null;
        if (userOpt.isPresent()) {
            user = userOpt.get();
            project.addUser(user);
        }
        projectRepository.save(project);
        return projectDto;
    }

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

    public ProjectDto findProjectById(Long id) {
        Optional<Project> optProject = projectRepository.findById(id);
        if (optProject.isPresent()) {
            return projectConverter.convertProjectToDto(optProject.get());
        }
        return new ProjectDto();
    }

    @Transactional
    public ProjectDto deleteProject(Long id) {
        final Optional<Project> optProject = projectRepository.findById(id);
        optProject.ifPresent(project -> projectRepository.delete(optProject.get()));
        return new ProjectDto();
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
}
