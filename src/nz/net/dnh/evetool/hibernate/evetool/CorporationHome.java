package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import nz.net.dnh.evetool.Bootstrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.google.inject.Inject;

/**
 * Home object for domain model class Corporation.
 * 
 * @see nz.net.dnh.evetool.hibernate.evetool.Corporation
 * @author Hibernate Tools
 */
@Stateless
public class CorporationHome {

	private static final Log log = LogFactory.getLog(CorporationHome.class);

	@PersistenceContext
	@Inject
	private EntityManager entityManager;
	
	public CorporationHome() {
		Bootstrapper.inject.injectMembers(this);
	}

	public void persist(Corporation transientInstance) {
		log.debug("persisting Corporation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Corporation persistentInstance) {
		log.debug("removing Corporation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Corporation merge(Corporation detachedInstance) {
		log.debug("merging Corporation instance");
		try {
			Corporation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Corporation findById(int id) {
		log.debug("getting Corporation instance with id: " + id);
		try {
			Corporation instance = entityManager.find(Corporation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * Find a corporation by name
	 * 
	 * @param name
	 *            The name of the corporation
	 * @return The corporation found
	 */
	public Corporation findByName(String name) {
		log.debug("getting corporation with name: " + name);
		try {
			CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Corporation> criteria = builder
					.createQuery(Corporation.class);
			Root<Corporation> corpRoot = criteria.from(Corporation.class);
			criteria.select(corpRoot);
			criteria.where(builder.equal(corpRoot.get("corpName"), name));
			List<Corporation> instance = this.entityManager.createQuery(criteria)
					.getResultList();
			
			if (instance.size() == 0)
				return null;

			log.debug("got corporation");
			return instance.get(0);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Corporation> findSearchableByPrefix(String namePrefix) {
		log.debug("Searching for all visiable corporations stating with: "
				+ namePrefix);
		try {
			// CriteriaBuilder builder =
			// this.entityManager.getCriteriaBuilder();
			// CriteriaQuery<Corporation> criteria =
			// builder.createQuery(Corporation.class);
			// Root<Corporation> corpRoot = criteria.from(Corporation.class);
			// criteria.select(corpRoot);
			// criteria.where(builder.like(corpRoot.get("corpName"),
			// namePrefix+"%"));
			// Corporation instance =
			// this.entityManager.createQuery(criteria).getSingleResult();

			List<Corporation> instance = this.entityManager.createQuery(
					"from Corporation where findable=1 and corpName like '"
							+ namePrefix + "%'", Corporation.class)
					.getResultList();

			log.debug("got corporation");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
