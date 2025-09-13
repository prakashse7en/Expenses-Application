package com.digital.digital_expenses_mcp;

import com.digital.digital_expenses_mcp.service.ExpensesMcpService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalExpensesMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalExpensesMcpApplication.class, args);
	}


	@Bean
	public ToolCallbackProvider expensesTools(ExpensesMcpService expensesMcpService) {
		return MethodToolCallbackProvider.builder().toolObjects(expensesMcpService).build();
	}

}
