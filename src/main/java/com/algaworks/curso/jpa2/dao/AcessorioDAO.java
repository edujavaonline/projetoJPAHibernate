package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.model.Acessorio;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AcessorioDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Acessorio acessorio) {
		this.manager.merge(acessorio);
	}
	
	public List<Acessorio> buscarTodos() {
		return this.manager.createQuery("from Acessorio", Acessorio.class).getResultList();
	}
	
	public Acessorio buscarPeloCodigo(Long codigo) {
		return this.manager.find(Acessorio.class, codigo);
	}
	
	@Transactional
	public void excluir(Acessorio acessorio) throws NegocioException {
		try {
			acessorio = buscarPeloCodigo(acessorio.getCodigo());
			this.manager.remove(acessorio);
			this.manager.flush();
		} catch (Exception e) {
			throw new NegocioException("Este acessório não pode ser excluído!");
		}
	}
}
