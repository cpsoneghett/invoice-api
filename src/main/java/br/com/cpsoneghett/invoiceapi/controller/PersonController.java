package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import br.com.cpsoneghett.invoiceapi.domain.service.PersonService;
import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {


    private final ApplicationEventPublisher publisher;

    private final PersonService personService;

    public PersonController(ApplicationEventPublisher publisher, PersonService personService) {
        this.publisher = publisher;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> list() {
        return personService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response) {

        Person savedPerson = personService.save(person);

        publisher.publishEvent(new ResourceCreatedEvent<>(this, response, savedPerson.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable UUID id) {

        Person person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable UUID id, @Valid @RequestBody Person person) {

        Person savedPerson = personService.update(id, person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActiveProperty(@PathVariable UUID id, @RequestBody Boolean active) {

        personService.updateActiveStatus(id, active);
    }

}
