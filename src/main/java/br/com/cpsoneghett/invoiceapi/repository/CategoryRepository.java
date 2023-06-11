package br.com.cpsoneghett.invoiceapi.repository;

import br.com.cpsoneghett.invoiceapi.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
