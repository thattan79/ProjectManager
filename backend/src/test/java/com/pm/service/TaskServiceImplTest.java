package com.pm.service;

import com.pm.dto.ParentTaskDto;
import com.pm.dto.TaskDto;
import com.pm.entity.ParentTask;
import com.pm.entity.Project;
import com.pm.entity.Task;
import com.pm.entity.User;
import com.pm.repository.ParentTaskRepository;
import com.pm.repository.ProjectRepository;
import com.pm.repository.TaskRepository;
import com.pm.repository.UserRepository;
import com.pm.utils.TaskConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ParentTaskRepository parentTaskRepository;
    @Mock
    private TaskConverter taskConverter;
    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    public void createParentTask() {
        when(parentTaskRepository.save(any())).thenReturn(mockParentTask());
        assertNotNull(taskServiceImpl.createParentTask(mockParentTaskDto()));
    }

    @Test
    public void findAllParentTasks() {
        when(parentTaskRepository.findAll()).thenReturn(Arrays.asList(mockParentTask()));
        assertNotNull(taskServiceImpl.findAllParentTasks());
    }

    @Test
    public void createTask() {
        when(taskConverter.createTaskFromDto(any())).thenReturn(mockTask());
        when(parentTaskRepository.findById(anyLong())).thenReturn(Optional.of(mockParentTask()));
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mockProject()));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser()));
        when(taskRepository.save(any())).thenReturn(mockTask());
        assertNotNull(taskServiceImpl.createTask(mockTaskDto()));
    }

    @Test
    public void updateTaskStatus() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(mockTask()));
        when(taskRepository.save(any())).thenReturn(mockTask());
        when(taskConverter.convertTaskToDto(any())).thenReturn(mockTaskDto());
        assertNotNull(taskServiceImpl.updateTaskStatus(mockTaskDto()));
    }

    @Test
    public void updateTaskStatusEmpty() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNull(taskServiceImpl.updateTaskStatus(mockTaskDto()));
    }

    @Test
    public void updateTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(mockTask()));
        when(taskRepository.save(any())).thenReturn(mockTask());
        assertNotNull(taskServiceImpl.updateTask(mockTaskDto()));
    }

    @Test
    public void findAllParentTasksByInput() {
        when(parentTaskRepository.findByParentTaskNameContaining(anyString())).thenReturn(Arrays.asList(mockParentTask()));
        assertNotNull(taskServiceImpl.findAllParentTasksByInput("test"));
    }

    @Test
    public void findAllParentTasksByInputDefault() {
        when(parentTaskRepository.findAll()).thenReturn(Arrays.asList(mockParentTask()));
        assertNotNull(taskServiceImpl.findAllParentTasksByInput("default"));
    }

    private ParentTaskDto mockParentTaskDto() {
        final ParentTaskDto parentTaskDto = new ParentTaskDto();
        parentTaskDto.setParentId(1);
        parentTaskDto.setParentTaskName("parentdto");
        return parentTaskDto;
    }

    private ParentTask mockParentTask() {
        final ParentTask parentTask = new ParentTask();
        parentTask.setParentId(1);
        parentTask.setParentTaskName("parent");
        return parentTask;
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
        return task;
    }

    private Project mockProject() {
        final Project project = new Project();
        project.setProjectTitle("Test");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPriority(1);
        return project;
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId("123");
        return user;
    }
}
