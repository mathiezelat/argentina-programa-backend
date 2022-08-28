package com.mathiezelat.portfolio.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.mathiezelat.portfolio.Dto.dtoExperience;
import com.mathiezelat.portfolio.Entity.Experience;
import com.mathiezelat.portfolio.Security.Controller.Message;
import com.mathiezelat.portfolio.Service.SExperiencie;

@Controller
@RequestMapping("/experience")
@CrossOrigin(origins = "http://localhost:4200")
public class CExperience {
    @Autowired
    SExperiencie sExperiencie;

    @GetMapping
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = sExperiencie.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id) {
        if (!sExperiencie.existsById(id)) {
            return new ResponseEntity(new Message("La experiencia no existe"), HttpStatus.NOT_FOUND);
        }
        Experience experience = sExperiencie.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoExp) {
        if (StringUtils.isBlank(dtoExp.getNameE())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sExperiencie.existsByNameE(dtoExp.getNameE())) {
            return new ResponseEntity(new Message("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        }

        Experience experience = new Experience(0, dtoExp.getNameE(), dtoExp.getDescriptionE());
        sExperiencie.save(experience);

        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoExp) {
        if (!sExperiencie.existsById(id)) {
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sExperiencie.existsByNameE(dtoExp.getNameE()) &&
                sExperiencie.getByNameE(dtoExp.getNameE()).get().getId() != id) {
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoExp.getNameE())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experience experience = sExperiencie.getOne(id).get();
        experience.setNameE(dtoExp.getNameE());
        experience.setDescriptionE(dtoExp.getDescriptionE());

        sExperiencie.save(experience);
        return new ResponseEntity(new Message("Experiencia actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperiencie.existsById(id)) {
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        sExperiencie.delete(id);

        return new ResponseEntity(new Message("Experiencia eliminado"), HttpStatus.OK);
    }
}
