package com.pm.service;

import com.pm.dto.ParentTaskDto;
import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.dto.TaskUserDto;
import com.pm.entity.ParentTask;
import com.pm.entity.Project;
import com.pm.entity.Task;

import java.util.List;

public interface ITaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(TaskDto taskDto);

    TaskDto updateTaskStatus(TaskDto taskDto);

    ParentTask createParentTask(ParentTaskDto parentTaskDto);

    List<ParentTask> findAllParentTasks();

    List<ParentTask> findAllParentTasksByInput(String input);

/*    List<ProjectDto> findAllTasksByProject(String input);*/

}
