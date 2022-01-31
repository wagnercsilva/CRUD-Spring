package br.com.wagner.crud.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteTest {

    private Cliente model = new Cliente("nome", false , BigDecimal.ZERO, "conta", new Date(), "dtNascMask");

    @Test
    public void testModelCliente(){

        assertNotNull(model.getConta());
        assertNotNull(model.getDtNasc());
        assertNotNull(model.getDtNascMask());
        assertNotNull(model.getId());
        assertNotNull(model.getNome());
        assertNotNull(model.isPlanoExclusive());
        assertNotNull(model.getSaldo());
    }
    
    
}
