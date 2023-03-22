package com.api.Internetbanking.controllers;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Internetbanking.dtos.ClienteDto;
import com.api.Internetbanking.models.Cliente;
import com.api.Internetbanking.models.Conta;
import com.api.Internetbanking.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Internet Banking", description = "Api para cadastro de cliente e consulta de conta")
@CrossOrigin(origins="*")
public class ClienteController {
	
	final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	/**
	 * @param clienteDto
	 * @return
	 * Metodo responsavel por cadastrar um cliente
	 */
	@PostMapping
	@Operation(description ="Cadastra um cliente")
	public ResponseEntity<Object> cadastrarCliente(@RequestBody @Valid ClienteDto clienteDto){
		var cliente = new Cliente();
		var conta = new Conta();
		BeanUtils.copyProperties(clienteDto, cliente);
		BeanUtils.copyProperties(clienteDto.getConta(), conta);
		conta.depositar(clienteDto.getConta().getSaldo());
		cliente.setConta(conta);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
		
	}
	
	/**
	 * @return
	 * Metodo responsavel por retornar uma lista de clientes
	 * 
	 */
	@GetMapping
	@Operation(description ="Retorna uma lista de Cliente")
	public ResponseEntity<Page<Cliente>> getRetornaTodosClientes(@PageableDefault
			(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll(pageable));
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * Metodo responsavel por retornar um cliente pesquisando pelo ID.
	 */
	@GetMapping("/{id}")
	@Operation(description ="Retorna um Cliente")
	public ResponseEntity<Object> getRetornaCliente(@PathVariable(value = "id") Long id){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * Metodo responsavel por deletar um cliente pelo seu ID.
	 */
	@DeleteMapping("/{id}")
	@Operation(description ="Deleta um cliente")
	public ResponseEntity<Object> getDeletaCliente(@PathVariable(value = "id") Long id){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!!");
		}
		
		clienteService.delete(clienteOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * Metodo responsavel por retornar um cliente pesquisando pelo ID.
	 */
	@PutMapping("/{id}")
	@Operation(description ="Atualiza os dados de um cliente")
	public ResponseEntity<Object> getAtualizarCliente(@PathVariable(value = "id") Long id, @RequestBody @Valid ClienteDto clienteDto){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!!");
		}
		
		Cliente cliente = clienteOptional.get();
		
		cliente.setNome(clienteDto.getNome());
		cliente.setDataNascimento(clienteDto.getDataNascimento());
		cliente.getConta().setExclusive(clienteDto.getConta().getExclusive());
		cliente.getConta().setNumeroConta(clienteDto.getConta().getNumeroConta());

		return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * Metodo responsavel por efetuar os saques da conta.
	 */
	@PutMapping("/saque/{id}/{valor}")
	@Operation(description ="Sacar um valor")
	public ResponseEntity<Object> getSaqueValor(@PathVariable(value = "id") Long id, @PathVariable(value = "valor") BigDecimal valor){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!!");
		}
		
		Cliente cliente = clienteOptional.get();
		cliente.getConta().sacar(valor);

		return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
	}
	
	/**
	 * 
	 * @param id
	 * @param valor
	 * @return
	 * Metodo responsavel por efetuar deposito na conta
	 * 
	 */
	@PutMapping("/deposito/{id}/{valor}")
	@Operation(description ="Depositar um valor")
	public ResponseEntity<Object> getDepositoValor(@PathVariable(value = "id") Long id, @PathVariable(value = "valor") BigDecimal valor){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!!");
		}
		
		Cliente cliente = clienteOptional.get();
		cliente.getConta().depositar(valor);
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
	}
	
	/**
	 * 
	 * @param id
	 * @param valor
	 * @param id2
	 * @return
	 * Metodo responsavel por sacar e transfir o valor da conta para outra conta
	 * 
	 */
	@PutMapping("/transferencia/{id}/{valor}/{id2}")
	@Operation(description ="Trasnferir valor para outra conta")
	public ResponseEntity<Object> getTrasnferirValor(@PathVariable(value = "id") Long id, @PathVariable(value = "valor") BigDecimal valor, @PathVariable(value = "id2") Long id2){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		Optional<Cliente> clienteOptional2 = clienteService.findById(id2);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!!");
		}
		
		if(!clienteOptional2.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente para transferencia não localizado!!");
		}
		
		Cliente cliente = clienteOptional.get();
		Cliente cliente2 = clienteOptional2.get();
		cliente.getConta().transferir(valor, cliente2.getConta());
		
		ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente2));
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
	}


}
