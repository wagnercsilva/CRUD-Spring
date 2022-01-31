package br.com.wagner.crud.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wagner.crud.models.Cliente;
import br.com.wagner.crud.models.Extrato;
import br.com.wagner.crud.utils.Utils;

/**
 * ClienteController
 * @author Wagner Silva
 */
@RestController
@RequestMapping("transacoes")
public class TransacoesController {

    private static final String DEPOSITO  = "Deposito";
	private static final String SAQUE  = "Saque";

    @Autowired
    ClienteController clienteController;

    @Autowired
	ExtratoController extratoController;
    
    @PutMapping("/saque")
	public ResponseEntity<String> saque(@RequestParam String conta, @RequestParam BigDecimal valorSaque){

		try {
			Cliente cliente = clienteController.obterClienteConta(conta);
			
			BigDecimal valorTarifa = this.obterTaxaSaque(cliente.isPlanoExclusive(), valorSaque);
			
			valorSaque = valorSaque.add(valorTarifa);
			
			if(valorSaque.compareTo(cliente.getSaldo()) != 1 ){				
				
				BigDecimal saldoAtualizado = cliente.getSaldo().subtract(valorSaque);

				cliente.setSaldo(saldoAtualizado);
				clienteController.salvar(cliente);

				Extrato extrato = new Extrato(conta, Utils.dataAtualZeroHora(), SAQUE, valorSaque.subtract(valorTarifa), valorTarifa);
				extratoController.gravarExtrato(extrato);

				return new ResponseEntity<String>("Saque realizado com sucesso, Saldo Atual: ".concat(saldoAtualizado.toString()), HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("Saldo Insuficiente!", HttpStatus.OK);
			}			
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao realizar saque, tente novamente mais tarde!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping("/deposito")
	public ResponseEntity<String> deposito(@RequestParam String conta, @RequestParam BigDecimal valorDeposito){

		try {
			Cliente cliente = clienteController.obterClienteConta(conta);
			BigDecimal saldoAtualizado = cliente.getSaldo().add(valorDeposito);

			cliente.setSaldo(saldoAtualizado);
			clienteController.salvar(cliente);

			Extrato extrato = new Extrato(conta, Utils.dataAtualZeroHora(), DEPOSITO, valorDeposito, BigDecimal.ZERO);
			extratoController.gravarExtrato(extrato);
			
			return new ResponseEntity<String>("Depósito realizado com sucesso, Saldo Atual: ".concat(saldoAtualizado.toString()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao realizar depósito, tente novamente mais tarde!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	private BigDecimal obterTaxaSaque(boolean exclusive, BigDecimal valorSaque){

		if(exclusive || valorSaque.doubleValue() <= 100){
			return BigDecimal.ZERO;
		}else if(valorSaque.doubleValue() > 100 && valorSaque.doubleValue() <= 300){
			return valorSaque.multiply(new BigDecimal("0.004"));
		}else{
			return valorSaque.multiply(new BigDecimal("0.01"));
		}
		
	}
    
}
