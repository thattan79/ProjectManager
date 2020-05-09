package com.pm.service;

import com.pm.dto.*;
import com.pm.entity.*;
import com.pm.repository.*;
import com.pm.utils.TaskConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements ITaskService {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private ParentTaskRepository parentTaskRepository;
    @Resource
    private TaskConverter taskConverter;

    @Transactional
    public ParentTask createParentTask(ParentTaskDto parentTaskDto) {
        final ParentTask parentTask = new ParentTask();
        parentTask.setParentTaskName(parentTaskDto.getParentTaskName());
        parentTaskRepository.save(parentTask);
        return parentTask;
    }

    public List<ParentTask> findAllParentTasks() {
        return parentTaskRepository.findAll();
    }

    public TaskDto createTask(TaskDto taskDto) {
        log.info("-createTask-");
        return saveTask(taskDto);
    }

    public TaskDto updateTaskStatus(TaskDto taskDto) {
        log.info("-updateTaskStatus-");
        Optional<Task> optTask = taskRepository.findById(taskDto.getTaskId());
        if (optTask.isPresent()) {
            final Task task = optTask.get();
            task.setStatus("COMPLETE");
            taskRepository.save(task);
            return taskConverter.convertTaskToDto(task);
        }
        return null;
    }

    public TaskDto updateTask(TaskDto taskDto) {
        log.info("-updateTask-");
        if (taskDto.getTaskId() > 0) {
            Optional<Task> optTask = taskRepository.findById(taskDto.getTaskId());
            if (optTask.isPresent()) {
                final Task task = optTask.get();
                task.setTaskName(taskDto.getTaskName());
                task.setStartDate(taskDto.getStartDate());
                task.setEndDate(taskDto.getEndDate());
                task.setPriority(taskDto.getPriority());
                taskRepository.save(task);
            }
        }
        return taskDto;
    }

    public List<ParentTask> findAllParentTasksByInput(String input) {
        if ("default".equals(input)) {
            return findAllParentTasks();
        }
        return parentTaskRepository.findByParentTaskNameContaining(input);
    }

    private TaskDto saveTask(TaskDto taskDto) {
        List<Task> allTasks = new ArrayList<>();
        final Task task = taskConverter.createTaskFromDto(taskDto);

        Optional<ParentTask> optParentTask = parentTaskRepository.findById(taskDto.getParentTaskId());
        if (optParentTask.isPresent()) {
            final ParentTask parentTask = optParentTask.get();
            task.setParentTask(parentTask);
            parentTask.setTasks(allTasks);
        }
        allTasks.add(task);

        Project project = null;
        Optional<Project> optProject = projectRepository.findById(taskDto.getProjectId());
        if (optProject.isPresent()) {
            project = optProject.get();
            task.setProject(project);
            project.setTasks(allTasks);
        }

        User user = null;
        Optional<User> optUser = userRepository.findById(taskDto.getUserId());
        if (optUser.isPresent()) {
            user = optUser.get();
            task.setUser(user);
            user.setTask(task);
        }

        if (project != null && user != null) {
            user.setProject(project);
        }
        taskRepository.save(task);
        return taskDto;
    }
}
