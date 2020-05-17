package com.pm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParentTask {
    @Id
    @SequenceGenerator(name = "parentSeqGen", sequenceName = "parentSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "parentSeqGen")
    private long parentId;

    private String parentTaskName;

    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Task> tasks;
}
