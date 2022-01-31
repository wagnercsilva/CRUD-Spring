package br.com.wagner.crud.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExtratoController.class)
public class ExtratoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ExtratoController controller;

    @Test
    public void consultarExtrato() throws Exception {
        mockMvc.perform(get("/extrato/consultar")
                .accept(MediaType.APPLICATION_JSON)
                .param("conta", "12345")
                .param("data", "31/01/2022"))
                .andExpect(status().isOk());
    }
}
