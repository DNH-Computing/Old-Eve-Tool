package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import nz.net.dnh.evetool.GuiceBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;

/**
 * Home object for domain model class User.
 * 
 * @see nz.net.dnh.evetool.hibernate.evetool.User
 * @author Hibernate Tools
 */
@Stateless
public class UserHome extends GuiceBase {

	private static final Log log = LogFactory.getLog(UserHome.class);

	@PersistenceContext @Inject
	private EntityManager entityManager;

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		log.debug("removing User instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(UserId id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = entityManager.find(User.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<User> findAll()  {
		log.debug("getting all users");
		try {
			CriteriaQuery<User> query = entityManager.getCriteriaBuilder().createQuery(User.class);
			Root<User> userRoot = query.from(User.class);
			query.select(userRoot);
			
			List<User> users = entityManager.createQuery(query).getResultList();
			log.debug("get all successful");
			return users;
		} catch (RuntimeException re) {
			log.error("get all failed", re);
			throw re;
		}
	}
}
