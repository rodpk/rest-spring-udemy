package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

// spring injeta as dependencias - n precisa dar new ou static;
@Service 
public class PersonService {
    
    private final AtomicLong counter = new AtomicLong();

    public Person findByID(String id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Rodrigo");
        person.setLastName("Pinheiro");
        person.setAddress("Campinas, São Paulo - Brazil");
        person.setGender("Male");
        return person;
    }

    public List<Person> listAll() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i< 8; i ++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person create(Person person) {
        return person;
    }

    public Person update(Person person) {
        return person;
    }

    public void delete(String id) {
        
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Some address, São Paulo - Brazil " + i);
        person.setGender("Male or female " + i);
        return person;
    }
}

