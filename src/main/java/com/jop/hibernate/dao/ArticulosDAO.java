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

import com.jop.hibernate.dto.Articulos;

/**
 * DAO para la clase Articulos
 * 
 * @author José Ortega Pérez
 * @version 1.0
 * @since 18-7-2018
 */
public class ArticulosDAO {
	
		private static final Logger log = LoggerFactory.getLogger(ArticulosDAO.class);

		private SessionFactory sessionFactory;

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		private Session getCurrentSession() {
			return sessionFactory.getCurrentSession();
		}

		public void save(Articulos transientInstance) {
			log.debug("saving Articulos instance");
			try {
				getCurrentSession().save(transientInstance);
				log.debug("save successful");
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}

		public void delete(Articulos persistentInstance) {
			log.debug("deleting Articulos instance");
			try {
				getCurrentSession().delete(persistentInstance);
				log.debug("delete successful");
			} catch (RuntimeException re) {
				log.error("delete failed", re);
				throw re;
			}
		}

		public Articulos findById(Integer codigoArticulo) {
			log.debug("getting Articulos instance with id: " + codigoArticulo);
			try {
				Articulos instance = (Articulos) getCurrentSession().get(
						"com.jop.hibernate.dto.Articulos", codigoArticulo);
				return instance;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
		}

		@SuppressWarnings("unchecked")
		public List<Articulos> findByExample(Articulos instance) {
			log.debug("finding Articulos instance by example");
			try {
				List<Articulos> results = (List<Articulos>) getCurrentSession()
						.createCriteria("com.jop.hibernate.dto.Articulos")
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
			log.debug("finding Articulos instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from Articulos as model where model."
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
			log.debug("finding all Articulos instances");
			try {
				String queryString = "from Articulos";
				Query queryObject = getCurrentSession().createQuery(queryString);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}

		public Articulos merge(Articulos detachedInstance) {
			log.debug("merging Articulos instance");
			try {
				Articulos result = (Articulos) getCurrentSession().merge(detachedInstance);
				log.debug("merge successful");
				return result;
			} catch (RuntimeException re) {
				log.error("merge failed", re);
				throw re;
			}
		}

		public void attachDirty(Articulos instance) {
			log.debug("attaching dirty Articulos instance");
			try {
				getCurrentSession().saveOrUpdate(instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public void attachClean(Articulos instance) {
			log.debug("attaching clean Articulos instance");
			try {
				getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
						instance);
				log.debug("attach successful");
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
			}
		}

		public static ArticulosDAO getFromApplicationContext(ApplicationContext ctx) {
			return (ArticulosDAO) ctx.getBean(ArticulosDAO.class);
		}

}
