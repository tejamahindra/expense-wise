package com.tejamahindra.expensewise.service;

import com.tejamahindra.expensewise.dto.ExpenseDto;
import com.tejamahindra.expensewise.model.Category;
import com.tejamahindra.expensewise.model.Expense;
import com.tejamahindra.expensewise.model.User;
import com.tejamahindra.expensewise.repository.CategoryRepository;
import com.tejamahindra.expensewise.repository.ExpenseRepository;
import com.tejamahindra.expensewise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Expense createExpense(ExpenseDto dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setType(dto.getType());
        expense.setNote(dto.getNote());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setCategory(category);
        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(String email) {
        return expenseRepository.findByUserEmail(email);
    }

    public Expense updateExpense(Long id, ExpenseDto dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setType(dto.getType());
        expense.setNote(dto.getNote());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Map<String, Double> getSummary(String email) {
        Double totalIncome = expenseRepository
                .sumByUserEmailAndType(email, "INCOME");
        Double totalExpense = expenseRepository
                .sumByUserEmailAndType(email, "EXPENSE");
        Double balance = totalIncome - totalExpense;

        Map<String, Double> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("balance", balance);
        return summary;
    }
}