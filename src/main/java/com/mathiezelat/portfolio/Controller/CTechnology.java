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

import com.mathiezelat.portfolio.Dto.dtoTechnology;
import com.mathiezelat.portfolio.Entity.Technology;
import com.mathiezelat.portfolio.Security.Controller.Message;
import com.mathiezelat.portfolio.Service.STechnology;

@Controller
@RequestMapping("/technologies")
@CrossOrigin(origins = "http://localhost:4200")
public class CTechnology {
    @Autowired
    STechnology sTechnology;

    @GetMapping
    public ResponseEntity<List<Technology>> list() {
        List<Technology> list = sTechnology.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getById(@PathVariable("id") int id) {
        if (!sTechnology.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        Technology technology = sTechnology.getOne(id).get();

        return new ResponseEntity(technology, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody dtoTechnology dtoTech) {
        if (StringUtils.isBlank(dtoTech.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sTechnology.existsByName(dtoTech.getName())) {
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Technology technology = new Technology(0, dtoTech.getName(), dtoTech.getPercent());
        sTechnology.save(technology);

        return new ResponseEntity(new Message("Tecnologia creada"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoTechnology dtoTech) {
        if (!sTechnology.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (sTechnology.existsByName(dtoTech.getName())) {
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoTech.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Technology technology = sTechnology.getOne(id).get();

        technology.setName(dtoTech.getName());
        technology.setPercent(dtoTech.getPercent());

        sTechnology.save(technology);

        return new ResponseEntity(new Message("Tecnologia actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sTechnology.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        sTechnology.delete(id);
        return new ResponseEntity(new Message("Projecto eliminado"), HttpStatus.OK);
    }
}
