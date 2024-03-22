package ca.trovo.greetings.rs;

import java.util.logging.Level;
import java.util.logging.Logger;

import ca.trovo.greetings.Greeter;
import ca.trovo.greetings.GreeterFactory;
import ca.trovo.greetings.Greeting;
import ca.trovo.greetings.greeter.GreeterNotFoundException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;


/**
 * Implementation of a service for greetings using {@code Jakarta EE 10}.
 * <p>
 * The {@link Path} annotation must be repeated here for {@code CDI} (Weld) to work,
 * without it, {@link #factory} was always {@code null} and {@code JAX-RS} (RestEasy) would not inject it.
 * </p>
 */
@Path("/greetings")
public class GreetingService implements GreetingResource {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(GreetingService.class.getName());

	@Inject
	private GreeterFactory factory;


	@Override
	public Greeting greet(String greeterName, String greetee) throws GreeterNotFoundException {
		LOGGER.log(Level.FINER, "Received a request for {0} to greet {1}", new Object[] { greeterName, greetee });
		Greeter greeter = this.factory.get(greeterName);
		Greeting greeting = greeter.greet(greetee);

		LOGGER.log(Level.FINER, "Replying {0}", greeting);
		return greeting;
	}

}
