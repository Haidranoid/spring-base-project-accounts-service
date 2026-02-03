package com.springbaseproject.accountservice.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbaseproject.accountservice.configuration.TestSecurityConfig;
import com.springbaseproject.accountservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.accountservice.controllers.InternalAccountController;
import com.springbaseproject.accountservice.controllers.advices.AccountExceptionHandler;
import com.springbaseproject.accountservice.fixtures.AccountDtoFixtures;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InternalAccountController.class)
@Import({
        TestSecurityConfig.class,
        AccountExceptionHandler.class,
        GlobalExceptionHandler.class
})
public class InternalAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountServiceImpl accountService;


    @Test
    @DisplayName("POST /api/v1/accounts returns 200 when account is created")
    public void createAccount_whenAccountIsCreated_shouldReturn200() throws Exception {
        var createAccountDto = AccountDtoFixtures.createAdminAccountDto();
        var accountCreated = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountService.create(createAccountDto))
                .thenReturn(accountCreated);

        mockMvc.perform(
                        post("/api/v1/internal/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createAccountDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value(createAccountDto.username()))
                .andExpect(jsonPath("$.email").value(createAccountDto.email()));
    }

    @Test
    @DisplayName("POST /api/v1/accounts/authenticate-login returns 200 when credentials are valid")
    public void authenticateLogin_whenCredentialsAreValid_shouldReturn200() throws Exception {
        var authenticateAccountDto = AccountDtoFixtures.authenticateAccountDto();
        var accountAuthenticated = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountService.authenticateAccount(authenticateAccountDto))
                .thenReturn(accountAuthenticated);

        mockMvc.perform(
                        post("/api/v1/internal/accounts/authenticate-login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authenticateAccountDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value(authenticateAccountDto.username()));
    }
}
