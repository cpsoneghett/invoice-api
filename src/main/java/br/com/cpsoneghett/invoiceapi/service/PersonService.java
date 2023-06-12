package br.com.cpsoneghett.invoiceapi.service;

import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import br.com.cpsoneghett.invoiceapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

        Optional<Person> savedPerson = getPersonById(id);

        BeanUtils.copyProperties(person, savedPerson.get(), "id");
        return personRepository.save(savedPerson.get());
    }

    public void updateActiveStatus(UUID id, Boolean active) {
        Optional<Person> savedPerson = getPersonById(id);

        savedPerson.get().setActive(active);
        personRepository.save(savedPerson.get());
    }

    private Optional<Person> getPersonById(UUID id) {
        Optional<Person> savedPerson = personRepository.findById(id);

        if (savedPerson.isEmpty()) throw new NoSuchElementException("Resource with id " + id + " was not found;");
        return savedPerson;
    }
}
