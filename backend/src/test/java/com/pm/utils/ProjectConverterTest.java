package com.pm.utils;

import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.dto.UserDto;
import com.pm.entity.Project;
import com.pm.entity.Task;
import com.pm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectConverterTest {

    @InjectMocks
    private ProjectConverter projectConverter;

    @Mock
    private UserConverter userConverter;

    @Mock
    private TaskConverter taskConverter;

    @Test
    public void createProjectFromDto() {
        assertNotNull(projectConverter.createProjectFromDto(mockProjectDto()));
    }

    @Test
    public void convertProjectToDto() {
        when(userConverter.convertUserListToDtoList(anyList())).thenReturn(Arrays.asList(mockUserDto()));
        assertNotNull(projectConverter.convertProjectToDto(mockProject()));
    }

    @Test
    public void convertProjectListToDtoList() {
        when(taskConverter.convertTaskListToDtoList(anyList())).thenReturn(Arrays.asList(mockTaskDto()));
        assertNotNull(projectConverter.convertProjectListToDtoList(Arrays.asList(mockProject())));
    }

    private ProjectDto mockProjectDto() {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectTitle("Test");
        projectDto.setStartDate(LocalDate.now());
        projectDto.setEndDate(LocalDate.now());
        projectDto.setPriority(1);
        projectDto.setUserId(1);
        projectDto.setProjectId(1);
        return projectDto;
    }

    private Project mockProject() {
        final Project project = new Project();
        project.setProjectId(1);
        project.setProjectTitle("Test");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPriority(1);
        project.setTasks(Arrays.asList(mockTask()));
        project.setUsers(Arrays.asList(mockUser()));
        return project;
    }

    private Task mockTask() {
        final Task task = new Task();
        task.setTaskId(1);
        task.setEndDate(LocalDate.now());
        task.setStartDate(LocalDate.now());
        task.setPriority(1);
        task.setTaskName("Task");
        task.setStatus("COMPLETE");
        return task;
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId(123l);
        return user;
    }

    private UserDto mockUserDto() {
        final UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmployeeId(123l);
        return userDto;
    }

    private TaskDto mockTaskDto() {
        final TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(1);
        taskDto.setEndDate(LocalDate.now());
        taskDto.setStartDate(LocalDate.now());
        taskDto.setPriority(1);
        taskDto.setTaskName("TaskDto");
        taskDto.setStatus("COMPLETE");
        return taskDto;
    }

}
