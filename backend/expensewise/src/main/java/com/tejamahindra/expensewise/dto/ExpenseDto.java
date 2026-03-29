package com.tejamahindra.expensewise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDto {

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String type; // INCOME or EXPENSE

    private String note;

    @NotNull
    private LocalDate expenseDate;

    @NotNull
    private Long categoryId;
}