package nz.net.dnh.evetool.hibernate.eve;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nz.net.dnh.evetool.hibernate.evetool.UserHome;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;

@Stateless
public class ItemHome {
	private static final Log log = LogFactory.getLog(UserHome.class);

	@PersistenceContext @Inject @Named("eve")
	private EntityManager entityManager;
	
	public Item findItemByID(int typeID) {
		if (log.isDebugEnabled())
			log.debug("getting item with typeid: "+typeID);
		
		try {
			Item result = entityManager.find(Item.class, typeID);
			log.debug("get successful");
			return result;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
