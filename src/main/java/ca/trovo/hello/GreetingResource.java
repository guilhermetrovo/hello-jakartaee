package ca.trovo.hello;

import ca.trovo.hello.greeter.GreeterNotFoundException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


/**
 * Defines the {@code HTTP} resources for greetings.
 */
@Path("/greetings")
public interface GreetingResource {

	/** The base endpoint URI value */
	public static final String ENDPOINT_BASE = JaxRsActivator.ENDPOINT_BASE + "/greetings";


	/** The value for {@link Path @Path} used by {@link #greet(String, String)}. */
	public static final String ENDPOINT_URI_PATH_GREET_VALUE = "{greeterName}/greet/{greetee}";
	/**
	 * Helper constant to be used in {@link String#format(String, Object...)} to access {@link #greet(String, String)}.
	 * It is prefixed with {@link #ENDPOINT_BASE}.
	*/
	public static final String ENDPOINT_URI_PATH_GREET_PATTERN = ENDPOINT_BASE + "/%s/greet/%s";




	/**
	 * A greetings endpoint, that repeats your name in the greeting.
	 *
	 * @param greeterName
	 * 			The greeter to use.
	 * @param greetee
	 * 			The name to greet.
	 *
	 * @return The greetings.
	 *
	 * @throws GreeterNotFoundException
	 * 				When the {@link Greeter} is not found via the given {@code greeterName}.
	 *
	 */
	@GET
	@Path(ENDPOINT_URI_PATH_GREET_VALUE)
	@Produces(MediaType.APPLICATION_JSON)
	public Greeting greet(@PathParam("greeterName") String greeterName, @PathParam("greetee") String greetee) throws GreeterNotFoundException;

}
