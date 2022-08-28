package com.mathiezelat.portfolio.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathiezelat.portfolio.Dto.dtoPerson;
import com.mathiezelat.portfolio.Entity.Person;
import com.mathiezelat.portfolio.Interface.IPersonService;
import com.mathiezelat.portfolio.Security.Controller.Message;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    IPersonService iPersonService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = iPersonService.getAllPersons();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getOnePerson(@PathVariable int id) {
        Person person = iPersonService.getOnePerson(id);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody dtoPerson dtoPer) {
        Person person = new Person(0, dtoPer.getName(), dtoPer.getLastName(), dtoPer.getDateOfBirth(),
                dtoPer.getNationality(), dtoPer.getEmail(), dtoPer.getAboutMe(), dtoPer.getLocation(),
                dtoPer.getOccupation(), dtoPer.getCoverImage(), dtoPer.getProfilePicture());
        iPersonService.savePerson(person);

        return new ResponseEntity(new Message("La persona fue creada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody dtoPerson dtoPer) {
        Person person = iPersonService.getOnePerson(id);

        person.setName(dtoPer.getName());
        person.setLastName(dtoPer.getLastName());
        person.setDateOfBirth(dtoPer.getDateOfBirth());
        person.setNationality(dtoPer.getNationality());
        person.setEmail(dtoPer.getEmail());
        person.setAboutMe(dtoPer.getAboutMe());
        person.setLocation(dtoPer.getLocation());
        person.setOccupation(dtoPer.getOccupation());
        person.setCoverImage(dtoPer.getCoverImage());
        person.setProfilePicture(dtoPer.getProfilePicture());

        iPersonService.savePerson(person);

        return new ResponseEntity(new Message("Persona actualizada correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id) {
        iPersonService.deletePerson(id);
        return new ResponseEntity(new Message("La persona fue borrada correctamente"), HttpStatus.OK);
    }
}
