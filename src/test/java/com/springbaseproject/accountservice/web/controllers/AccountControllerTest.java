package com.springbaseproject.accountservice.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbaseproject.accountservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.accountservice.controllers.AccountController;
import com.springbaseproject.accountservice.controllers.advices.AccountExceptionHandler;
import com.springbaseproject.accountservice.fixtures.AccountDtoFixtures;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    @DisplayName("PATCH /api/v1/accounts/{userId} returns 200 when account exists")
    public void updateAccount_whenAccountExists_shouldReturn200() throws Exception {
        var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();
        var updatedAccountDto = AccountDtoFixtures.updatedAccountDtoOne(1L);

        when(accountService.update(1L, updateAccountDto))
                .thenReturn(updatedAccountDto);

        mockMvc.perform(
                patch("/api/v1/accounts/{userId}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAccountDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value(updatedAccountDto.username()));
    }

    @Test
    @DisplayName("PATCH /api/v1/accounts/{userId} returns 500 when account was not found")
    public void updateAccount_whenAccountDoesNotExist_shouldReturn404() throws Exception {
        var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();

        when(accountService.update(1L, updateAccountDto))
                .thenThrow(new UsernameNotFoundException("Account not found"));


        mockMvc.perform(
                        patch("/api/v1/accounts/{userId}",1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateAccountDto))
                )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Account not found"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("DELETE /api/v1/accounts/{userId} returns 200 when account exists")
    public void deleteAccount_whenAccountExists_shouldReturn200() throws Exception {
        Mockito.doNothing()
                .when(accountService).delete(1L);

        mockMvc.perform(
                        delete("/api/v1/accounts/{userId}",1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/v1/accounts/{userId} returns 500 when account was not found")
    public void deleteAccount_whenAccountDoesNotExist_shouldReturn404() throws Exception {
        Mockito.doThrow(new RuntimeException("Account not found"))
                .when(accountService)
                .delete(1L);

        mockMvc.perform(
                        delete("/api/v1/accounts/{userId}",1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Account not found"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("PATCH /api/v1/accounts/change-password returns 200 when account exists")
    public void changePassword_whenAccountExists_shouldReturn200() throws Exception {
        var changePasswordDto = AccountDtoFixtures.changePasswordAccountDto();

        mockMvc.perform(
                patch("/api/v1/accounts/change-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changePasswordDto))
                )
                .andExpect(status().isNoContent());
    }
}
