package br.com.wagner.crud.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wagner.crud.models.Cliente;
import br.com.wagner.crud.repositories.ClienteRepository;
import br.com.wagner.crud.utils.Utils;

/**
 * ClienteController
 * @author Wagner Silva
 */
@RestController
@RequestMapping("cliente")
public class ClienteController {

	private ClienteRepository clienteRepository;	

	public ClienteController(ClienteRepository clienteRepository){
		super();
		this.clienteRepository = clienteRepository;
	}
    
    @GetMapping("/health")
	public ResponseEntity<String> health() {
		return ResponseEntity.ok("Servidor Up - ".concat(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now())));
	}

	@PostMapping("/incluir")
	public ResponseEntity<String> incluirCliente(@RequestBody Cliente cliente) {
		
		try {
			cliente.setDtNasc(Utils.stringToDate(cliente.getDtNascMask()));
			clienteRepository.save(cliente);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar incluir cliente, tente novamente mais tarde!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Cliente cadastrado com sucesso!", HttpStatus.OK);
	}

	@GetMapping("/consultar")
	public ResponseEntity<?> obterClientes(@RequestParam (required = false) String conta ) {

		List<Cliente> clientes = new ArrayList<>();
		
		try {	
			if(conta != null){
				clientes.add(this.obterClienteConta(conta));
			}else{
				clientes = clienteRepository.findAll();
			}

			return clientes.get(0) != null ?  new ResponseEntity<>(clientes, HttpStatus.OK) : new ResponseEntity<String>("Cliente não cadastrado!", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao consultar clientes, tente novamente mais tarde!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	protected Cliente obterClienteConta(String conta){		
		Cliente cliente = clienteRepository.findByConta(conta);		
		return cliente;		
	}

	protected void salvar(Cliente cliente){
		clienteRepository.save(cliente);
	}

}
