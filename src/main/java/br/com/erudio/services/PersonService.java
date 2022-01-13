package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

// spring injeta as dependencias - n precisa dar new ou static;
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person findByID(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
    }

    public List<Person> listAll() {
        return personRepository.findAll();
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return personRepository.save(entity);
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
        personRepository.delete(entity);
    }
}
