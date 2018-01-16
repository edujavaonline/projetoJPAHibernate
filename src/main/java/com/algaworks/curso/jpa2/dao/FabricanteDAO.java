package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.model.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public void salvar(Fabricante fabricante) {
		this.manager.merge(fabricante);
	}
	
	public List<Fabricante> buscarTodos() {
		return this.manager.createQuery("from Fabricante", Fabricante.class).getResultList();
	}
	
	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		try {
			fabricante = buscarPeloCodigo(fabricante.getCodigo());
			manager.remove(fabricante);
			manager.flush();
		} catch (Exception e) {
			throw new NegocioException("Este fabricante não pode ser excluído!");
		} 
		
	}
	
	public Fabricante buscarPeloCodigo(Long codigo) {
		return this.manager.find(Fabricante.class, codigo);
	}
}
