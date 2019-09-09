package com.jop.hibernate.modelo;

/**
 * Interfaz para la fachada de GestionLineaPedidos
 * @author José Ortega Pérez
 *
 */
import java.util.List;

import com.jop.hibernate.dto.LineaPedido;

public interface IGestionLineaPedidos {

	List<LineaPedido> consultarTodas();

	LineaPedido consultarPorCodigo(Long codigoLineaPedido);

	void nuevaLineaPedido(LineaPedido lineaPedido);

	void modificarLineaPedido(LineaPedido lineaPedido);

	void borrarLineaPedido(LineaPedido lineaPedido);

}
