package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.model.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaModeloCarroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	private List<ModeloCarro> modelosCarros;
	
	private ModeloCarro modeloCarroSelecionado;
	
	@PostConstruct
	public void init() {
		this.modelosCarros = this.modeloCarroDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			this.modeloCarroDAO.excluir(this.modeloCarroSelecionado);
			this.modelosCarros.remove(this.modeloCarroSelecionado);
			FacesUtil.addSuccessMessage("O Modelo de Carro " + this.modeloCarroSelecionado.getDescricao() + " foi excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public ModeloCarro getModeloCarroSelecionado() {
		return modeloCarroSelecionado;
	}

	public void setModeloCarroSelecionado(ModeloCarro modeloCarroSelecionado) {
		this.modeloCarroSelecionado = modeloCarroSelecionado;
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}
	
	
}
