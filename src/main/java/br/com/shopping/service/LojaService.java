package br.com.shopping.service;

import java.util.List;

import javax.ejb.LocalBean;

import br.com.shopping.model.Loja;

@LocalBean
public interface LojaService {
	Loja adicionar(Loja loja);
	Loja atualizar(Loja loja);
	void excluir(long id);
	Loja buscar(long id);
	Loja buscarPorNome(String nome);
	List<Loja> listar();
}
