// File: src/test/java/com/digital/transactions/expenses/controller/ExpensesControllerUnitTest.java
package com.digital.transactions.expenses;

import com.digital.transactions.expenses.controller.ExpensesController;
import com.digital.transactions.expenses.exception.UserNotFoundException;
import com.digital.transactions.expenses.pojo.model.ExpensesDto;
import com.digital.transactions.expenses.service.ExpensesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpensesControllerUnitTest {

    @Mock
    private ExpensesService expensesService;

    @InjectMocks
    private ExpensesController expensesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExpense_success() throws Exception {
        ExpensesDto input = new ExpensesDto();
        input.setUserId(UUID.fromString("c154a551-c31d-405c-a289-efbe89383019"));
        input.setExpenseAmount(BigDecimal.valueOf(100.73));
        input.setCategory("Food");
        input.setDescription("Random expense 398");

        ExpensesDto expected = new ExpensesDto();
        expected.setUserId(input.getUserId());
        expected.setExpenseAmount(input.getExpenseAmount());
        expected.setCategory(input.getCategory());
        expected.setDescription(input.getDescription());

        when(expensesService.createExpenses(input)).thenReturn(expected);

        ExpensesDto result = expensesController.createExpense(input);

        assertNotNull(result);
        assertEquals(expected.getUserId(), result.getUserId());
        assertEquals(expected.getCategory(), result.getCategory());
        assertEquals(expected.getExpenseAmount(), result.getExpenseAmount());
        assertEquals(expected.getDescription(), result.getDescription());
        verify(expensesService, times(1)).createExpenses(input);
    }

    @Test
    void createExpense_userNotFound() throws Exception {
        ExpensesDto input = new ExpensesDto();
        input.setUserId(UUID.randomUUID());
        input.setExpenseAmount(BigDecimal.valueOf(100.73));
        input.setCategory("Travel");
        input.setDescription("Random expense 398");

        when(expensesService.createExpenses(input)).thenThrow(new UserNotFoundException("User not found"));

        assertThrows(UserNotFoundException.class, () -> expensesController.createExpense(input));
        verify(expensesService, times(1)).createExpenses(input);
    }
}