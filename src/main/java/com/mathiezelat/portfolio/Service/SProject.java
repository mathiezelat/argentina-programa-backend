package com.mathiezelat.portfolio.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathiezelat.portfolio.Entity.Project;
import com.mathiezelat.portfolio.Repository.RProject;

@Service
@Transactional
public class SProject {
    @Autowired
    RProject rProject;

    public List<Project> list() {
        return rProject.findAll();
    }

    public Optional<Project> getOne(int id) {
        return rProject.findById(id);
    }

    public Optional<Project> getByName(String name) {
        return rProject.findByName(name);
    }

    public void save(Project project) {
        rProject.save(project);
    }

    public void delete(int id) {
        rProject.deleteById(id);
    }

    public boolean existsById(int id) {
        return rProject.existsById(id);
    }

    public boolean existsByName(String name) {
        return rProject.existsByName(name);
    }
}
