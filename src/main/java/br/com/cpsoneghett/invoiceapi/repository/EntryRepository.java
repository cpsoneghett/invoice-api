package br.com.cpsoneghett.invoiceapi.repository;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntryRepository extends JpaRepository<Entry, UUID> {
}
