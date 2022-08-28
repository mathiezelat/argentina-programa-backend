package com.mathiezelat.portfolio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathiezelat.portfolio.Entity.Person;
import com.mathiezelat.portfolio.Interface.IPersonService;
import com.mathiezelat.portfolio.Repository.IPersonRepository;

@Service
public class ImpPersonService implements IPersonService {

    @Autowired
    IPersonRepository iPersonRepo;

    @Override
    public List<Person> getAllPersons() {
        List<Person> listPersons = iPersonRepo.findAll();
        return listPersons;
    }

    @Override
    public Person getOnePerson(Long id) {
        Person person = iPersonRepo.findById(id).orElse(null);
        return person;
    }

    @Override
    public void savePerson(Person person) {
        iPersonRepo.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        iPersonRepo.deleteById(id);
    }

}
