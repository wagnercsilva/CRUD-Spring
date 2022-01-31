package br.com.wagner.crud.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wagner.crud.models.Extrato;
import br.com.wagner.crud.repositories.ExtratoRepository;
import br.com.wagner.crud.utils.Utils;

/**
 * ExtratoController
 * @author Wagner Silva
 */
@RestController
@RequestMapping("extrato")
public class ExtratoController {

	private ExtratoRepository extratoRepository;

	public ExtratoController(ExtratoRepository extratoRepository){
		super();
		this.extratoRepository = extratoRepository;
	}

	@GetMapping("/consultar")
	public ResponseEntity<List<Extrato>> consultarExtrato(@RequestParam String conta, @RequestParam String data){
		
		try {
			List<Extrato> extrato = new ArrayList<>();

			extrato = extratoRepository.findByDataAndConta(Utils.stringToDate(data), conta);

			return new ResponseEntity<>(extrato, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}

	protected void gravarExtrato(Extrato extrato){

		try {
			extratoRepository.save(extrato);
		} catch (Exception e) {
			System.out.println("Erro na gravação do extrato");
		}

	}
	

}
