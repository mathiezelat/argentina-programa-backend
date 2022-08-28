package com.mathiezelat.portfolio.Interface;

import java.util.List;

import com.mathiezelat.portfolio.Entity.Person;

public interface IPersonService {
    public List<Person> getAllPersons();

    public Person getOnePerson(Long id);

    public void savePerson(Person person);

    public void deletePerson(Long id);
}
