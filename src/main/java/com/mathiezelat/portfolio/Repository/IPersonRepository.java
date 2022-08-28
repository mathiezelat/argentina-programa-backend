package com.mathiezelat.portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiezelat.portfolio.Entity.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {

}
