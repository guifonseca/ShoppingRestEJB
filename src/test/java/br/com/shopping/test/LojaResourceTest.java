package br.com.shopping.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.shopping.model.Loja;
import br.com.shopping.model.Telefone;
import br.com.shopping.test.error.BeanValidationError;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LojaResourceTest {
	
	private static final String path = "http://localhost:8080/shopping";
	private LojaClient proxy = null;
	private static long id;
	private static long idTelefone;
	
	@Before
	public void init() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
		proxy = target.proxy(LojaClient.class);
	}

	@Test
	public void teste01_adicionar() {
		Loja loja = new Loja();
		loja.setNome("teste");
		loja.setPiso(1);
		
		Telefone t1 = new Telefone();
		t1.setNumero(111111111L);
		
		Set<Telefone> telefones = new HashSet<Telefone>();
		telefones.add(t1);
		
		loja.setTelefones(telefones);
		
		Response response = proxy.adicionar(loja);
		Loja retorno = response.readEntity(Loja.class);
		
		id = retorno.getId();
		
		assertEquals(200, response.getStatus());
		assertEquals(loja.getNome(), retorno.getNome());
		assertEquals(loja.getPiso(), retorno.getPiso());
		
		for (Telefone telefone : retorno.getTelefones()) {
			idTelefone = telefone.getId();
			assertEquals(t1.getNumero(), telefone.getNumero());
		}
		
		response.close();
	}
	
	@Test
	public void teste02_adicionar_com_erro_nome_ja_cadastrado() {
		Loja loja = new Loja();
		loja.setNome("teste");
		loja.setPiso(1);
		
		Telefone t1 = new Telefone();
		t1.setNumero(111111111L);
		
		Set<Telefone> telefones = new HashSet<Telefone>();
		telefones.add(t1);
		
		loja.setTelefones(telefones);
		
		Response response = proxy.adicionar(loja);
		BeanValidationError error = response.readEntity(BeanValidationError.class);
		
		assertEquals(400, response.getStatus());
		
		String msgErro = error.getParameterViolations().get(0).getMessage();
		assertEquals("Nome ja cadastrado.", msgErro);
		
		response.close();
	}
	
	@Test
	public void teste03_atualizar() {
		Loja loja = new Loja();
		loja.setId(id);
		loja.setNome("teste 2");
		loja.setPiso(1);
		
		Telefone t1 = new Telefone();
		t1.setId(idTelefone);
		t1.setNumero(222222222L);
		
		Set<Telefone> telefones = new HashSet<Telefone>();
		telefones.add(t1);
		
		loja.setTelefones(telefones);
		
		Response response = proxy.atualizar(loja);
		Loja retorno = response.readEntity(Loja.class);
		
		assertEquals(200, response.getStatus());
		assertEquals(loja.getId(), retorno.getId());
		assertEquals(loja.getNome(), retorno.getNome());
		assertEquals(loja.getPiso(), retorno.getPiso());
		
		for (Telefone telefone : retorno.getTelefones()) 
			assertEquals(t1.getNumero(), telefone.getNumero());
		
		response.close();
	}
	
	@Test
	public void teste04_buscar() {
		Loja loja = new Loja();
		loja.setId(id);
		loja.setNome("teste 2");
		loja.setPiso(1);
		
		Telefone t1 = new Telefone();
		t1.setId(idTelefone);
		t1.setNumero(222222222L);
		
		Set<Telefone> telefones = new HashSet<Telefone>();
		telefones.add(t1);
		
		loja.setTelefones(telefones);
		
		Response response = proxy.buscar(id);
		Loja retorno = response.readEntity(Loja.class);
		
		assertEquals(200, response.getStatus());
		assertEquals(loja.getId(), retorno.getId());
		assertEquals(loja.getNome(), retorno.getNome());
		assertEquals(loja.getPiso(), retorno.getPiso());
		
		for (Telefone telefone : retorno.getTelefones()) 
			assertEquals(t1.getNumero(), telefone.getNumero());
		
		response.close();
	}	
	
	@Test
	public void teste05_excluir() {	
		Response response = proxy.excluir(id);
		
		assertEquals(200, response.getStatus());
		
		response.close();
	}	
	
	@Test
	public void teste06_buscar_sem_retornar_conteudo_codigo_204() {
		Response response = proxy.buscar(id);
		
		assertEquals(204, response.getStatus());
		
		response.close();
	}	
}
