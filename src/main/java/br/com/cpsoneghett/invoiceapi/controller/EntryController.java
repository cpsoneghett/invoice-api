package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import br.com.cpsoneghett.invoiceapi.repository.EntryRepository;
import br.com.cpsoneghett.invoiceapi.service.EntryService;
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
@RequestMapping("/api/v1/entry")
public class EntryController {

    private final EntryRepository entryRepository;

    private final ApplicationEventPublisher publisher;

    private final EntryService entryService;


    public EntryController(EntryRepository entryRepository, ApplicationEventPublisher publisher, EntryService entryService) {
        this.entryRepository = entryRepository;
        this.publisher = publisher;
        this.entryService = entryService;
    }

    @GetMapping
    public List<Entry> list() {
        return entryRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry savedEntry = entryRepository.save(entry);

        publisher.publishEvent(new ResourceCreatedEvent<>(this, response, savedEntry.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> findById(@PathVariable UUID id) {
        Optional<Entry> entry = entryRepository.findById(id);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<Entry> entry = entryRepository.findById(id);

        if (entry.isPresent()) {
            entryRepository.delete(entry.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else
            return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> update(@PathVariable UUID id, @Valid @RequestBody Entry entry) {

        Entry savedEntry = entryService.update(id, entry);
        return ResponseEntity.ok(savedEntry);
    }

}
