package com.jop.hibernate.modelo;

import java.util.List;

import com.jop.hibernate.dto.Articulos;

/**
 * Interfaz para la fachada de GestionArticulos
 * @author José Ortega Pérez
 *
 */
public interface IGestionArticulos {
	
	List<Articulos> consultarTodos();
	
	Articulos consultarPorId(Integer codigoArticulo);
	
	void nuevoArticulo(Articulos articulo);
	
	void modificarArticulo(Articulos articulo);
	
	void borrarArticulo(Articulos articulo);

}
