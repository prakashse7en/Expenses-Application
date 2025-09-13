package com.digital.digital_expenses_mcp.model;

import lombok.Data;
import java.util.List;

@Data
public class ExpensesDtoResponse {
    private List<ExpensesDto> content;
    private boolean first;
    private boolean last;
    private boolean empty;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private boolean hasNext;
    private boolean hasPrevious;

}


