package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }
    @PostMapping()

    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {

        Category categoryToSave = categoryService.saveCategory(category);

        return new ResponseEntity<>(categoryToSave, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Category> category = categoryService.findCategoryById(id);
        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")

    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Category> updateCategoryById(@NonNull @PathVariable Long id, @Valid @RequestBody Category category) {

        if (!id.equals(category.getCategoryId())) {

            return ResponseEntity.badRequest().build();
        }

        categoryService.updateCategory(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
