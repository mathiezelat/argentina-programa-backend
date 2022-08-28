package com.mathiezelat.portfolio.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiezelat.portfolio.Entity.Technology;

@Repository
public interface RTechnology extends JpaRepository<Technology, Integer> {
    public Optional<Technology> findByName(String name);

    public boolean existsByName(String name);
}
