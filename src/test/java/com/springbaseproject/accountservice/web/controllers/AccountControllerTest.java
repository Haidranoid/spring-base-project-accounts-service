package com.springbaseproject.accountservice.web.controllers;

import com.springbaseproject.accountservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.accountservice.controllers.publics.AccountController;
import com.springbaseproject.accountservice.controllers.publics.advices.AccountExceptionHandler;
import com.springbaseproject.accountservice.fixtures.AccountDtoFixtures;
import com.springbaseproject.accountservice.fixtures.AccountEntityFixtures;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({
        AccountExceptionHandler.class,
        GlobalExceptionHandler.class
})
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    //@Autowired
    //private MockMvcTester mvcTester;

    @MockitoBean
    private AccountServiceImpl accountService;

    //@WithMockUser(roles = "ADMIN")
    @Test
    @DisplayName("GET /api/v1/accounts returns 200 when accounts list is not empty")
    public void getAllAccounts_whenAccountsIsNotEmpty_shouldReturn200() throws Exception {
        var accountsList = AccountDtoFixtures.twoAccountResponseDto();

        when(accountService.findAll())
                .thenReturn(accountsList);

        mockMvc.perform(get("/api/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("GET /api/v1/accounts returns 200 when accounts list is empty")
    public void getAllAccounts_whenNoAccounts_shouldReturn200() throws Exception {
        var accountsEmptyList = AccountDtoFixtures.emptyAccountResponseDtoList();

        when(accountService.findAll())
                .thenReturn(accountsEmptyList);

        mockMvc.perform(get("/api/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /api/v1/accounts/{id} returns 200 when account exists")
    public void getAccountById_whenAccountExists_shouldReturn200() throws Exception {
        // Arrange
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountService.findById(1L))
                .thenReturn(accountDto);

        // Act + Assert
        mockMvc.perform(get("/api/v1/accounts/1"))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/accounts/{id} returns 500 when account was not found")
    public void getAccountById_whenAccountDoesNotExist_shouldReturn404() throws Exception {
        when(accountService.findById(1L))
                .thenThrow(new UsernameNotFoundException("Account not found"));

        mockMvc.perform(get("/api/v1/accounts/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Account not found"))
                .andExpect(jsonPath("$.timestamp").exists());

        /*
         var result = mockMvcTester.get()
                        .uri("/api/v1/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .exchange();

        assertThat(result).hasStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("Account not found");
        assertThat(result).bodyJson().extractingPath("$.timestamp").isNotEmpty();
         */
    }
}
