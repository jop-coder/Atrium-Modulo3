package com.jop.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jop.hibernate.dao.ArticulosDAO;
import com.jop.hibernate.dto.Articulos;

/**
 * Fachada para el uso de transacciones con Spring. La definición de las
 * transacciones con anotaciones en la clase.
 * 
 * @author José Ortega Pérez
 * @version 1.0
 * @since 18-7-2018
 *
 */
@Component
@Scope("prototype")
public class GestionArticulos implements IGestionArticulos {

	// PROPIEDADES ****************************************
	private ArticulosDAO articuloDAO;

	// CONSULTAS ******************************************
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Articulos> consultarTodos() {
		List<Articulos> lista = articuloDAO.findAll();
		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public Articulos consultarPorId(Integer codigoArticulo) {
		Articulos articulo = articuloDAO.findById(codigoArticulo);
		return articulo;
	}

	// OTRAS OPERACIONES CRUD *****************************
	@Override
	@Transactional
	public void nuevoArticulo(Articulos articulo) {
		articuloDAO.save(articulo);
	}

	@Override
	@Transactional
	public void modificarArticulo(Articulos articulo) {
		articuloDAO.attachDirty(articulo);
	}

	@Override
	@Transactional
	public void borrarArticulo(Articulos articulo) {
		articuloDAO.delete(articulo);
	}

	// ACCESOR PARA SPRING ********************************
	public void setArticuloDAO(ArticulosDAO articuloDAO) {
		this.articuloDAO = articuloDAO;
	}

}
