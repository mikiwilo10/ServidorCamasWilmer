package ec.edu.ups.AppDis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.AppDis.model.Movimiento;
import ec.edu.ups.AppDis.model.Producto;

@Stateless
public class ProductoDao {

	@PersistenceContext
	private EntityManager em;
	
	public void CrearProducto(Producto p) {
		em.persist(p);
	}
	
	public Producto buscar(int id) {
		return em.find(Producto.class, id);
	}
	
	public void CrearMovimiento(Movimiento m) {
		em.persist(m);
	}
	
	
	public List<Movimiento> listarMovimiento(){
		String jpql="SELECT p FROM Movimiento p";
		Query q=em.createQuery(jpql,Movimiento.class);
		return q.getResultList();
	}
	
}
