package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.repository.ClienteRepository;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CatalagoClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente){
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

		if(emailEmUso){
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
				return clienteRepository.save(cliente);
	}
	@Transactional
	public void excluir(Long clienteId){
		clienteRepository.deleteById(clienteId);
	}

}
