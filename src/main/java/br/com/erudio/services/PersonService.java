package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

// spring injeta as dependencias - n precisa dar new ou static;
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonVO findByID(Long id) {
        var entity =  personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));

        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public Page<PersonVO> listAll(Pageable pageable) {
        var page =  personRepository.findAll(pageable);
        return page.map(this::convertToPersonVO);
    }
    
    public Page<PersonVO> findPersonByName(Pageable pageable, String firstname) {
        var page =  personRepository.findPersonByName(firstname, pageable);
        return page.map(this::convertToPersonVO);
    }

    private PersonVO convertToPersonVO(Person entity) {
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {
        var entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo =  DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;

    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
        personRepository.delete(entity);
    }

    @Transactional // controle de transação  
    public PersonVO disablePerson(Long id) {
        personRepository.disablePerson(id);
        return this.findByID(id);
    }
}
