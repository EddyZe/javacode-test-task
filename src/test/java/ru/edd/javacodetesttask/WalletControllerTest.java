package ru.edd.javacodetesttask;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.edd.javacodetesttask.dto.WalletDTO;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

    UUID walletId = UUID.fromString("e51afd83-a4b5-414a-82d5-e99f1e5ac121");


    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/sql/wallet-test.sql")
    void getWalletBalance_ReturnBalanceAndUUIDWallet() throws Exception {

        var requestBuilder = MockMvcRequestBuilders.get("/api/v1/wallets/%s".formatted(walletId), WalletDTO.class);


        String jsonResult = """
                {
                    "walletId": "%s",
                    "balance": 1000.00
                }
                """.formatted(walletId);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(jsonResult))
                .andExpect(status().isOk());

    }

    @Test
    @Sql("/sql/wallet-test.sql")
    void executeTransactionWithdraw_ReturnStatusOk() throws Exception {

        String jsonRequest = """
                {
                    "walletId": "%s",
                    "operationType": "WITHDRAW",
                    "amount": 500
                }
                """.formatted(walletId);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        String jsonResponse = """
                {
                    "status": "OK",
                    "newBalance": 500.00
                }
                """;


        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(jsonResponse))
                .andExpect(status().isOk());

    }

    @Test
    @Sql("/sql/wallet-test.sql")
    void executeTransactionWithdraw_ReturnStatusBadRequest() throws Exception {

        String jsonRequest = """
                {
                    "walletId": "%s",
                    "operationType": "WITHDRAW",
                    "amount": 2000
                }
                """.formatted(walletId);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        String jsonResponse = """
                {
                    "message": "Недостаточно средств!"
                }
                """;


        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(jsonResponse))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Sql("/sql/wallet-test.sql")
    void executeTransactionDeposit_ReturnStatusOk() throws Exception {

        String jsonRequest = """
                {
                    "walletId": "%s",
                    "operationType": "DEPOSIT",
                    "amount": 5000
                }
                """.formatted(walletId);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        String jsonResponse = """
                {
                    "status": "OK",
                    "newBalance": 6000.00
                }
                """;


        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(jsonResponse))
                .andExpect(status().isOk());

    }
}
