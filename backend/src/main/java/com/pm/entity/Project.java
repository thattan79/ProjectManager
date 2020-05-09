package com.pm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROJECT_TABLE")
public class Project {
    @Id
    @SequenceGenerator(name = "projectSeqGen", sequenceName = "projectSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "projectSeqGen")
    private long projectId;

    private String projectTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.setProject(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setProject(null);
    }
}
