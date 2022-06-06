package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;

/**
 * Home object for domain model class Module.
 * @see nz.net.dnh.evetool.hibernate.evetool.Module
 * @author Hibernate Tools
 */
@Stateless
public class ModuleHome {

	private static final Log log = LogFactory.getLog(ModuleHome.class);

	@PersistenceContext @Inject
	private EntityManager entityManager;

	public void persist(Module transientInstance) {
		log.debug("persisting Module instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Module persistentInstance) {
		log.debug("removing Module instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Module merge(Module detachedInstance) {
		log.debug("merging Module instance");
		try {
			Module result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Module findById(int id) {
		log.debug("getting Module instance with id: " + id);
		try {
			Module instance = entityManager.find(Module.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
