package org.ikigaidigital.infrastructure.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Testcontainers
@TestPropertySource(properties = "spring.docker.compose.enabled=false")
class TimeDepositControllerIntegrationTest {

    @Container
    static final PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("time_deposit_test")
            .withUsername("time_deposit")
            .withPassword("time_deposit");

    @DynamicPropertySource
    static void overrideDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getTimeDepositsShouldReturnRequiredResponseFields() throws Exception {
        mockMvc.perform(get("/time-deposits"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].planType").exists())
            .andExpect(jsonPath("$[0].balance").exists())
            .andExpect(jsonPath("$[0].days").exists())
            .andExpect(jsonPath("$[0].withdrawals").isArray());
    }

    @Test
    void patchBalancesShouldUpdateAndPersistCalculatedBalances() throws Exception {
        mockMvc.perform(patch("/time-deposits/balances"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[?(@.id == 1)].balance").value(1000.83))
            .andExpect(jsonPath("$[?(@.id == 2)].balance").value(2005.0))
            .andExpect(jsonPath("$[?(@.id == 3)].balance").value(3012.5));

        BigDecimal basicBalance = jdbcTemplate.queryForObject(
            "SELECT balance FROM time_deposits WHERE id = 1",
            BigDecimal.class
        );
        BigDecimal studentBalance = jdbcTemplate.queryForObject(
            "SELECT balance FROM time_deposits WHERE id = 2",
            BigDecimal.class
        );
        BigDecimal premiumBalance = jdbcTemplate.queryForObject(
            "SELECT balance FROM time_deposits WHERE id = 3",
            BigDecimal.class
        );

        assertThat(basicBalance).isEqualByComparingTo("1000.83");
        assertThat(studentBalance).isEqualByComparingTo("2005.00");
        assertThat(premiumBalance).isEqualByComparingTo("3012.50");
    }
}
