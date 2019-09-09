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

import com.jop.hibernate.dto.Pedidos;

/**
 * 
 * DAO para la clase Pedidos
 *
 * @author José Ortega Pérez
 * @version 1.0
 * @since 18-7-2018
 */
public class PedidosDAO {
	
		private static final Logger log = LoggerFactory.getLogger(PedidosDAO.class);

		private SessionFactory sessionFactory;

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		protected Session getCurrentSession() {
			return sessionFactory.getCurrentSession();
		}

		public void save(Pedidos transientInstance) {
			log.debug("saving Pedidos instance");
			try {
				getCurrentSession().save(transientInstance);
				log.debug("save successful");
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}

		public void delete(Pedidos persistentInstance) {
			log.debug("deleting Pedidos instance");
			try {
				getCurrentSession().delete(persistentInstance);
				log.debug("delete successful");
			} catch (RuntimeException re) {
				log.error("delete failed", re);
				throw re;
			}
		}

		public Pedidos findById(Integer numeroPedido) {
			log.debug("getting Pedidos instance with id: " + numeroPedido);
			try {
				Pedidos instance = (Pedidos) getCurrentSession().get(
						"com.jop.hibernate.dto.Pedidos", numeroPedido);
				return instance;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
		}

		public List<Pedidos> findByExample(Pedidos instance) {
			log.debug("finding Pedidos instance by example");
			try {
				List<Pedidos> results = (List<Pedidos>) getCurrentSession()
						.createCriteria("com.jop.hibernate.dto.Pedidos")
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
			log.debug("finding Pedidos instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from Pedidos as model where model."
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
			log.debug("finding all Pedidos instances");
			try {
				String queryString = "from Pedidos";
				Query queryObject = getCurrentSession().createQuery(queryString);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}

		public Pedidos merge(Pedidos detachedInstance) {
			log.debug("merging Pedidos instance");
			try {
				Pedidos result = (Pedidos) getCurrentSession().merge(detachedInstance);
				log.debug("merge successful");
				return result;
			} catch (RuntimeException re) {
				log.error("merge failed", re);
				throw re;
			}
		}

		public void attachDirty(Pedidos instance) {
			log.debug("attaching dirty Pedidos instance");
			try {
				getCurrentSession().saveOrUpdate(instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public void attachClean(Pedidos instance) {
			log.debug("attaching clean Pedidos instance");
			try {
				getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
						instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public static PedidosDAO getFromApplicationContext(ApplicationContext ctx) {
			return (PedidosDAO) ctx.getBean("PedidosDAO");
		}

}
