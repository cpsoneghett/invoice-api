package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.domain.dto.CategoryRequest;
import br.com.cpsoneghett.invoiceapi.domain.model.Category;
import br.com.cpsoneghett.invoiceapi.domain.service.CategoryService;
import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ApplicationEventPublisher publisher;

    public CategoryController(CategoryService categoryService, ApplicationEventPublisher publisher) {
        this.categoryService = categoryService;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Category> list() {
        return categoryService.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryRequest category, HttpServletResponse response) {
        Category savedCategory = categoryService.save(category);

        publisher.publishEvent(new ResourceCreatedEvent<>(this, response, savedCategory.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Optional<Category> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
