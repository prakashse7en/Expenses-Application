// File: src/test/java/com/digital/transactions/expenses/controller/ExpensesControllerTest.java
package com.digital.transactions.expenses;

import com.digital.transactions.expenses.controller.ExpensesController;
import com.digital.transactions.expenses.exception.UserNotFoundException;
import com.digital.transactions.expenses.pojo.model.ExpensesDto;
import com.digital.transactions.expenses.pojo.model.ExpensesDtoResponse;
import com.digital.transactions.expenses.service.ExpensesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpensesController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
public class ExpensesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpensesService expensesService;

    @Autowired
    private ObjectMapper objectMapper;

    // Java
    // Add this to your ExpensesControllerTest.java

    @Test
    @DisplayName("POST /api/v1/expenses - create expense with valid token and payload")
    void createExpenseWithValidTokenAndPayload() throws Exception {
        String token = "fyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ5el9nSEQ1NC1DcHB1MV94akczLVFKX2VYME5GN3drcV9sVmFLUWVJTUYwIn0.eyJleHAiOjE3NTU0ODMyMzEsImlhdCI6MTc1NTQ4MjkzMSwianRpIjoib25ydHJvOjhkZDA5YWNlLWNmY2YtYTAzZS0wZjBiLTEzMTQ2YjIxNzc3MiIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9yZWFsbXMvZXhwZW5zZXMiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTgwZjQ5ZDktM2ZiOC00MzFkLTk4MzQtNzU2ODVlMTg0NzA0IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZXhwZW5zZXNjbGllbnRhZG1pbiIsInNpZCI6IjQzMDA4Y2QwLWNhNGMtNDEyYi1hM2MwLTg2NDQ5OTY1ODVhMSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJleHBlbnNlc2FkbWlucmVhbG1yb2xlIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWV4cGVuc2VzIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiZXhwZW5zZXNjbGllbnRhZG1pbiI6eyJyb2xlcyI6WyJjbGllbnR1c2VyIiwiY2xpZW50YWRtaW4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Imh5ZHJveHkgbGVzbmFyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiZXhwZW5zZXN1c2VyIiwiZ2l2ZW5fbmFtZSI6Imh5ZHJveHkiLCJmYW1pbHlfbmFtZSI6Imxlc25hciIsImVtYWlsIjoiaHlkcm94eS5sZXNuYXJAZ21haWwuY29tIn0.CgbCzpaAEcN7mjD95U_45d2N7jXv9OT6hcGj9ymxW8VHZYQ0Nn6IFRcc26j8BiGU9tKZIs9UerwP77k4JDmf7uAuUOvhIfeaWBtxew-uQ56JHc9g2_br5rW71wFKahssqDXr2zBrU8EM3enbkiI0fCxUrPNx4yz5KDVTPTBHEMwN-Zu_llr1adHWiAAqKGuv6YrH3xIUhRikMa58TGnX1vlgvhLfJHiSIIY6Hzqdtw1QnB4Y3fwb-zXhKmPuli6Zdt7mrsUPWLY5oQR5xggjgaCL_Ov3y3Lf-o7hvfXloPFayBsVZjKmrUTq7Sy55CPul1_9heiQvb8tZAc-drutTw";
        String payload = "{\n" +
                "    \"userId\": \"c154a551-c31d-405c-a289-efbe89383019\",\n" +
                "    \"expenseAmount\": 100.73,\n" +
                "    \"category\": \"Food\",\n" +
                "    \"description\": \"Random expense 398\"\n" +
                "}";

        ExpensesDto responseDto = new ExpensesDto();
        responseDto.setUserId(UUID.fromString("c154a551-c31d-405c-a289-efbe89383019"));
        responseDto.setExpenseAmount(new java.math.BigDecimal("100.73"));
        responseDto.setCategory("Food");
        responseDto.setDescription("Random expense 398");

        Mockito.when(expensesService.createExpenses(any(ExpensesDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("c154a551-c31d-405c-a289-efbe89383019"))
                .andExpect(jsonPath("$.category").value("Food"))
                .andExpect(jsonPath("$.expenseAmount").value(100.73))
                .andExpect(jsonPath("$.description").value("Random expense 398"));
    }

    @Test
    @DisplayName("GET /api/v1/expenses/{userId} - success")
    void getExpensesPaginatedSuccess() throws Exception {
        UUID userId = UUID.randomUUID();
        ExpensesDtoResponse responseDto = new ExpensesDtoResponse();
        responseDto.setHasPrevious(false);
        responseDto.setHasNext(false);
        //fill the content with dummy data
        responseDto.setContent(Collections.singletonList(new ExpensesDto()));

        Mockito.when(expensesService.findExpensesByUserId(any(), any(UUID.class)))
                .thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/expenses/{userId}", userId)
                        .param("page", "0")
                        .param("size", "5")
                        .param("sortBy", "category")
                        .param("ascending", "true"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/v1/expenses - user not found")
    void createExpenseUserNotFound() throws Exception {
        ExpensesDto requestDto = new ExpensesDto();
        requestDto.setUserId(UUID.fromString(UUID.randomUUID().toString()));
        requestDto.setExpenseAmount(BigDecimal.valueOf(100.73));
        requestDto.setCategory("Travel");
        requestDto.setDescription("Random expense 398");

        Mockito.when(expensesService.createExpenses(any(ExpensesDto.class)))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(post("/api/v1/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }
}