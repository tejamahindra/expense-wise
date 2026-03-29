package com.tejamahindra.expensewise.service;

import com.tejamahindra.expensewise.dto.CategoryDto;
import com.tejamahindra.expensewise.model.Category;
import com.tejamahindra.expensewise.model.User;
import com.tejamahindra.expensewise.repository.CategoryRepository;
import com.tejamahindra.expensewise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category createCategory(CategoryDto dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setUser(user);

        return categoryRepository.save(category);
    }

    public List<Category> getUserCategories(String email) {
        return categoryRepository.findByUserEmail(email);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}