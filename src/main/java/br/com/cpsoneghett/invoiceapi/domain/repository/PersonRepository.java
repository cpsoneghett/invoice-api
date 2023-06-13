package br.com.cpsoneghett.invoiceapi.domain.repository;

import br.com.cpsoneghett.invoiceapi.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
}
