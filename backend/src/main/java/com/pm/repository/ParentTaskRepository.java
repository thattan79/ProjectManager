package com.pm.repository;

import com.pm.dto.ParentTaskDto;
import com.pm.entity.ParentTask;
import com.pm.entity.Project;
import com.pm.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {
    List<ParentTask> findByParentTaskNameContaining(String input);
}

