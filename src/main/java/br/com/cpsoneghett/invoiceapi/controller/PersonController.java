package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import br.com.cpsoneghett.invoiceapi.repository.PersonRepository;
import br.com.cpsoneghett.invoiceapi.service.PersonService;
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
    
    private final PersonRepository personRepository;

    private final ApplicationEventPublisher publisher;

    private final PersonService personService;

    public PersonController(PersonRepository personRepository, ApplicationEventPublisher publisher, PersonService personService) {
        this.personRepository = personRepository;
        this.publisher = publisher;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> list() {
        return personRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person savedPerson = personRepository.save(person);

        publisher.publishEvent(new ResourceCreatedEvent<>(this, response, savedPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable UUID id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<Person> person = personRepository.findById(id);

        if(person.isPresent()) {
            personRepository.delete(person.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else
            return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable UUID id, @Valid @RequestBody Person person) {

        Person savedPerson = personService.update(id,person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActiveProperty(@PathVariable UUID id, @RequestBody Boolean active) {

        personService.updateActiveStatus(id,active);
    }
    
}
