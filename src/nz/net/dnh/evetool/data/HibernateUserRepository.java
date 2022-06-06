package nz.net.dnh.evetool.data;

import com.google.inject.Inject;

import nz.net.dnh.evetool.entities.evetool.Corporation;
import nz.net.dnh.evetool.entities.evetool.User;
import nz.net.dnh.evetool.hibernate.evetool.UserHome;
import nz.net.dnh.evetool.hibernate.evetool.UserId;

/**
 * A hibernate backed user repository
 * 
 * @author danielh
 *
 */
public class HibernateUserRepository implements IUserRepository {
	/**
	 * The hibernate broker object
	 */
	private UserHome home = new UserHome();
	
	/**
	 * Repository for getting information about corporations
	 */
	@Inject
	private ICorporationRepository corpRepo;

	/**
	 * Return the guest user
	 * 
	 * @return	Null since there is no guest user
	 */
	@Override
	public User getGuestUser() {
		return null; // There is no guest user
	}

	/**
	 * Get the given user
	 */
	@Override
	public User getUser(String username, String corpname) {
		Corporation c = this.corpRepo.getCorporationByName(corpname);
		
		if (c == null)
			return null;
		
		return this.home.findById(new UserId(username, c.getCorpId()));
	}

	/**
	 * Get the user
	 */
	@Override
	public User getUser(String username, Corporation corp) {
		return this.home.findById(new UserId(username, corp.getCorpId()));
	}

}
