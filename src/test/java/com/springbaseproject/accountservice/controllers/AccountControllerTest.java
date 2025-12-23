package com.springbaseproject.accountservice.controllers;

import com.springbaseproject.accountservice.controllers.publics.AccountController;
import com.springbaseproject.accountservice.mocks.factories.AccountsMockFactory;
import com.springbaseproject.accountservice.security.TestSecurityConfig;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc(addFilters = false)
@Import(TestSecurityConfig.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MockMvcTester mockMvcTester;
    @MockitoBean
    private AccountServiceImpl accountService;

    @Test
    public void getAllAccounts_shouldReturnStatusOk() throws Exception {
        //when(userService.findById(1)).thenReturn(userOptionalMocked);

        mockMvc.perform(get("/api/v1/accounts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountById_shouldReturnStatusOk() {
        var accountDto = AccountsMockFactory.accountResponseDtoOne();

        when(accountService.findById(1L))
                .thenReturn(accountDto);

        assertThat(mockMvcTester.get().uri("/api/v1/accounts/1"))
                .hasStatusOk();
    }
}
