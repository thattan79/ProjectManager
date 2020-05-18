package com.pm.repository;

import com.pm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameContaining(String input);

    List<User> findByLastNameContaining(String input);

    List<User> findByEmployeeIdContaining(String input);

    @Query("select u from User u where u.project.projectId=:id")
    List<User> findByProjectId(Long id);

    User findByEmployeeId(Long id);
}

