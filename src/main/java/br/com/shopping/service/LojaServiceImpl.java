package br.com.shopping.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import br.com.shopping.model.Loja;

@Stateless
public class LojaServiceImpl implements LojaService {
	
	private final static Logger log = Logger.getLogger(LojaServiceImpl.class);
	
	@PersistenceContext(unitName="shoppingPU")
    private EntityManager em;

	public Loja adicionar(Loja loja) {
		em.persist(loja);
		return em.find(Loja.class, loja.getId());
	}

	public Loja atualizar(Loja loja) {
		return em.merge(loja);
	}

	public void excluir(long id) {
		Loja loja = em.find(Loja.class, id);
		em.remove(loja);
	}

	public Loja buscar(long id) {
		return em.find(Loja.class, id);
	}

	public List<Loja> listar() {
		return em.createQuery("SELECT l FROM Loja l", Loja.class).getResultList();
	}

	public Loja buscarPorNome(String nome) {
		Loja loja = null;
		try {
			loja = em.createQuery("SELECT l FROM Loja l where l.nome = :nome", Loja.class).setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			log.debug("buscarPorNome() - Query n�o retornou resultado.");
		}
		return loja;
	}
}
