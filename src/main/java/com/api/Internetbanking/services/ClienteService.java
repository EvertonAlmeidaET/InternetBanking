package com.api.Internetbanking.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Internetbanking.models.Cliente;
import com.api.Internetbanking.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}
	
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteRepository.findAll(pageable);
	}
	
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}
	
	@Transactional
	public void delete(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
}
