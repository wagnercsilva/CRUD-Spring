package br.com.wagner.crud.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExtratoTest {

    private Extrato model = new Extrato("conta", new Date(), "tipo", BigDecimal.ZERO, BigDecimal.ZERO);

    @Test
    public void testModelExtrato(){

        assertNotNull(model.getConta());
        assertNotNull(model.getData());
        assertNotNull(model.getTipo());
        assertNotNull(model.getValor());
        assertNotNull(model.getValorTarifa());
    }
    
    
}
