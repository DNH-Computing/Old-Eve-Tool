package nz.net.dnh.evetool.auth;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import nz.net.dnh.common.auth.AuthenticationAdaptor;
import nz.net.dnh.common.auth.AuthenticationResult;
import nz.net.dnh.common.auth.Result;
import nz.net.dnh.common.guice.InjectLogger;
import nz.net.dnh.evetool.GuiceBase;
import nz.net.dnh.evetool.data.IUserRepository;
import nz.net.dnh.evetool.entities.evetool.User;

/**
 * Authenticate a user against the hibernate backend
 * 
 * @author danielh
 * 
 */
public final class BasicAuthenticationAdaptor extends GuiceBase implements
		AuthenticationAdaptor {
	/**
	 * A logger for this class
	 */
	@InjectLogger
	private Logger logger;

	/**
	 * The user repository
	 */
	@Inject
	private IUserRepository userRepo;

	/**
	 * The corporation the user belongs to
	 */
	private final String corpName;

	/**
	 * The username to check
	 */
	private final String usernane;

	/**
	 * The password of the user
	 */
	private final String password;

	/**
	 * Sets up the authentication adaptor
	 * 
	 * @param username
	 *            The username of the user
	 * @param password
	 *            The password of the user
	 * @param corpname
	 *            The name of the corporation the user belongs to
	 */
	public BasicAuthenticationAdaptor(String username, String password,
			String corpname) {
		super();

		if (this.logger.isDebugEnabled())
			this.logger.debug("Authentication requested for user '" + username
					+ "' from corp '" + corpname + "' with password '"
					+ password + "'");

		this.corpName = corpname;
		this.usernane = username;
		this.password = password;
	}

	/**
	 * Perform the authentication
	 */
	@Override
	public AuthenticationResult authenticate() {
		User u = this.userRepo.getUser(this.usernane, this.corpName);

		if (u == null)
			return new AuthenticationResult(Result.FAILURE_IDENTITY_NOT_FOUND,
					null);

		if (u.isPassword(this.password))
			return new AuthenticationResult(Result.SUCCESS, u);
		else
			return new AuthenticationResult(Result.FAILURE_CREDENTIAL_INVALID,
					null);
	}

}
