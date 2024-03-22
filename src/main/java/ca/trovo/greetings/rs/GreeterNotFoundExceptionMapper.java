package ca.trovo.greetings.rs;

import ca.trovo.greetings.greeter.GreeterNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


/**
 * The {@code JAX-RS} class to map {@link GreeterNotFoundException} to the correct {@code HTTP} response.
 */
@Provider
public class GreeterNotFoundExceptionMapper implements ExceptionMapper<GreeterNotFoundException> {

	@Override
	public Response toResponse(GreeterNotFoundException exception) {
		return switch (exception.getCause()) {
			case NullPointerException npe -> Response.status(Response.Status.BAD_REQUEST).entity(npe.getMessage()).build();
			default -> Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
		};
	}

}
