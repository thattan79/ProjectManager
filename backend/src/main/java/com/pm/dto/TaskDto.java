package com.pm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private long taskId;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private String status;
    private boolean isParentTask;
    private long userId;
    private long projectId;
    private long parentTaskId;
    private ParentTaskDto parentTaskDto = new ParentTaskDto();
    private UserDto userDto = new UserDto();
    private ProjectDto projectDto = new ProjectDto();
}
