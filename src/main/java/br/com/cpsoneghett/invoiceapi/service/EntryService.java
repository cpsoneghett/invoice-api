package br.com.cpsoneghett.invoiceapi.service;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import br.com.cpsoneghett.invoiceapi.exception.InactiveStatusException;
import br.com.cpsoneghett.invoiceapi.repository.EntryRepository;
import br.com.cpsoneghett.invoiceapi.repository.filter.EntryFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    private final PersonService personService;

    public EntryService(EntryRepository repository, PersonService personService) {
        this.entryRepository = repository;
        this.personService = personService;
    }

    public Entry update(UUID id, Entry entry) {

        Entry savedEntry = getEntryById(id);

        BeanUtils.copyProperties(entry, savedEntry, "id");
        return entryRepository.save(savedEntry);
    }

    private Entry getEntryById(UUID id) {
        Optional<Entry> savedEntry = entryRepository.findById(id);

        if (savedEntry.isEmpty()) throw new NoSuchElementException("Resource with id " + id + " was not found;");

        return savedEntry.get();
    }

    public Entry save(Entry entry) {

        Person person = personService.getPersonById(entry.getPerson().getId());

        if (!person.isActive()) throw new InactiveStatusException();

        return entryRepository.save(entry);
    }

    public Page<Entry> filter(EntryFilter filter, Pageable pageable) {
        return this.entryRepository.filter(filter, pageable);
    }

    public Optional<Entry> findById(UUID id) {
        return this.entryRepository.findById(id);
    }

    public void delete(Entry entry) {
        this.entryRepository.delete(entry);
    }
}
