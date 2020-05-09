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
    public String taskName;
    public LocalDate startDate;
    public LocalDate endDate;
    public int priority;
    public String status;
    public boolean isParentTask;
    public long userId;
    private long projectId;
    private long parentTaskId;
    private ParentTaskDto parentTaskDto = new ParentTaskDto();
    private UserDto userDto = new UserDto();
    private ProjectDto projectDto = new ProjectDto();
}
