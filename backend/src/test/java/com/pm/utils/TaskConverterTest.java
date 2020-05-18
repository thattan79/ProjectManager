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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskConverterTest {

    @InjectMocks
    private TaskConverter taskConverter;

    @Mock
    private UserConverter userConverter;

    @Mock
    private ProjectConverter projectConverter;

    @Test
    public void createTaskFromDto() {
        assertNotNull(taskConverter.createTaskFromDto(mockTaskDto()));
    }

    @Test
    public void convertTaskListToDtoList() {
        when(userConverter.convertUserToDto(any())).thenReturn(mockUserDto());
        when(projectConverter.convertProjectToDto(any())).thenReturn(mockProjectDto());
        assertNotNull(taskConverter.convertTaskListToDtoList(Arrays.asList(mockTask())));
    }

    @Test
    public void convertTaskToDto() {
        assertNotNull(taskConverter.convertTaskToDto(mockTask()));
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

    private Task mockTask() {
        final Task task = new Task();
        task.setTaskId(1);
        task.setEndDate(LocalDate.now());
        task.setStartDate(LocalDate.now());
        task.setPriority(1);
        task.setTaskName("Task");
        task.setStatus("COMPLETE");
        task.setUser(new User());
        task.setProject(new Project());
        return task;
    }

    private UserDto mockUserDto() {
        final UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmployeeId(123l);
        return userDto;
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId(123l);
        return user;
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
}
