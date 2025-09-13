package com.digital.digital_expenses_mcp.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ExpensesDto {
    private UUID expenseId;
    private UUID userId;
    private BigDecimal expenseAmount;
    private String category;
    private String description;
    private String userName;
}
