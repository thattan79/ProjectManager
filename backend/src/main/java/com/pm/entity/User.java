package com.pm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class User {
    @Id
    @SequenceGenerator(name = "userSeqGen", sequenceName = "userSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "userSeqGen")
    private long userId;
    private String firstName;
    private String lastName;
    private String employeeId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Project project;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "task_id")
    @JsonManagedReference
    private Task task;

}
