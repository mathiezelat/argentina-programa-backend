package com.mathiezelat.portfolio.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiezelat.portfolio.Entity.Education;

@Repository
public interface REducation extends JpaRepository<Education, Integer> {
    public Optional<Education> findByName(String name);

    public boolean existsByName(String name);
}
