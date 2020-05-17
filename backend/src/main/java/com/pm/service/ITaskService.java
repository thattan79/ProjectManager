package com.pm.service;

import com.pm.dto.ParentTaskDto;
import com.pm.dto.TaskDto;
import com.pm.entity.ParentTask;

import java.util.List;

public interface ITaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(TaskDto taskDto);

    TaskDto updateTaskStatus(TaskDto taskDto);

    ParentTask createParentTask(ParentTaskDto parentTaskDto);

    List<ParentTask> findAllParentTasks();

    List<ParentTask> findAllParentTasksByInput(String input);

}
