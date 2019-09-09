package com.jop.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jop.hibernate.dao.LineaPedidoDAO;
import com.jop.hibernate.dto.LineaPedido;

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
public class GestionLineaPedidos implements IGestionLineaPedidos {
	
	// PROPIEDADES ****************************************
	private LineaPedidoDAO lineaPedidoDAO;

	// CONSULTAS ******************************************
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<LineaPedido> consultarTodas() {
		List<LineaPedido> lista = lineaPedidoDAO.findAll();
		return lista;
	}

	@Override
	@Transactional(readOnly=true)
	public LineaPedido consultarPorCodigo(Long codigoLineaPedido) {
		LineaPedido lineaPedido = lineaPedidoDAO.findById(codigoLineaPedido);
		return lineaPedido;
	}

	// OTRAS OPERACIONES CRUD *****************************
	@Override
	@Transactional
	public void nuevaLineaPedido(LineaPedido lineaPedido) {
		lineaPedidoDAO.save(lineaPedido);
	}

	@Override
	@Transactional
	public void modificarLineaPedido(LineaPedido lineaPedido) {
		lineaPedidoDAO.attachDirty(lineaPedido);
	}

	@Override
	@Transactional
	public void borrarLineaPedido(LineaPedido lineaPedido) {
		lineaPedidoDAO.delete(lineaPedido);
	}

	// ACCESOR PARA SPRING ********************************
	public void setLineaPedidoDAO(LineaPedidoDAO lineaPedidoDAO) {
		this.lineaPedidoDAO = lineaPedidoDAO;
	}

}
