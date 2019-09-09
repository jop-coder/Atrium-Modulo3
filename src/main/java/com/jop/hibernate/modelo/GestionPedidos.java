package com.jop.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jop.hibernate.dao.PedidosDAOext;
import com.jop.hibernate.dto.Pedidos;

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
public class GestionPedidos implements IGestionPedidos {
	
	// PROPIEDADES ****************************************
	private PedidosDAOext pedidoDAO;

	// CONSULTAS ******************************************
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Pedidos> consultarTodos() {
		List<Pedidos> lista = pedidoDAO.findAll();
		return lista;
	}

	@Override
	@Transactional(readOnly=true)
	public Pedidos consultarPorCodigo(Integer codigoPedido) {
		Pedidos pedido = pedidoDAO.findById(codigoPedido);
		return pedido;
	}

	@Override
	@Transactional(readOnly=true)
	public Pedidos consultarConLineas(Integer numeroPedido) {
		Pedidos pedido = pedidoDAO.consultarConLineas(numeroPedido);
		return pedido;
	}

	// OTRAS OPERACIONES CRUD *****************************
	@Override
	@Transactional
	public void nuevoPedido(Pedidos pedido) {
		pedidoDAO.save(pedido);
	}

	@Override
	@Transactional
	public void modificarPedido(Pedidos pedido) {
		pedidoDAO.attachDirty(pedido);
	}

	@Override
	@Transactional
	public void borrarPedido(Pedidos pedido) {
		pedidoDAO.delete(pedido);
	}

	// ACCESOR PARA SPRING ********************************
	public void setPedidoDAO(PedidosDAOext pedidosDAO) {
		this.pedidoDAO = pedidosDAO;
	}
	
}
