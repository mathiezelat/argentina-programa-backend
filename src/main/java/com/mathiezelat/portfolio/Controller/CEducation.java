package com.mathiezelat.portfolio.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;

import com.mathiezelat.portfolio.Dto.dtoEducation;
import com.mathiezelat.portfolio.Entity.Education;
import com.mathiezelat.portfolio.Security.Controller.Message;
import com.mathiezelat.portfolio.Service.SEducation;

@Controller
@RequestMapping("/education")
@CrossOrigin(origins = "http://localhost:4200")
public class CEducation {
    @Autowired
    SEducation sEducation;

    @GetMapping
    public ResponseEntity<List<Education>> list() {
        List<Education> list = sEducation.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        Education education = sEducation.getOne(id).get();

        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoEdu) {
        if (StringUtils.isBlank(dtoEdu.getNameE())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEducation.existsByNameE(dtoEdu.getNameE())) {
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Education education = new Education(0, dtoEdu.getNameE(), dtoEdu.getDescriptionE());
        sEducation.save(education);

        return new ResponseEntity(new Message("Educacion creada"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoEdu) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (sEducation.existsByNameE(dtoEdu.getNameE())
                && sEducation.getByNameE(dtoEdu.getNameE()).get().getId() != id) {
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEdu.getNameE())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Education education = sEducation.getOne(id).get();

        education.setNameE(dtoEdu.getNameE());
        education.setDescriptionE(dtoEdu.getDescriptionE());

        sEducation.save(education);

        return new ResponseEntity(new Message("Educacion actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        sEducation.delete(id);
        return new ResponseEntity(new Message("Educacion eliminada"), HttpStatus.OK);
    }

}
