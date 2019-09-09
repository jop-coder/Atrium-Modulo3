package com.jop.view.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.icefaces.ace.event.RowEditEvent;
import org.icefaces.ace.event.SelectEvent;
import org.icefaces.ace.event.UnselectEvent;
import org.icefaces.ace.model.table.RowStateMap;

import com.jop.hibernate.dto.Articulos;
import com.jop.hibernate.modelo.IGestionArticulos;

/**
 * Bean gestionado correspondiente al archivo
 * lista_articulos.xhtml
 * 
 * @author José Ortega Pérez
 * @version 1.0
 * @since 25-7-2018
 *
 */
@ManagedBean(name = "articulos_bean")
@ViewScoped
public class Articulos_Bean {
	
	// PROPIEDADES DE CLASE
	private List<Articulos> lista;
	@ManagedProperty("#{gestionArticulos}")
	private IGestionArticulos gestionArticulos;
	private Articulos articuloSeleccionado;
	private boolean seleccionado;
    private List selectedRows;
    private RowStateMap stateMap = new RowStateMap();

	/**
	 * Método encargado de poblar la lista de artículos.
	 */
	@PostConstruct
	public void cargarArticulos() {
		lista = gestionArticulos.consultarTodos();
		seleccionado = false;
	}
	
	 // ESCUCHADORES DE EVENTOS *************************************
	/**
	 * Escuchador de selección del datatable
	 * @param event
	 */
    public void selectListener(SelectEvent event) {
        articuloSeleccionado = (Articulos)event.getObject();
        seleccionado = true;
    }
    
    /**
     * Escuchador de deselección del datatable
     * @param event
     */
    public void deselectListener(UnselectEvent event) {
    	seleccionado = false;
    }
    
    /**
     * Escuchador de edición de fila del datatable
     * @param evento
     */
    public void rowEditListener(RowEditEvent evento) {
		Articulos articulo = (Articulos) evento.getObject();
		gestionArticulos.modificarArticulo(articulo);
	}

	// ACCESORES PARA JSF *******************************************
	public List<Articulos> getLista() {
		return lista;
	}

	public void setLista(List<Articulos> lista) {
		this.lista = lista;
	}

	public IGestionArticulos getGestionArticulos() {
		return gestionArticulos;
	}

	public void setGestionArticulos(IGestionArticulos gestionArticulos) {
		this.gestionArticulos = gestionArticulos;
	}

	public Articulos getArticuloSeleccionado() {
		return articuloSeleccionado;
	}

	public void setArticuloSeleccionado(Articulos articuloSeleccionado) {
		this.articuloSeleccionado = articuloSeleccionado;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public List getSelectedRows() {
		return selectedRows;
	}

	public void setSelectedRows(List selectedRows) {
		this.selectedRows = selectedRows;
	}

	public RowStateMap getStateMap() {
		return stateMap;
	}

	public void setStateMap(RowStateMap stateMap) {
		this.stateMap = stateMap;
	}

}
