package ca.trovo;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


/**
 * Enables {@code JAX-RS} and starts all services for this application.
 */
@ApplicationPath("/api")
public class JaxRsActivator extends Application {

	/** The base endpoint URI value for all services in this application. */
	public static final String ENDPOINT_BASE = "/api";

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(JaxRsActivator.class.getName());


	/**
	 * Default constructor that executes during the application startup, before loading all services.
	 */
	public JaxRsActivator() {
		LOGGER.log(Level.INFO,
			  """

			 _   _      _ _
			| | | |    | | |
			| |_| | ___| | | ___
			|  _  |/ _ \\ | |/ _ \\
			| | | |  __/ | | (_) |
			\\_| |_/\\___|_|_|\\___/

				"""
		);
	}
}
