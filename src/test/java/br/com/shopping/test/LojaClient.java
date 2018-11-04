package br.com.shopping.test;

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

import br.com.shopping.model.Loja;

@Path("/loja")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface LojaClient {

	@PUT
	public Response adicionar(Loja loja);

	@POST
	public Response atualizar(Loja loja);

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam(value = "id") long id);

	@GET
	@Path("/{id}")
	public Response buscar(@PathParam(value = "id") long id);

	@GET
	public Response listar();

}
