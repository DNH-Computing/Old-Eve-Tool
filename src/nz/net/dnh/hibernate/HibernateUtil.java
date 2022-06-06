package nz.net.dnh.hibernate;

import java.sql.Driver;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.ejb.Ejb3Configuration;

public class HibernateUtil {
	public static Configuration buildSessionConfiguration(Class<?> dialect,
			Class<? extends Driver> driver, String url, String username,
			String password) {
		final Configuration config = new Configuration();

		final Properties properties = new Properties();
		properties.setProperty(Environment.DIALECT, dialect.getName());
		properties.setProperty(Environment.DRIVER, driver.getName());
		properties.setProperty(Environment.URL, url);
		properties.setProperty(Environment.USER, username);
		properties.setProperty(Environment.PASS, password);

		config.addProperties(properties);
		
		return config;
	}

	public static Configuration addAnnotatedClasses(final Configuration config,
			final Class<?>... classes) {
		for (Class<?> clazz : classes) {
			config.addAnnotatedClass(clazz);
		}
		return config;
	}
	
	public static Ejb3Configuration buildEjbConfiguration(Class<?> dialect,
			Class<? extends Driver> driver, String url, String username,
			String password) {
		final Ejb3Configuration config = new Ejb3Configuration();

		final Properties properties = new Properties();
		properties.setProperty(Environment.DIALECT, dialect.getName());
		properties.setProperty(Environment.DRIVER, driver.getName());
		properties.setProperty(Environment.URL, url);
		properties.setProperty(Environment.USER, username);
		properties.setProperty(Environment.PASS, password);

		config.addProperties(properties);
		
		return config;
	}

	public static Config buildSessionFactory(Class<?> dialect,
			Class<? extends Driver> driver, String url, String username,
			String password, final Class<?>... classes) {
		final Config cfg = new Config();
		final Configuration config = buildSessionConfiguration(dialect, driver, url, username, password);
		addAnnotatedClasses(config, classes);
		cfg.SessionFactory = config.buildSessionFactory();;
		
		final Ejb3Configuration ejb = buildEjbConfiguration(dialect, driver, url, username, password);
		for (Class<?> c : classes)
			ejb.addAnnotatedClass(c);
		cfg.EntityManagerFactory = ejb.buildEntityManagerFactory();
		
		return cfg;
	}
	
	public static class Config {
		private SessionFactory SessionFactory;
		private EntityManagerFactory EntityManagerFactory;
		
		public SessionFactory getSessionFactory() {
			return SessionFactory;
		}

		public EntityManagerFactory getEntityManagerFactory() {
			return EntityManagerFactory;
		}
	}
}