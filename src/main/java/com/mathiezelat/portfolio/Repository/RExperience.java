package com.mathiezelat.portfolio.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiezelat.portfolio.Entity.Experience;

@Repository
public interface RExperience extends JpaRepository<Experience, Integer> {
    public Optional<Experience> findByName(String name);

    public boolean existsByName(String name);
}
