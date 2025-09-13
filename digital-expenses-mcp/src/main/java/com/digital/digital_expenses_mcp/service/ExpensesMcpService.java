package com.digital.digital_expenses_mcp.service;

import com.digital.digital_expenses_mcp.model.ExpensesDto;
import com.digital.digital_expenses_mcp.model.ExpensesDtoResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.digital.digital_expenses_mcp.utils.Constants.PROMPT_GET_EXPENSES;
import static com.digital.digital_expenses_mcp.utils.PromptConstants.PROMT_CREATE_EXPENSES;


@Service
public class ExpensesMcpService {


    @Autowired
    AuthMcpService authMcpService;

    @Value("${expenses.tokenEndpoint}")
    private String expensesEndpoint;


    private final RestClient restClient;
    @Autowired
    RestTemplate restTemplate;

    public ExpensesMcpService() {

        this.restClient = RestClient.builder()
                .baseUrl(expensesEndpoint)
                .defaultHeader("Accept", "application/geo+json")
                .defaultHeader("User-Agent", "ExpensesApiClient/1.0 (your@email.com)")
                .build();
    }

    @Tool(description = PROMT_CREATE_EXPENSES)
    public ExpensesDto createExpense(ExpensesDto expensesDto) {
        RestTemplate restTemplate1 = new RestTemplate();
        String url = "http://localhost:8084/api/v1/expenses";

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+authMcpService.getToken());

        // Create the request entity
        HttpEntity<ExpensesDto> request = new HttpEntity<>(expensesDto, headers);

        // Send the POST request
        return restTemplate1.postForEntity(url, request, ExpensesDto.class).getBody();
    }

    /**
     * Get expenses for a particular user id
     * @param userId id
     * @return expenses for the user
     * @throws RestClientException if the request fails
     */
    @Tool(description = PROMPT_GET_EXPENSES)
    public String getExpensesByUserId( String userId) {

        String url = expensesEndpoint + userId + "?page=0&size=20";
        //modify the response to ExpensesDtoResponse
        ExpensesDtoResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(ExpensesDtoResponse.class);

        return response.toString();
    }

}
