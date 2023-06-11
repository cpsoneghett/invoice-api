package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import br.com.cpsoneghett.invoiceapi.repository.PersonRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    
    private PersonRepository personRepository;

    private ApplicationEventPublisher publisher;

    public PersonController(PersonRepository personRepository, ApplicationEventPublisher publisher) {
        this.personRepository = personRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Person> list() {
        return personRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person savedPerson = personRepository.save(person);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable UUID id) {
        Optional<Person> person = personRepository.findById(id);
        return !person.isEmpty() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
    }
    
}
