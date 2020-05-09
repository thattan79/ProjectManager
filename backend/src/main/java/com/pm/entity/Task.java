package com.pm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TASK_TABLE")
public class Task {
    @Id
    @SequenceGenerator(name = "taskSeqGen", sequenceName = "taskSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "taskSeqGen")
    private long taskId;

    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private String status;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonManagedReference
    private Project project;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    private ParentTask parentTask;
}
