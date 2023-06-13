package br.com.cpsoneghett.invoiceapi.domain.repository.filter;

import java.time.LocalDate;

public record EntryFilter(String description, LocalDate dueDateFrom, LocalDate dueDateTo) {
}
