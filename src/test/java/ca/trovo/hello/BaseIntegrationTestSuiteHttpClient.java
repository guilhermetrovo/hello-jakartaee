package ca.trovo.hello;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;


/**
 * Base class for all integration tests that need to use a {@link Client JAX-RS HTTP Client}.
 * This class:
 * <ul>
 *     <li>Provides the injected {@link #contextRoot} from {@code Arquillian}.</li>
 *     <li>Builds and terminates a new client before/after each {@code jUnit} test.</li>
 * </ul>
 *
 * @see #contextRoot
 * @see #client
 *
 */
abstract class BaseIntegrationTestSuiteHttpClient extends BaseIntegrationTestSuite {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(BaseIntegrationTestSuiteHttpClient.class.getName());


	/**
	 * The application's context root from the arquillian deployment that can be used
	 * by tests that need to perform an {@code HTTP} call.
	 *
	 * For example {@code http://localhost:8080/hello-jakartaee}
	 */
	@ArquillianResource
	protected URL contextRoot;

	/**
	 * A {@link Client JAX-RS HTTP Client} which life-cycle is controlled by this class.
	 * It is initialized and destroyed before/after each {@code jUnit} test.
	 */
	protected Client client;



	/**
	 * Method that is executed before each {@code jUnit} test runs.
	 */
	@Before
	public void beforeEach() {
		LOGGER.log(Level.FINER, "Building HTTP client.");
		this.client = ClientBuilder.newBuilder().build();
	}

	@After
	public void afterEach() {
		if (this.client != null) {
			LOGGER.log(Level.FINER, "Closing HTTP client.");
			this.client.close();
		} else {
			LOGGER.log(Level.FINER, "HTTP client was null.");
		}
	}

}
