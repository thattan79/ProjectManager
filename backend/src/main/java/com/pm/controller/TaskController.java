package com.pm.controller;

import com.pm.dto.ParentTaskDto;
import com.pm.dto.TaskDto;
import com.pm.entity.ParentTask;
import com.pm.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/task")
@Transactional
public class TaskController {

    @Resource
    private ITaskService taskServiceImpl;

    @PostMapping("/create")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.createTask(taskDto);
    }

    @PostMapping("/update")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.updateTask(taskDto);
    }

    @PostMapping("/updateTaskStatus")
    public TaskDto updateTaskStatus(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.updateTaskStatus(taskDto);
    }


    @PostMapping("/createParent")
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
}
