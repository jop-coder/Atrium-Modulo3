package com.jop.view.model;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.jop.hibernate.dto.Articulos;
import com.jop.hibernate.modelo.IGestionArticulos;

/**
 * Bean gestionado correspondiente al archivo
 * alta_articulo.xhtml
 * 
 * @author José Ortega Pérez
 * @version 1.0
 * @since 25-7-2018
 *
 */
@ManagedBean(name = "nuevoArticulo_bean")
@ViewScoped
public class NuevoArticulo_Bean {
	
	// PROPIEDADES DE CLASE
	@ManagedProperty("#{gestionArticulos}")
	private IGestionArticulos gestionArticulos;
	private Articulos nuevoArticulo;
	private Boolean modificar;

	// CONSTRUCTOR
	public NuevoArticulo_Bean() {
		nuevoArticulo = new Articulos();
	}
	
	// OTROS METODOS
	/**
	 * Método para el borrado de datos del formulario.
	 */
	@PostConstruct
	public void clear() {
		nuevoArticulo.setCodigoArticulo(0);
		nuevoArticulo.setDescripcionArticulo("");
		nuevoArticulo.setPrecioUnidadArticulo(new BigDecimal(0));
		nuevoArticulo.setCantidad(0);
	}
	
	
	// ACCESORES PARA JSF ************************************************
	public IGestionArticulos getGestionArticulos() {
		return gestionArticulos;
	}

	public void setGestionArticulos(IGestionArticulos gestionArticulos) {
		this.gestionArticulos = gestionArticulos;
	}

	public Articulos getNuevoArticulo() {
		return nuevoArticulo;
	}

	public void setNuevoArticulo(Articulos nuevoArticulo) {
		this.nuevoArticulo = nuevoArticulo;
	}

	public Boolean getModificar() {
		return modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}

}
