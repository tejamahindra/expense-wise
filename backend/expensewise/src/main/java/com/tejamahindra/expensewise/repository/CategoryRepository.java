package com.tejamahindra.expensewise.repository;

import com.tejamahindra.expensewise.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserEmail(String email);
}