package ca.trovo.hello;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;


/**
 * Implementation of a service for greetings using {@code Jakarta EE 10}.
 */
public class HelloService implements HelloResource {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(HelloService.class.getName());


	@Context
	private HttpServletRequest httpServletRequest;


	@Override
	public Response ping() {
		LOGGER.log(Level.FINE, "Received a request for ping, replying pong");
		return Response.ok().entity("pong").build();
	}


	@Override
	public Response sayHello(@PathParam("name") String name) {
		LOGGER.log(Level.FINE, "Received a name request for {0}", name);
		return Response.ok().entity("Hello " + name).build();
	}
}
