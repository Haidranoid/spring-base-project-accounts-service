package com.springbaseproject.accountservice.integration.flows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional - weir behahivor when MockMvc is used
//public class AccountFlowIT extends AbstractPostgresIT {
public class AccountFlowIT {

    @Autowired
    MockMvc mvc;

    //@Test
    void createAccountFlow_shouldPersistAndReturnAccount() throws Exception {
        mvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "email": "test@test.com",
                                      "firstName": "Steve"
                                    }
                                """))
                .andExpect(status().isCreated());

        mvc.perform(get("/api/v1/accounts"))
                .andExpect(status().isOk());
    }
}
