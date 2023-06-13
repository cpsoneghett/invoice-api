package br.com.cpsoneghett.invoiceapi.domain.service;

import br.com.cpsoneghett.invoiceapi.domain.dto.CategoryRequest;
import br.com.cpsoneghett.invoiceapi.domain.model.Category;
import br.com.cpsoneghett.invoiceapi.domain.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(CategoryRequest request) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category, "id");

        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        Optional<Category> savedCategory = categoryRepository.findById(id);

        if (savedCategory.isEmpty()) throw new NoSuchElementException("Category with id " + id + " was not found;");
        return savedCategory;
    }
}
