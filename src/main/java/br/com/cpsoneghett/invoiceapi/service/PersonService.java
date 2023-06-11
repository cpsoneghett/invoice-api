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

        Optional<Person> savedPerson = personRepository.findById(id);

        if (savedPerson.isEmpty())
            throw new NoSuchElementException("Element with id " + id + " was not found;");

        BeanUtils.copyProperties(person, savedPerson.get(), "id");
        return personRepository.save(savedPerson.get());
    }

}
