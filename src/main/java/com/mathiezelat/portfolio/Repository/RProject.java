package com.mathiezelat.portfolio.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiezelat.portfolio.Entity.Project;

@Repository
public interface RProject extends JpaRepository<Project, Integer> {
    public Optional<Project> findByName(String name);

    public boolean existsByName(String name);
}
