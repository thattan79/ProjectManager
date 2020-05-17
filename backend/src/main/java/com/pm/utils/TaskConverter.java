package com.pm.utils;

import com.pm.dto.ParentTaskDto;
import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.dto.UserDto;
import com.pm.entity.ParentTask;
import com.pm.entity.Project;
import com.pm.entity.Task;
import com.pm.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter {

    @Resource
    private UserConverter userConverter;
    @Resource
    private ProjectConverter projectConverter;

    public Task createTaskFromDto(TaskDto taskDto) {
        final Task task = new Task();
        task.setTaskName(taskDto.getTaskName());
        task.setStartDate(taskDto.getStartDate());
        task.setEndDate(taskDto.getEndDate());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setStatus("START");
        if (taskDto.getTaskId() > 0) {
            task.setTaskId(taskDto.getTaskId());
        }
        return task;
    }

    public List<TaskDto> convertTaskListToDtoList(List<Task> tasks) {
        List<TaskDto> taskDtos = new ArrayList<>();
        tasks.stream().map(task -> {
            final TaskDto taskDto = new TaskDto();
            taskDto.setEndDate(task.getEndDate());
            taskDto.setStartDate(task.getStartDate());
            taskDto.setPriority(task.getPriority());
            taskDto.setTaskName(task.getTaskName());
            taskDto.setStatus(task.getStatus());
            taskDto.setTaskId(task.getTaskId());
            ParentTask parentTask = task.getParentTask();
            if (parentTask != null) {
                final ParentTaskDto dto = new ParentTaskDto();
                dto.setParentId(parentTask.getParentId());
                dto.setParentTaskName(parentTask.getParentTaskName());
                taskDto.setParentTaskDto(dto);
            }
            User user = task.getUser();
            if (user != null) {
                final UserDto userDto = userConverter.convertUserToDto(user);
                taskDto.setUserDto(userDto);
            }

            Project project = task.getProject();
            if (project != null) {
                final ProjectDto projectDto = projectConverter.convertProjectToDto(project);
                taskDto.setProjectDto(projectDto);
            }
            taskDtos.add(taskDto);
            return taskDtos;
        }).collect(Collectors.toList());
        return taskDtos;
    }

    public TaskDto convertTaskToDto(Task task) {
        final TaskDto taskDto = new TaskDto();
        taskDto.setTaskName(task.getTaskName());
        taskDto.setStartDate(task.getStartDate());
        taskDto.setEndDate(task.getEndDate());
        taskDto.setPriority(task.getPriority());
        taskDto.setStatus(task.getStatus());
        if (task.getTaskId() > 0) {
            taskDto.setTaskId(task.getTaskId());
        }
        return taskDto;
    }
}
