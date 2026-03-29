package com.tejamahindra.expensewise.repository;

import com.tejamahindra.expensewise.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserEmail(String email);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user.email = :email AND e.type = :type")
    Double sumByUserEmailAndType(String email, String type);
}