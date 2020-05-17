package com.pm.service;

import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.dto.UserDto;
import com.pm.entity.Project;
import com.pm.entity.User;
import com.pm.repository.ProjectRepository;
import com.pm.repository.UserRepository;
import com.pm.utils.ProjectConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @InjectMocks
    private ProjectServiceImpl projectServiceImpl;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectConverter projectConverter;

    @Test
    public void createProject() {
        when(projectConverter.createProjectFromDto(any())).thenReturn(mockProject());
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser()));
        when(projectRepository.save(any())).thenReturn(any());
        projectServiceImpl.createProject(mockProjectDto());
        verify(projectRepository, times(1)).save(any());
        verify(projectConverter, times(1)).createProjectFromDto(any());
    }

    @Test
    public void findAllProjects() {
        when(projectConverter.convertProjectListToDtoList(anyList())).thenReturn(Arrays.asList(mockProjectDtoWithoutTask()));
        assertNotNull(projectServiceImpl.findAllProjects());
    }

    @Test
    public void findAllProjectByInputDefault() {
        when(projectConverter.convertProjectListToDtoList(anyList())).thenReturn(Arrays.asList(mockProjectDto()));
        assertNotNull(projectServiceImpl.findAllProjectByInput("default"));
    }

    @Test
    public void findAllProjectByInputValue() {
        when(projectConverter.convertProjectListToDtoList(anyList())).thenReturn(Arrays.asList(mockProjectDto()));
        assertNotNull(projectServiceImpl.findAllProjectByInput("test"));
    }

    @Test
    public void findAllProjectByInputUndefined() {
        assertNotNull(projectServiceImpl.findAllProjectByInput("undefined"));
    }

    @Test
    public void findProjectById() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mockProject()));
        projectServiceImpl.findProjectById(anyLong());
        verify(projectConverter, times(1)).convertProjectToDto(any());
    }

    @Test
    public void findProjectByIdEmpty() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNotNull(projectServiceImpl.findProjectById(anyLong()));
    }

    @Test
    public void deleteProjectIdNotExist() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNotNull(projectServiceImpl.deleteProject(10L));
    }

    @Test
    public void deleteProjectIdExist() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mockProject()));
        doNothing().when(projectRepository).delete(isA(Project.class));
        assertNotNull(projectServiceImpl.deleteProject(10L));
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId("123");
        return user;
    }

    private UserDto mockUserDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmployeeId("123");
        return userDto;
    }

    private ProjectDto mockProjectDtoWithoutTask() {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectTitle("Test");
        projectDto.setStartDate(LocalDate.now());
        projectDto.setEndDate(LocalDate.now());
        projectDto.setPriority(1);
        projectDto.setUserId(1);
        return projectDto;
    }

    private ProjectDto mockProjectDto() {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectTitle("Test");
        projectDto.setStartDate(LocalDate.now());
        projectDto.setEndDate(LocalDate.now());
        projectDto.setPriority(1);
        projectDto.setUserId(1);
        projectDto.setTaskDtos(Arrays.asList(mockTaskDto()));
        return projectDto;
    }

    private Project mockProject() {
        final Project project = new Project();
        project.setProjectTitle("Test");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPriority(1);
        return project;
    }

    private TaskDto mockTaskDto() {
        final TaskDto taskDto = new TaskDto();
        taskDto.setEndDate(LocalDate.now());
        taskDto.setStartDate(LocalDate.now());
        taskDto.setPriority(1);
        taskDto.setTaskName("Task");
        taskDto.setStatus("COMPLETE");
        return taskDto;
    }
}
