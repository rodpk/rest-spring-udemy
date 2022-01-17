package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.services.PersonService;

@RestController

// enable cross origins, can define which can access
// can be defined in an endpoint or globally in config.
// @CrossOrigin(origins = {"http://localhost:8080", "http://www.erudio.com.br"})
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO findById(@PathVariable("id") Long id) {

        PersonVO personVO = personService.findByID(id);
        // adiciona auto relacionamento
        personVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return personVO;
    }

    // hateoas ta meio vago ainda..
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<PersonVO> listAll() {
        List<PersonVO> persons = personService.listAll();
        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return persons;

    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
            "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = personService.create(person);
        personVO.add(linkTo(methodOn(BookController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = personService.update(person);
        personVO.add(linkTo(methodOn(BookController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

}
