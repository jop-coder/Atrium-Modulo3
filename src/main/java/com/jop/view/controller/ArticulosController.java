package com.jop.view.controller;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;

import com.jop.hibernate.modelo.IGestionArticulos;
import com.jop.view.model.Articulos_Bean;
import com.jop.view.model.NuevoArticulo_Bean;
import com.jop.view.utils.FacesUtils;

/**
 * Controlador para la gestión de artículos
 * 
 * @author José Ortega Pérez
 * @version 1.0
 * @since 25-7-2018
 *
 */
@ManagedBean(name = "articulosController")
@ApplicationScoped
public class ArticulosController implements Serializable {

	private static final long serialVersionUID = -7240738981304810013L;

	@ManagedProperty(value = "#{gestionArticulos}")
	private IGestionArticulos gestionArticulos;

	
	/**
	 * Método para dar de alta un nuevo artículo
	 * @return
	 */
	public String addArticulo() {
		// Obtenemos bean gestionado correspondiente al formulario de alta
		NuevoArticulo_Bean form_articulo = (NuevoArticulo_Bean) FacesUtils.getManagedBean("nuevoArticulo_bean");
		// Damos de alta el objeto NuevoArticulo que obtenemos del formulario
		gestionArticulos.nuevoArticulo(form_articulo.getNuevoArticulo());
		// Devolvemos String para navegar a la página de lista de artículos
		return "listaArticulos";
	}
	
	public String borraArticulo() {
		// Obtenemos bean gestionado correspondiente a la lista de artículos
		Articulos_Bean form_articulo = (Articulos_Bean) FacesUtils.getManagedBean("articulos_bean");
		// Borramos de la BD el objeto articuloSeleccionado que obtenemos del bean de la lista
		gestionArticulos.borrarArticulo(form_articulo.getArticuloSeleccionado());
		// Devolvemos String para navegar a la página de lista de artículos
		return "listaArticulos";
	}

	
	// Métodos asociados a FacesUtils.java para el borrado del 
	// contenido de componentes de un formulario.
	public void clearForm(ActionEvent ae) {
		// Clear bean values
		NuevoArticulo_Bean articulo = (NuevoArticulo_Bean) FacesUtils.getManagedBean("nuevoArticulo_bean");
		articulo.clear();
		// Clear component values
		// Retrieve a reference to the containing form
		UIComponent form = getContainingForm(ae.getComponent());
		// Clear all input components in the form
		clearEditableValueHolders(form);
	}

	public UIComponent getContainingForm(UIComponent component) {
		if (!(component.getParent() instanceof UIForm)) {
			return getContainingForm(component.getParent());
		} else {
			return component.getParent();
		}
	}

	public void clearEditableValueHolders(UIComponent form) {
		Iterator<UIComponent> iterator = form.getFacetsAndChildren();
		while (iterator.hasNext()) {
			UIComponent component = iterator.next();
			if (component instanceof EditableValueHolder) {
				((EditableValueHolder) component).resetValue();
			}
			clearEditableValueHolders(component);
		}

	}

	// ACCESORES PARA JSF
	public void setGestionArticulos(IGestionArticulos gestionArticulos) {
		this.gestionArticulos = gestionArticulos;
	}


}
