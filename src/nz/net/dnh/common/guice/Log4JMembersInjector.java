package nz.net.dnh.common.guice;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.google.inject.MembersInjector;

/**
 * Injects a Log4J Logger instance
 * 
 * @author danielh
 *
 * @param <T>	The type of the class we are injecting into
 */
class Log4JMembersInjector<T> implements MembersInjector<T> {
	/**
	 * The field to inject
	 */
	private final Field field;
	
	/**
	 * The logger instance
	 */
	private final Logger logger;

	/**
	 * Setup the injection
	 * 
	 * @param field
	 */
	Log4JMembersInjector(Field field) {
		this.field = field;
		this.logger = Logger.getLogger(field.getDeclaringClass());
		field.setAccessible(true);
	}
	
	@Override
	public void injectMembers(T t) {
		try {
			field.set(t, logger);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
