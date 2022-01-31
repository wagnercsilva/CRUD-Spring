package br.com.wagner.crud.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.wagner.crud.models.Cliente;
import br.com.wagner.crud.utils.Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ClienteController controller;

    @Test
    public void incluirCliente() throws Exception {
        mockMvc.perform(post("/cliente/incluir")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString(new Cliente("teste", false, BigDecimal.ZERO, "12001", Utils.stringToDate("01/01/2001"), ""))))
                .andExpect(status().isOk());
    }

    @Test
    public void consultarCliente() throws Exception {
        mockMvc.perform(get("/cliente/consultar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String jsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

