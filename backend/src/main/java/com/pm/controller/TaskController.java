package com.pm.controller;

import com.pm.dto.ParentTaskDto;
import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.entity.ParentTask;
import com.pm.entity.Task;
import com.pm.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequestMapping("/task")
@Transactional
public class TaskController {

    @Resource
    private ITaskService taskServiceImpl;

    @RequestMapping("/create")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.createTask(taskDto);
    }

    @RequestMapping("/update")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.updateTask(taskDto);
    }

    @RequestMapping("/updateTaskStatus")
    public TaskDto updateTaskStatus(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.updateTaskStatus(taskDto);
    }


    @RequestMapping("/createParent")
    public ParentTask createParentTask(@RequestBody ParentTaskDto parentTaskDto) {
        return taskServiceImpl.createParentTask(parentTaskDto);
    }

    @GetMapping("/findAllParent")
    public List<ParentTask> findAllParentTasks() {
        return taskServiceImpl.findAllParentTasks();
    }

    @GetMapping("/findAllParentTasksByInput/{input}")
    public List<ParentTask> findAllParentTasksByInput(@PathVariable("input") String input) {
        return taskServiceImpl.findAllParentTasksByInput(input);
    }

/*    @RequestMapping("/findAllTasksByProject/{input}")
    public List<ProjectDto> findAllTasksByProject(@PathVariable("input") String projectName) {
        return taskServiceImpl.findAllTasksByProject(projectName);
    }*/
}
