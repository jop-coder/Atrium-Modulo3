package com.jop.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.jop.hibernate.dto.Pedidos;

/**
 * Extensión de PedidosDAO para añadir métodos adicionales.
 * 
 * @author José Ortega Pérez
 * @version 1.0
 * @since 18-7-2018
 *
 */
public class PedidosDAOext extends PedidosDAO {

	/**
	 * Consulta de pedido con resolución de carga vaga.
	 * 
	 * @param numeroPedido
	 * @return pedido
	 */
	public Pedidos consultarConLineas(Integer numeroPedido) {

		// CREACION DE CRITERIA Y RESTRICCIONES
		Criteria c = getCurrentSession().createCriteria(Pedidos.class);
		c.setFetchMode("lineaPedidos", FetchMode.JOIN);
		c.setFetchMode("lineaPedidos.articulos", FetchMode.JOIN);
		c.add(Restrictions.idEq(numeroPedido));
		
		// GESTION DEL PRODUCTO CARTESIANO
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		// CONSULTA
		Pedidos pedido = null;
		try {
			pedido = (Pedidos) c.uniqueResult();
		} catch (HibernateException e) {
			System.out.println("Error al consultar pedido con resolución de carga vaga.");
			e.printStackTrace();
		}

		// DEVOLVEMOS RESULTADO
		return pedido;
	}

}
