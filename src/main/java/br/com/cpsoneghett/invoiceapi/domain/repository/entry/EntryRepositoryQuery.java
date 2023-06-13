package br.com.cpsoneghett.invoiceapi.domain.repository.entry;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import br.com.cpsoneghett.invoiceapi.domain.repository.filter.EntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntryRepositoryQuery {

    Page<Entry> filter(EntryFilter filter, Pageable pageable);
}
