package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Person findById(@PathVariable("id") String id) {
        return personService.findByID(id);
    }


    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Person>listAll() {
        return personService.listAll();
    }

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }


    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        personService.delete(id);
    }

}
