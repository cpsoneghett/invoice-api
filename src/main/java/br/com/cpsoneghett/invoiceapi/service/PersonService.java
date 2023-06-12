package br.com.cpsoneghett.invoiceapi.service;

import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import br.com.cpsoneghett.invoiceapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository repository) {
        this.personRepository = repository;
    }

    public Person update(UUID id, Person person) {

        Person savedPerson = getPersonById(id);

        BeanUtils.copyProperties(person, savedPerson, "id");
        return personRepository.save(savedPerson);
    }

    public void updateActiveStatus(UUID id, Boolean active) {
        Person savedPerson = getPersonById(id);

        savedPerson.setActive(active);
        personRepository.save(savedPerson);
    }

    public Person getPersonById(UUID id) {
        Optional<Person> savedPerson = personRepository.findById(id);

        if (savedPerson.isEmpty()) throw new NoSuchElementException("Resource with id " + id + " was not found;");
        return savedPerson.get();
    }

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public void delete(UUID id) {

        Person person = getPersonById(id);
        this.personRepository.delete(person);
    }
}
