package com.pm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private long projectId;
    public String projectTitle;
    public LocalDate startDate;
    public LocalDate endDate;
    public int priority;
    public long userId;
    public List<UserDto> userDtos = new ArrayList<>();
    public List<TaskDto> taskDtos = new ArrayList<>();
    public int totalNoOfTasks;
    public int totalNoOfCompletedTasks;
}
