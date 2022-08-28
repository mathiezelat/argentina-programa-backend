package com.mathiezelat.portfolio.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathiezelat.portfolio.Entity.Technology;
import com.mathiezelat.portfolio.Repository.RTechnology;

@Service
@Transactional
public class STechnology {
    @Autowired
    RTechnology rTechnology;

    public List<Technology> list() {
        return rTechnology.findAll();
    }

    public Optional<Technology> getOne(int id) {
        return rTechnology.findById(id);
    }

    public Optional<Technology> getByName(String name) {
        return rTechnology.findByName(name);
    }

    public void save(Technology technology) {
        rTechnology.save(technology);
    }

    public void delete(int id) {
        rTechnology.deleteById(id);
    }

    public boolean existsById(int id) {
        return rTechnology.existsById(id);
    }

    public boolean existsByName(String name) {
        return rTechnology.existsByName(name);
    }
}
