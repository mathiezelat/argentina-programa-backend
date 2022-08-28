package com.mathiezelat.portfolio.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathiezelat.portfolio.Entity.Experience;
import com.mathiezelat.portfolio.Repository.RExperience;

@Service
@Transactional
public class SExperiencie {
    @Autowired
    RExperience rExperience;

    public List<Experience> list() {
        return rExperience.findAll();
    }

    public Optional<Experience> getOne(int id) {
        return rExperience.findById(id);
    }

    public Optional<Experience> getByName(String name) {
        return rExperience.findByName(name);
    }

    public void save(Experience exp) {
        rExperience.save(exp);
    }

    public void delete(int id) {
        rExperience.deleteById(id);
    }

    public boolean existsById(int id) {
        return rExperience.existsById(id);
    }

    public boolean existsByName(String name) {
        return rExperience.existsByName(name);
    }
}
