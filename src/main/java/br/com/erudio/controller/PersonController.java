package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    PagedResourcesAssembler<PersonVO> assembler;

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO findById(@PathVariable("id") Long id) {

        PersonVO personVO = personService.findByID(id);
        // adiciona auto relacionamento
        personVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return personVO;
    }

    // hateoas ta meio vago ainda..
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page, 
                                  @RequestParam(value = "limit", defaultValue = "12") int limit, 
                                  @RequestParam(value = "direction", defaultValue = "asc") String direction) {
                                      // proprio spring injeta a instancia do assembler

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
        Page<PersonVO> persons = personService.listAll(pageable);

        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));


        PagedResources<?> resources = assembler.toResource(persons);
        return new ResponseEntity<>(resources, HttpStatus.OK);

    }

    @GetMapping(value = "/findPersonByName/{firstName}",produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> listAllByName(@RequestParam(value = "page", defaultValue = "0") int page, 
                                  @RequestParam(value = "limit", defaultValue = "12") int limit, 
                                  @RequestParam(value = "direction", defaultValue = "asc") String direction, 
                                  
                                  @PathVariable("firstName") String firstname) {
                                      // proprio spring injeta a instancia do assembler

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
        Page<PersonVO> persons = personService.findPersonByName(pageable, firstname);

        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

        PagedResources<?> resources = assembler.toResource(persons);
        
        return new ResponseEntity<>(resources, HttpStatus.OK);
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


    @PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO disablePerson(@PathVariable("id") Long id) {

        PersonVO personVO = personService.disablePerson(id);
        personVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return personVO;
    }
}
