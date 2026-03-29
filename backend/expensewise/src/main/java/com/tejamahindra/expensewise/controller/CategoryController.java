package com.tejamahindra.expensewise.controller;

import com.tejamahindra.expensewise.dto.CategoryDto;
import com.tejamahindra.expensewise.model.Category;
import com.tejamahindra.expensewise.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryDto dto,
                                           Authentication auth) {
        return ResponseEntity.ok(
                categoryService.createCategory(dto, auth.getName())
        );
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(Authentication auth) {
        return ResponseEntity.ok(
                categoryService.getUserCategories(auth.getName())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted");
    }
}