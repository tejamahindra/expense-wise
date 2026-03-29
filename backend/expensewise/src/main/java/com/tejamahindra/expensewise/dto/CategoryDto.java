package com.tejamahindra.expensewise.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    @NotBlank
    private String name;

    @NotBlank
    private String type; // INCOME or EXPENSE
}