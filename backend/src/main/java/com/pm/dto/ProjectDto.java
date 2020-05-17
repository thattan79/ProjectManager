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
    private String projectTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private long userId;
    private List<UserDto> userDtos = new ArrayList<>();
    private List<TaskDto> taskDtos = new ArrayList<>();
    private int totalNoOfTasks;
    private int totalNoOfCompletedTasks;
}
