package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import br.com.cpsoneghett.invoiceapi.exception.InactiveStatusException;
import br.com.cpsoneghett.invoiceapi.exception.InvoiceError;
import br.com.cpsoneghett.invoiceapi.repository.filter.EntryFilter;
import br.com.cpsoneghett.invoiceapi.service.EntryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/entry")
public class EntryController {

    private final EntryService entryService;

    private final ApplicationEventPublisher publisher;
    private final MessageSource messageSource;



    public EntryController(ApplicationEventPublisher publisher, EntryService entryService, MessageSource messageSource) {
        this.publisher = publisher;
        this.entryService = entryService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public Page<Entry> search(EntryFilter filter, Pageable pageable) {
        return entryService.filter(filter, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Entry> save(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry savedEntry = entryService.save(entry);

        publisher.publishEvent(new ResourceCreatedEvent<>(this, response, savedEntry.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> findById(@PathVariable UUID id) {
        Optional<Entry> entry = entryService.findById(id);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<Entry> entry = entryService.findById(id);

        if (entry.isPresent()) {
            entryService.delete(entry.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else
            return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> update(@PathVariable UUID id, @Valid @RequestBody Entry entry) {

        Entry savedEntry = entryService.update(id, entry);
        return ResponseEntity.ok(savedEntry);
    }


    @ExceptionHandler({InactiveStatusException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(InactiveStatusException ex) {

        String userMessage = messageSource.getMessage("person.inactive", null, LocaleContextHolder.getLocale());
        String devMessage = ex.toString();

        List<InvoiceError> errors = List.of(new InvoiceError(userMessage, devMessage));

        return ResponseEntity.badRequest().body(errors);
    }
}
