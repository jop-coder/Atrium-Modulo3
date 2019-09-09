package com.jop.hibernate.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.jop.hibernate.dto.LineaPedido;

/**
 * 
 * DAO para la clase LineaPedido
 *
 * @author José Ortega Pérez
 * @version 1.0
 * @since 18-7-2018
 * 
 */
public class LineaPedidoDAO {
	
		private static final Logger log = LoggerFactory.getLogger(LineaPedidoDAO.class);

		private SessionFactory sessionFactory;

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		private Session getCurrentSession() {
			return sessionFactory.getCurrentSession();
		}

		public void save(LineaPedido transientInstance) {
			log.debug("saving LineaPedido instance");
			try {
				getCurrentSession().save(transientInstance);
				log.debug("save successful");
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}

		public void delete(LineaPedido persistentInstance) {
			log.debug("deleting LineaPedido instance");
			try {
				getCurrentSession().delete(persistentInstance);
				log.debug("delete successful");
			} catch (RuntimeException re) {
				log.error("delete failed", re);
				throw re;
			}
		}

		public LineaPedido findById(Long id) {
			log.debug("getting LineaPedido instance with id: " + id);
			try {
				LineaPedido instance = (LineaPedido) getCurrentSession().get(
						"com.jop.hibernate.dto.LineaPedido", id);
				return instance;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
		}

		public List<LineaPedido> findByExample(LineaPedido instance) {
			log.debug("finding LineaPedido instance by example");
			try {
				List<LineaPedido> results = (List<LineaPedido>) getCurrentSession()
						.createCriteria("com.jop.hibernate.dto.LineaPedido")
						.add(create(instance)).list();
				log.debug("find by example successful, result size: "
						+ results.size());
				return results;
			} catch (RuntimeException re) {
				log.error("find by example failed", re);
				throw re;
			}
		}

		public List findByProperty(String propertyName, Object value) {
			log.debug("finding LineaPedido instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from LineaPedido as model where model."
						+ propertyName + "= ?";
				Query queryObject = getCurrentSession().createQuery(queryString);
				queryObject.setParameter(0, value);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
		}

		public List findAll() {
			log.debug("finding all LineaPedido instances");
			try {
				String queryString = "from LineaPedido";
				Query queryObject = getCurrentSession().createQuery(queryString);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}

		public LineaPedido merge(LineaPedido detachedInstance) {
			log.debug("merging Articulos instance");
			try {
				LineaPedido result = (LineaPedido) getCurrentSession().merge(detachedInstance);
				log.debug("merge successful");
				return result;
			} catch (RuntimeException re) {
				log.error("merge failed", re);
				throw re;
			}
		}

		public void attachDirty(LineaPedido instance) {
			log.debug("attaching dirty LineaPedido instance");
			try {
				getCurrentSession().saveOrUpdate(instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public void attachClean(LineaPedido instance) {
			log.debug("attaching clean LineaPedido instance");
			try {
				getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
						instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public static LineaPedidoDAO getFromApplicationContext(ApplicationContext ctx) {
			return (LineaPedidoDAO) ctx.getBean("LineaPedidoDAO");
		}

}
