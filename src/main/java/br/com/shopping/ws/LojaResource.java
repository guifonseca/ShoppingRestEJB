package br.com.shopping.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import br.com.shopping.model.Loja;
import br.com.shopping.service.LojaService;
import br.com.shopping.validation.UniqueName;

@Path("/loja")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class LojaResource {
	
	private final static Logger log = Logger.getLogger(LojaResource.class);

	@EJB
	private LojaService lojaService;

	@PUT
	public Response adicionar(@Valid @UniqueName Loja loja) {
		Loja retorno = null;
		try {
			retorno = lojaService.adicionar(loja);
			if (retorno == null)
				return Response.notModified().build();
		} catch (Exception e) {
			log.error("adicionar()", e);
			return Response.serverError().build();
		}
		return Response.ok(retorno).build();
	}

	@POST
	public Response atualizar(@Valid @UniqueName Loja loja) {
		Loja retorno = null;
		try {
			retorno = lojaService.atualizar(loja);
			if (retorno == null)
				return Response.notModified().build();
		} catch (Exception e) {
			log.error("atualizar()", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok(retorno).build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam(value = "id") long id) {
		try {
			Loja loja = lojaService.buscar(id);

			if (loja == null)
				return Response.noContent().build();

			lojaService.excluir(id);
		} catch (Exception e) {
			log.error("excluir()", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	public Response buscar(@PathParam(value = "id") long id) {
		Loja loja = null;
		try {
			loja = lojaService.buscar(id);
			if (loja == null)
				return Response.noContent().build();
		} catch (Exception e) {
			log.error("buscar()", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok(loja).build();
	}

	@GET
	public Response listar() {
		List<Loja> lojas = new ArrayList<Loja>();
		try {
			lojas = lojaService.listar();

			if (lojas == null || lojas.isEmpty())
				return Response.noContent().build();
		} catch (Exception e) {
			log.error("listar()", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok(lojas).build();
	}

}
