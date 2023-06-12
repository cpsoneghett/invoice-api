package br.com.cpsoneghett.invoiceapi.service;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import br.com.cpsoneghett.invoiceapi.repository.EntryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository repository) {
        this.entryRepository = repository;
    }

    public Entry update(UUID id, Entry entry) {

        Optional<Entry> savedEntry = getEntryById(id);

        BeanUtils.copyProperties(entry, savedEntry.get(), "id");
        return entryRepository.save(savedEntry.get());
    }

    private Optional<Entry> getEntryById(UUID id) {
        Optional<Entry> savedEntry = entryRepository.findById(id);

        if (savedEntry.isEmpty()) throw new NoSuchElementException("Resource with id " + id + " was not found;");

        return savedEntry;
    }
}
