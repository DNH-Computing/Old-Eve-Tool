package nz.net.dnh.evetool;

import java.util.Properties;

import javax.persistence.EntityManager;

import nz.net.dnh.common.auth.AuthenticatorFactory;
import nz.net.dnh.common.controller.HelperBroker;
import nz.net.dnh.common.guice.Log4JTypeListener;
import nz.net.dnh.common.tag.HeadTitleFactory;
import nz.net.dnh.evetool.data.HibernateCorporationRepository;
import nz.net.dnh.evetool.data.HibernateUserRepository;
import nz.net.dnh.evetool.data.ICorporationRepository;
import nz.net.dnh.evetool.data.IUserRepository;
import nz.net.dnh.evetool.hibernate.eve.Item;
import nz.net.dnh.evetool.hibernate.evetool.AuthorisedCorporations;
import nz.net.dnh.evetool.hibernate.evetool.Corporation;
import nz.net.dnh.evetool.hibernate.evetool.Module;
import nz.net.dnh.evetool.hibernate.evetool.Permission;
import nz.net.dnh.evetool.hibernate.evetool.Role;
import nz.net.dnh.evetool.hibernate.evetool.Rule;
import nz.net.dnh.evetool.hibernate.evetool.User;
import nz.net.dnh.evetool.hibernate.evetool.UserHome;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.ejb.Ejb3Configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.mysql.jdbc.Driver;

/**
 * Application specifig bootstrap
 * 
 * @author danielh
 *
 */
public class Bootstrapper {	
	/**
	 * The Log4J logger
	 */
	public static final Logger appLogger = Logger.getLogger(Bootstrapper.class);
	
	/**
	 * The guice injector
	 */
	public static Injector inject;
	
	/**
	 * Perform the boostrapping
	 */
	public static void bootstrap() {
		PropertyConfigurator.configure("WEB-INF/classes/nz/net/dnh/evetool/Logging.properties");
		
		// Configure Hibernate for eveTool
		com.google.inject.Module evetool;
		{
			final Class<?>[] classes = new Class<?>[] {
					AuthorisedCorporations.class,
					Corporation.class,
					Module.class,
					Permission.class,
					Role.class,
					Rule.class,
					User.class,
					UserHome.class
				};
			
			final Properties properties = new Properties();
			properties.setProperty(Environment.DIALECT, MySQL5Dialect.class.getName());
			properties.setProperty(Environment.DRIVER, Driver.class.getName());
			properties.setProperty(Environment.URL, "jdbc:mysql://localhost/EVETool");
			properties.setProperty(Environment.USER, "dnh");
			properties.setProperty(Environment.PASS, "_dnh");
	
			final Configuration config = new Configuration();
			config.addProperties(properties);
			
			final Ejb3Configuration ejb = new Ejb3Configuration();
			ejb.addProperties(properties);
			
			for (Class<?> c : classes) {
				config.addAnnotatedClass(c);
				ejb.addAnnotatedClass(c);
			}
			
			final SessionFactory session = config.buildSessionFactory();
			final EntityManager manager = ejb.buildEntityManagerFactory().createEntityManager();
			
			evetool = new AbstractModule() {
				@Override
				protected void configure() {
					bind(EntityManager.class).toInstance(manager);
					bind(SessionFactory.class).toInstance(session);
				}
			};
		}
		
		// Configure Hibernate for eve
		com.google.inject.Module eve;
		{
			final Class<?>[] classes = new Class<?>[] {
					Item.class
				};
			
			final Properties properties = new Properties();
			properties.setProperty(Environment.DIALECT, MySQL5Dialect.class.getName());
			properties.setProperty(Environment.DRIVER, Driver.class.getName());
			properties.setProperty(Environment.URL, "jdbc:mysql://localhost/EVE");
			properties.setProperty(Environment.USER, "dnh");
			properties.setProperty(Environment.PASS, "_dnh");
	
			final Configuration config = new Configuration();
			config.addProperties(properties);
			
			final Ejb3Configuration ejb = new Ejb3Configuration();
			ejb.addProperties(properties);
			
			for (Class<?> c : classes) {
				config.addAnnotatedClass(c);
				ejb.addAnnotatedClass(c);
			}
			
//			final SessionFactory session = config.buildSessionFactory();
			final EntityManager manager = ejb.buildEntityManagerFactory().createEntityManager();
			
			eve = new AbstractModule() {
				@Override
				protected void configure() {
					bind(EntityManager.class)
						.annotatedWith(Names.named("eve"))
						.toInstance(manager);
				}
			};
		}
		
		// Configure Guice
		inject = Guice.createInjector(eve, evetool, new AbstractModule() {
			@Override
			protected void configure() {
				bind(IUserRepository.class).to(HibernateUserRepository.class);
				bind(ICorporationRepository.class).to(HibernateCorporationRepository.class);
				bindListener(Matchers.any(), new Log4JTypeListener());
			}
		});
		
		// Add the action helpers
		HelperBroker.ActionHelperBroker
			.addHelper(new HeadTitleFactory())
			.addHelper(new AuthenticatorFactory());
	}
	
	/**
	 * Remove the bootstrapping. This is only useful in unit tests
	 */
	public static void unbootstrap() {
		HelperBroker.ActionHelperBroker.clear();
		inject = null;
	}
}
