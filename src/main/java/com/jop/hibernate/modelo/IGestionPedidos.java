package com.jop.hibernate.modelo;

import java.util.List;

import com.jop.hibernate.dto.Pedidos;

/**
 * Interfaz para la fachada de GestionPedidos
 * @author José Ortega Pérez
 *
 */
public interface IGestionPedidos {
	
	List<Pedidos> consultarTodos();

	Pedidos consultarPorCodigo(Integer codigoPedido);
	
	Pedidos consultarConLineas(Integer numeroPedido);

	void nuevoPedido(Pedidos pedido);

	void modificarPedido(Pedidos pedido);

	void borrarPedido(Pedidos pedido);

}
