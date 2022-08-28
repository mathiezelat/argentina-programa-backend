package com.mathiezelat.portfolio.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mathiezelat.portfolio.Entity.Person;
import com.mathiezelat.portfolio.Interface.IPersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    IPersonService iPersonService;

    @GetMapping
    public List<Person> getAllPersons() {
        return iPersonService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getOnePerson(@PathVariable Long id) {
        return iPersonService.getOnePerson(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String savePerson(@RequestBody Person person) {
        iPersonService.savePerson(person);
        return "La persona fue creada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public String updatePerson(@PathVariable Long id,
            @RequestParam("name") String newName,
            @RequestParam("lastName") String newLastName,
            @RequestParam("dateOfBirth") String newDateOfBirth,
            @RequestParam("nationality") String newNationality,
            @RequestParam("email") String newEmail,
            @RequestParam("aboutMe") String newAboutMe,
            @RequestParam("occupation") String newOccupation,
            @RequestParam("coverImage") String newCoverImage,
            @RequestParam("profilePicture") String newProfilePicture) {
        Person person = iPersonService.getOnePerson(id);

        person.setName(newName);
        person.setLastName(newLastName);
        person.setDateOfBirth(newDateOfBirth);
        person.setNationality(newNationality);
        person.setEmail(newEmail);
        person.setAboutMe(newAboutMe);
        person.setOccupation(newOccupation);
        person.setCoverImage(newCoverImage);
        person.setProfilePicture(newProfilePicture);

        iPersonService.savePerson(person);

        return "La persona fue actualizada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        iPersonService.deletePerson(id);
        return "La persona fue borrada correctamente";
    }
}
