package ec.edu.ups.AppDis.view;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.Suspended;

import ec.edu.ups.AppDis.bussines.GestionON;
import ec.edu.ups.AppDis.model.Movimiento;
import ec.edu.ups.AppDis.model.Producto;

@WebServlet("/prodcuto")
public class RegistoProducto   extends HttpServlet{
	
	@Inject
	private GestionON on;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("<h1>Hola mundo</h1>");
		try {
			Producto p=new Producto();
			p.setDescripcion("Plasma 4k");
			p.setStock(23);
		
			on.crearProducto(p);
			
			Movimiento m=new Movimiento();
			m.setFecha("3/04/20");
			m.setDescripcion("Ingreso #0001");
			m.setIngreso(10);
			m.setSaldo(p.getStock()+m.getIngreso() - m.getEgreso());
			m.setProducto(p);
			
			on.crearMovimiento(m);
			
			
			List<Movimiento> lista =on.lista();
			for (Movimiento mm : lista) {
				System.out.println(mm.getDescripcion());
				System.out.println(mm.getFecha());
				System.out.println(mm.getSaldo());
				System.out.println(mm.getProducto().getDescripcion());
			}	
			
		}
		 catch (Exception e) {
			response.getWriter().println("<h1>"+e.getMessage()+"</h1>");
		}
	}

}
