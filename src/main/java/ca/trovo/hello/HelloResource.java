package ca.trovo.hello;

import ca.trovo.JaxRsActivator;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


/**
 * Defines the {@code HTTP} resources for greetings.
 */
@Path("/hello")
public interface HelloResource {

	/** The base endpoint URI value */
	public static final String ENDPOINT_BASE = JaxRsActivator.ENDPOINT_BASE + "/hello";

	/** The value for {@link Path @Path} used by {@link #ping()}. */
	public static final String ENDPOINT_URI_PATH_PING_VALUE = "/ping";

	/**
	 * Helper constant to be used in {@link String#format(String, Object...)} to access {@link #ping()}.
	 * It is prefixed with {@link #ENDPOINT_BASE}.
	*/
	public static final String ENDPOINT_URI_PATH_PING_PATTERN = ENDPOINT_BASE + ENDPOINT_URI_PATH_PING_VALUE;

	/** The value for {@link Path @Path} used by {@link #sayHello(String)}. */
	public static final String ENDPOINT_URI_PATH_SAY_NAME_VALUE = "/say/{name}";
	/**
	 * Helper constant to be used in {@link String#format(String, Object...)} to access {@link #sayHello(String)}.
	 * It is prefixed with {@link #ENDPOINT_BASE}.
	*/
	public static final String ENDPOINT_URI_PATH_SAY_NAME_PATTERN = ENDPOINT_BASE + "/say/%s";




	/**
	 * A simple ping endpoint, which can be used for liveness check.
	 *
	 * @return A simple response, often {@code pong} as confirmation.
	 *
	 */
	@GET
	@Path(ENDPOINT_URI_PATH_PING_VALUE)
	public Response ping();


	/**
	 * A greetings endpoint, that repeats your name in the greeting.
	 *
	 * @param name
	 * 			The name to greet.
	 *
	 * @return The greetings.
	 *
	 */
	@GET
	@Path(ENDPOINT_URI_PATH_SAY_NAME_VALUE)
	public Response sayHello(@PathParam("name") String name);

}
