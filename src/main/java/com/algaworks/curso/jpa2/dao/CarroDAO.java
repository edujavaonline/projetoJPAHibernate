package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.model.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CarroDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Carro carro) {
		this.manager.merge(carro);
	}
	
	public List<Carro> buscarTodos() {
		return this.manager.createQuery("from Carro", Carro.class).getResultList();
	}
	
	public Carro buscarPeloCodigo(Long codigo) {
		return this.manager.find(Carro.class, codigo);
	}
	
	@Transactional
	public void excluir(Carro carro) throws NegocioException {
		try {
			carro = buscarPeloCodigo(carro.getCodigo());
			this.manager.remove(carro);
			this.manager.flush();
		} catch (Exception e) {
			throw new NegocioException("Este carro não pode ser excluído!");
		}
	}
	
}
