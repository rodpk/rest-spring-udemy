package br.com.erudio.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    

    @Query(value = "SELECT * FROM person", nativeQuery = true)
    public List<Person> listAll();




    @Modifying
    @Query("UPDATE Person p SET p.enabled = false where p.id = :id")
    void disablePerson(@Param("id") Long id);

    // like consome bastante performance
    // @Query("select p from Person p where p.firstName LIKE LOWER(CONCAT('%', :firstname, '%'))")
    @Query(value = "SELECT p FROM Person p WHERE p.firstName LIKE LOWER(CONCAT('%',:firstname,'%'))", nativeQuery = false)
    Page<Person> findPersonByName(@Param("firstname") String firstname, Pageable pageable);
}
