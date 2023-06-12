package br.com.cpsoneghett.invoiceapi.repository.entry;

import br.com.cpsoneghett.invoiceapi.domain.model.Entry;
import br.com.cpsoneghett.invoiceapi.repository.filter.EntryFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryQueryImpl implements EntryRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Entry> filter(EntryFilter filter, Pageable pageable) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
        Root<Entry> root = criteria.from(Entry.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<Entry> query = em.createQuery(criteria);
        addPageRestrictions(query, pageable);


        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private void addPageRestrictions(TypedQuery<Entry> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstRecordCurrentPage = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstRecordCurrentPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(EntryFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Entry> root = criteria.from(Entry.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return em.createQuery(criteria).getSingleResult();
    }

    private Predicate[] createRestrictions(EntryFilter filter, CriteriaBuilder builder, Root<Entry> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filter.description())) {
            predicates.add(builder.like(
                    builder.lower(root.get("description")), "%" + filter.description().toLowerCase() + "%"
            ));
        }
        if (filter.dueDateFrom() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dueDate"), filter.dueDateFrom())
            );
        }
        if (filter.dueDateTo() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dueDate"), filter.dueDateTo())
            );
        }

        return predicates.toArray(new Predicate[0]);
    }
}
