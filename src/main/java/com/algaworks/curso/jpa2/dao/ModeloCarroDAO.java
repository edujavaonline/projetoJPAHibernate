package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.model.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public void salvar(ModeloCarro modeloCarro) {
		this.manager.merge(modeloCarro);
	}
	
	public List<ModeloCarro> buscarTodos() {
		return this.manager.createQuery("from ModeloCarro", ModeloCarro.class).getResultList();
	}
	
	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException {
		try {
			modeloCarro = buscarPeloCodigo(modeloCarro.getCodigo());
			manager.remove(modeloCarro);
			manager.flush();
		} catch (Exception e) {
			throw new NegocioException("Este modelo de carro não pode ser excluído!");
		}
	}
	
	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return manager.find(ModeloCarro.class, codigo);
	}
}
