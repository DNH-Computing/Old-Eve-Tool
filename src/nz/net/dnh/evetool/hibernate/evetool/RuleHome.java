package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;

/**
 * Home object for domain model class Rule.
 * @see nz.net.dnh.evetool.hibernate.evetool.Rule
 * @author Hibernate Tools
 */
@Stateless
public class RuleHome {

	private static final Log log = LogFactory.getLog(RuleHome.class);

	@PersistenceContext @Inject
	private EntityManager entityManager;

	public void persist(Rule transientInstance) {
		log.debug("persisting Rule instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Rule persistentInstance) {
		log.debug("removing Rule instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Rule merge(Rule detachedInstance) {
		log.debug("merging Rule instance");
		try {
			Rule result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rule findById(RuleId id) {
		log.debug("getting Rule instance with id: " + id);
		try {
			Rule instance = entityManager.find(Rule.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
