package com.tejamahindra.expensewise.controller;

import com.tejamahindra.expensewise.dto.ExpenseDto;
import com.tejamahindra.expensewise.model.Expense;
import com.tejamahindra.expensewise.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> create(@Valid @RequestBody ExpenseDto dto,
                                          Authentication auth) {
        return ResponseEntity.ok(
                expenseService.createExpense(dto, auth.getName())
        );
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll(Authentication auth) {
        return ResponseEntity.ok(
                expenseService.getUserExpenses(auth.getName())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable Long id,
                                          @Valid @RequestBody ExpenseDto dto) {
        return ResponseEntity.ok(
                expenseService.updateExpense(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted");
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Double>> summary(Authentication auth) {
        return ResponseEntity.ok(
                expenseService.getSummary(auth.getName())
        );
    }
}