package ca.trovo.utils;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.core.UriBuilder;


/**
 * Utility class to deal with {@code HTTP}.
 */
public abstract class HttpUtils {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(HttpUtils.class.getName());

	/** A private constructor to avoid initialization. All methods are static helpers that does not depend on state. */
	private HttpUtils() {}



	/**
	 * Encodes a parameter value to be used in an endpoint call which is not part of the query string.
	 *
	 * <p>
	 * This basically boils down to the fact that the {@code URL} needs to be encoded as:
	 * <ul>
	 * 	   <li>using {@code %20} instead of spaces <b>before</b> the {@code ?}</li>
	 *     <li>using {@code +} instead of space <b>after</b> the {@code ?} </li>
	 * </ul>
	 * </p>
	 *
	 * The default encoding used by Java's {@link URLEncoder#encode(String, Charset)}
	 * uses {@code HTML}, which always use {@code +}, so here we replace it with {@code %20}
	 * and can use the returned value as part of the endpoint URL parameter.
	 *
	 * <p>
	 * Examples of usage:
	 * <pre>{@code
	 * // The URL we want to access is http://domain:port/path/to/get/{something}
	 * // {something} needs to be encoded, but blank spaces need to be replaced with %20
	 * String something = ; // i.e. user input
	 * String path = "http://domain:port/path/to/get/" + encodeParameterForURL(something);
	 * }</pre>
	 * </p>
	 *
	 * @param parameter
	 * 			The value to encode.
	 *
	 * @return The encoded value to be used as part of URLs.
	 *
	 */
	public static String encodeParameterForURL(String parameter) {
		String encoded = URLEncoder.encode(parameter, StandardCharsets.UTF_8);
		encoded = encoded.replace("+", "%20");

		LOGGER.log(Level.FINE, "Encoded parameter {0} as {1}", new Object[] { parameter, encoded });
		return encoded;
	}


	/**
	 * Helper method that creates a full URI to access an endpoint.
	 *
	 * @param contextRoot
	 * 			The URI for the endpoint's {@code Context Root}.
	 * 			For example, {@code http://localhost:8080/hello-jakartaee}.
	 * @param path
	 * 			The endpoint's {@code "base path"} that the service uses.
	 * 			For example, {@code /api/ping} or {@code /api/hello/john}.
	 *
	 * @return The full URI to access an endpoint.
	 *
	 * @throws NullPointerException
	 * 				When any of the non-optional parameters are {@code null}.
	 *
	 * @see #getEndpointURI(URI, String, Map)
	 *
	 */
	public static URI getEndpointURI(URI contextRoot, String path) {
		return getEndpointURI(contextRoot, path, null);
	}

	/**
	 * Helper method that creates a full URI to access an endpoint.
	 *
	 * @param contextRoot
	 * 			The URI for the endpoint's {@code Context Root}.
	 * 			For example, {@code http://localhost:8080/hello-jakartaee}.
	 * @param path
	 * 			The endpoint's {@code "base path"} that the service uses.
	 * 			For example, {@code /api/ping} or {@code /api/hello/john}.
	 * @param variables [optional]
	 * 			Any variables to send as parameter.
	 *
	 * @return The full URI to access an endpoint.
	 *
	 * @throws NullPointerException
	 * 				When any of the non-optional parameters are {@code null}.
	 *
	 * @see UriBuilder
	 * @see UriBuilder#buildFromMap(Map)
	 *
	 */
	public static URI getEndpointURI(URI contextRoot, String path, Map<String, ?> variables) {
		Objects.requireNonNull(contextRoot, String.format("Cannot get the endpoint for %s because the context root is null.", path));
		Objects.requireNonNull(path, "Cannot get an endpoint for a null path");

		LOGGER.log(Level.FINE, "Building endpoint URI for context root {1} and path {2} {3}",
				new Object[] { contextRoot.toASCIIString(), path, variables == null || variables.isEmpty() ? "without variables." : "with " + variables.size() + " variables." });

		UriBuilder builder = UriBuilder
				.fromUri(contextRoot)
				.path(path);

		URI uri = variables == null ? builder.build() : builder.buildFromMap(variables);
		LOGGER.log(Level.FINE, "Built endpoint URI: {0}", uri.toASCIIString());

		return uri;
	}
}
