package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;

/**
 * Home object for domain model class AuthorisedCorporations.
 * @see nz.net.dnh.evetool.hibernate.evetool.AuthorisedCorporations
 * @author Hibernate Tools
 */
@Stateless
public class AuthorisedCorporationsHome {

	private static final Log log = LogFactory
			.getLog(AuthorisedCorporationsHome.class);

	@PersistenceContext @Inject
	private EntityManager entityManager;

	public void persist(AuthorisedCorporations transientInstance) {
		log.debug("persisting AuthorisedCorporations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AuthorisedCorporations persistentInstance) {
		log.debug("removing AuthorisedCorporations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AuthorisedCorporations merge(AuthorisedCorporations detachedInstance) {
		log.debug("merging AuthorisedCorporations instance");
		try {
			AuthorisedCorporations result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AuthorisedCorporations findById(AuthorisedCorporationsId id) {
		log.debug("getting AuthorisedCorporations instance with id: " + id);
		try {
			AuthorisedCorporations instance = entityManager.find(
					AuthorisedCorporations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
