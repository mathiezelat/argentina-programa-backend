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

import com.mathiezelat.portfolio.Dto.dtoProject;
import com.mathiezelat.portfolio.Entity.Project;
import com.mathiezelat.portfolio.Security.Controller.Message;
import com.mathiezelat.portfolio.Service.SProject;

@Controller
@RequestMapping("/projects")
@CrossOrigin(origins = "https://ar-portfolio-mel.web.app")
public class CProject {
    @Autowired
    SProject sProject;

    @GetMapping
    public ResponseEntity<List<Project>> list() {
        List<Project> list = sProject.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") int id) {
        if (!sProject.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        Project project = sProject.getOne(id).get();

        return new ResponseEntity(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody dtoProject dtoPro) {
        if (StringUtils.isBlank(dtoPro.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sProject.existsByName(dtoPro.getName())) {
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Project project = new Project(0, dtoPro.getName(), dtoPro.getDescription(), dtoPro.getDate(), dtoPro.getUrl(),
                dtoPro.getImg());

        sProject.save(project);

        return new ResponseEntity(new Message("Projecto creado"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProject dtoPro) {
        if (!sProject.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (sProject.existsByName(dtoPro.getName()) &&
                sProject.getByName(dtoPro.getName()).get().getId() != id) {
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoPro.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Project project = sProject.getOne(id).get();

        project.setName(dtoPro.getName());
        project.setDescription(dtoPro.getDescription());
        project.setDate(dtoPro.getDate());
        project.setUrl(dtoPro.getUrl());
        project.setImg(dtoPro.getImg());

        sProject.save(project);

        return new ResponseEntity(new Message("Projecto actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProject.existsById(id)) {
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        sProject.delete(id);
        return new ResponseEntity(new Message("Projecto eliminado"), HttpStatus.OK);
    }
}
