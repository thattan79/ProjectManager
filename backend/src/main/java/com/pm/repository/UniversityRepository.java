package com.pm.repository;

import com.pm.entity.Project;
import com.pm.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
