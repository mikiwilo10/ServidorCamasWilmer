package ec.edu.ups.AppDis.bussines;

import java.util.List;

import javax.ejb.Stateless;

import ec.edu.ups.AppDis.dao.ProductoDao;
import ec.edu.ups.AppDis.model.Movimiento;
import ec.edu.ups.AppDis.model.Producto;

import javax.inject.Inject;

@Stateless
public class GestionON {

	@Inject
	private ProductoDao pdao;
	
	public void crearProducto(Producto p) {
		pdao.CrearProducto(p);
	}
	
	public Producto BuscarProducto(int id) {
		return pdao.buscar(id);
	}
	
	public void crearMovimiento(Movimiento m) {
		pdao.CrearMovimiento(m);;
	}
	
	
	public List<Movimiento> lista(){
		return pdao.listarMovimiento();
	}
	
	public List<Producto> listarProducto(){
		return pdao.listarProducto();
	}
	
	
}
