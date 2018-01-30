package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.model.Carro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CadastroCarroService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carroDAO;
	
	@Transactional
	public void salvar(Carro carro) throws NegocioException {
		if(carro.getChassi() == null || carro.getChassi().trim().equals("")) {
			throw new NegocioException("O nº do chassi é obrigatório!");
		}
		if(carro.getCor() == null || carro.getCor().trim().equals("")) {
			throw new NegocioException("A cor é obrigatório!");
		}
		if(carro.getValorDiaria() == null) {
			throw new NegocioException("O valor da diária é obrigatório!");
		}
		if(carro.getPlaca() == null || carro.getPlaca().trim().equals("")) {
			throw new NegocioException("O nº da placa é obrigatório!");
		}
		if(carro.getModeloCarro() == null) {
			throw new NegocioException("O modelo do carro é obrigatório!");
		}
		this.carroDAO.salvar(carro);
	}

}
