package com.springbaseproject.accountservice.web.controllers;

import com.springbaseproject.accountservice.configuration.TestSecurityConfig;
import com.springbaseproject.accountservice.controllers.advices.GlobalExceptionHandler;
import com.springbaseproject.accountservice.controllers.internals.InternalAccountController;
import com.springbaseproject.accountservice.controllers.publics.advices.AccountExceptionHandler;
import com.springbaseproject.accountservice.fixtures.AccountDtoFixtures;
import com.springbaseproject.accountservice.fixtures.AccountEntityFixtures;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(InternalAccountController.class)
@Import({
        TestSecurityConfig.class,
        AccountExceptionHandler.class,
        GlobalExceptionHandler.class
})
public class InternalAccountControllerTest {

    @Autowired
    private MockMvcTester mvc;
    @MockitoBean
    private AccountServiceImpl accountService;

    //@Test
    public void getAccountById_whenExists_shouldReturn200() {
        // Arrange
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);
        when(accountService.findById(1L))
                .thenReturn(accountDto);

        // Act + Assert
        assertThat(mvc.get().uri("/api/v1/accounts/1"))
                .hasStatusOk();
    }
}
