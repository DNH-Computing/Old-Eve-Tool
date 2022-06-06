package nz.net.dnh.evetool.data;

import java.util.List;

import nz.net.dnh.evetool.entities.evetool.Corporation;
import nz.net.dnh.evetool.hibernate.evetool.CorporationHome;

/**
 * A hibernate backed {@link ICorporationRepository}
 * 
 * @author danielh
 *
 */
public class HibernateCorporationRepository implements ICorporationRepository {
	/**
	 * The hibernate mapping object
	 */
	private CorporationHome corpHome = new CorporationHome();
	
	/**
	 * Get by ID
	 */
	@Override
	public Corporation getCorporationByID(int corpID) {
		return this.corpHome.findById(corpID);
	}

	/**
	 * Get by Name
	 */
	@Override
	public Corporation getCorporationByName(String name) {
		return this.corpHome.findByName(name);
	}

	/**
	 * @see nz.net.dnh.evetool.data.ICorporationRepository#getAllVisibleCorporationsStartingWith(java.lang.String)
	 */
	@Override
	public List<? extends Corporation> getAllVisibleCorporationsStartingWith(
			String namePrefix) {
		return this.corpHome.findSearchableByPrefix(namePrefix);
	}

}
