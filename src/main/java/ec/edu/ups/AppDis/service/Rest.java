package ec.edu.ups.AppDis.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.AppDis.bussines.GestionON;
import ec.edu.ups.AppDis.model.Movimiento;
import ec.edu.ups.AppDis.model.Producto;

@Path("inventario")
public class Rest {

	@Inject
	private GestionON on;
	
	@GET
	@Path("/producto")
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearProducto(@QueryParam("descripcion")String descripcion,@QueryParam("stock")int stock) {
		Producto p=new Producto();
		p.setDescripcion(descripcion);
		p.setStock(stock);
		
		on.crearProducto(p);
		return Response.ok("transfiriendo").header("Access-Control-Allow-Origin", "*").build(); 
	}
	
	
	@GET
	@Path("/movimiento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMovimiento(@QueryParam("fecha")String fecha,@QueryParam("Descripcion")String Descripcion,@QueryParam("ingreso")int ingreso,
			@QueryParam("egreso")int egreso,@QueryParam("idproducto")int idproducto) {
		
		Producto producto=on.BuscarProducto(idproducto);
	
		Movimiento m=new Movimiento();
		m.setFecha(fecha);
		m.setDescripcion(Descripcion);
		m.setIngreso(ingreso);
		m.setEgreso(egreso);
		m.setSaldo(producto.getStock()+m.getIngreso()-m.getEgreso());
		m.setProducto(producto);
		
		on.crearMovimiento(m);
		return Response.ok("transfiriendo").header("Access-Control-Allow-Origin", "*").build();
	}
	
	
	@GET
	@Path("/listarMovimiento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarMovimiento() {
	List<Movimiento> lista =on.lista();
		
		return Response.ok(lista).header("Access-Control-Allow-Origin", "*").build();
	}
	
	
	@GET
	@Path("/listarProducto")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProducto() {
	List<Producto> listaProducto =on.listarProducto();
		
		return Response.ok(listaProducto).header("Access-Control-Allow-Origin", "*").build();
	}
	
	
	
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		// }
	}

}
