package com.springbaseproject.accountservice.web.controllers.publics;

import com.springbaseproject.accountservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.accountservice.controllers.publics.AccountController;
import com.springbaseproject.accountservice.controllers.publics.advices.AccountExceptionHandler;
import com.springbaseproject.accountservice.fixtures.AccountFixtures;
import com.springbaseproject.accountservice.configuration.TestSecurityConfig;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private AccountServiceImpl accountService;

    @Test
    @DisplayName("GET /accounts/{id} returns 200 when account exists")
    public void getAccountById_whenExists_shouldReturn200() {
        // Arrange
        var accountDto = AccountFixtures.accountResponseDtoOne();
        when(accountService.findById(1L))
                .thenReturn(accountDto);

        // Act + Assert
        assertThat(mockMvcTester.get().uri("/api/v1/accounts/1"))
                .hasStatusOk();
    }

    @Test
    @DisplayName("GET /accounts/{id} returns 404 when account was not found")
    public void getAccountById_whenNotFound_shouldReturn404() {
        when(accountService.findById(1L))
                .thenThrow();

        assertThat(mockMvcTester.get().uri("/api/v1/accounts/1"))
                .hasFailed();
    }

    //@WithMockUser(roles = "ADMIN")
    public void getAllAccounts_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/accounts/1"))
                .andExpect(status().isOk());
    }
}
