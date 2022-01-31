package br.com.wagner.crud.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

@WebMvcTest(TransacoesController.class)
public class TransacoesControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TransacoesController controller;

    @Test
    public void deposito() throws Exception {
        mockMvc.perform(put("/transacoes/deposito")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("conta", "12345")
                .param("valorDeposito", BigDecimal.valueOf(100).toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void saque() throws Exception {
        mockMvc.perform(put("/transacoes/saque")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("conta", "12345")
                .param("valorSaque", BigDecimal.valueOf(50).toString()))
                .andExpect(status().isOk());
    }
    
}
