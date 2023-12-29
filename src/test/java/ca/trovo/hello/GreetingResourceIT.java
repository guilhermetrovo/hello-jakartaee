package ca.trovo.hello;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.trovo.utils.HttpUtils;
import ca.trovo.utils.JacksonUtils;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Integration test class for {@link GreetingResource} that uses a {@code JAX-RS} client
 * to perform calls to a {@code test.war} that runs inside {@code Arquillian} with the
 * real application code, simulating what would happen when the application is in production.
 */
@RunWith(Arquillian.class)
public class GreetingResourceIT extends BaseIntegrationTestSuiteHttpClient {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(GreetingResourceIT.class.getName());


	@Test
	public void whenUsingSimpleGreeter_shouldGreetForSimpleName(String greeter) throws URISyntaxException {
		shouldGreetForSimpleName("simple");
	}

	@Test
	public void whenUsingSimpleGreeter_shouldGreetWithSpaces(String greeter) throws URISyntaxException {
		shouldGreetWithSpaces("simple");
	}

	@Test
	public void whenUsingSimpleGreeter_shouldGreetWithNumbers(String greeter) throws URISyntaxException {
		shouldGreetWithNumbers("simple");
	}

	@Test
	public void whenUsingSimpleGreeter_shouldGreetWithSpecialChars(String greeter) throws URISyntaxException {
		shouldGreetWithSpecialChars("simple");
	}


	private void shouldGreetForSimpleName(String greeter) throws URISyntaxException {
		shouldGreet(greeter, "John");
	}

	private void shouldGreetWithSpaces(String greeter) throws URISyntaxException {
		shouldGreet(greeter, "John Smith");
	}

	private void shouldGreetWithNumbers(String greeter) throws URISyntaxException {
		shouldGreet(greeter, "1234567890");
		shouldGreet(greeter, "1.234.567.890");
		shouldGreet(greeter, "12.345.678,90");
	}

	private void shouldGreetWithSpecialChars(String greeter) throws URISyntaxException {
		shouldGreet(greeter, "1234567890");
		shouldGreet(greeter, "(abc)");
		shouldGreet(greeter, "[abc]");
		shouldGreet(greeter, "{abc}");
		shouldGreet(greeter, "<abc>");
		shouldGreet(greeter, "!abc!");
		shouldGreet(greeter, "@abc@");
		shouldGreet(greeter, "#abc#");
		shouldGreet(greeter, "$abc$");
		shouldGreet(greeter, "%abc%");
		shouldGreet(greeter, "^abc^");
		shouldGreet(greeter, "&abc&");
		shouldGreet(greeter, "*abc*");
		shouldGreet(greeter, "_abc_");
		shouldGreet(greeter, "-abc-");
		shouldGreet(greeter, "=abc=");
		shouldGreet(greeter, "+abc+");
		shouldGreet(greeter, "~abc~");
		shouldGreet(greeter, "`abc`");
		shouldGreet(greeter, "\"abc\"");
		shouldGreet(greeter, "'abc'");
		shouldGreet(greeter, "\\abc\\");
		shouldGreet(greeter, "/abc/");
	}

	/**
	 * Helper method to validate {@link GreetingResource#greet(String, String)}
	 * and assert that the response was valid.
	 *
	 * @param greeter
	 * 			The parameter to use when calling {@link GreetingResource#greet(String, String)}.
	 * 			This is part of the URL (and not query parameter), so it will be encoded as such.
	 * @param greetee
	 * 			The parameter to use when calling {@link GreetingResource#greet(String, String)}.
	 * 			This is part of the URL (and not query parameter), so it will be encoded as such.
	 *
	 * @see #shouldGreetHelper(String, String)
	 *
	 */
	private void shouldGreet(String greeter, String greetee) throws URISyntaxException {
		Greeting greeting = shouldGreetHelper(greeter, greetee);
		String response = greeting.greeting();
		Assert.assertTrue("The response is invalid. Response: " + response, response.contains(greetee));
	}

	/**
	 * Helper method that will call {@link GreetingResource#greet(String, String)}
	 * and assert that the response was successful, but not its contents.
	 *
	 * @param greeter
	 * 			The parameter to use when calling {@link GreetingResource#greet(String, String)}.
	 * 			This is part of the URL (and not query parameter), so it will be encoded as such.
	 * @param greetee
	 * 			The parameter to use when calling {@link GreetingResource#greet(String, String)}.
	 * 			This is part of the URL (and not query parameter), so it will be encoded as such.
	 *
	 * @return The {@code HTTP Response}'s entity value.
	 *
	 * @see HttpUtils#encodeParameterForURL(String)
	 * @see HttpUtils#getEndpointURI(URI, String)
	 *
	 */
	private Greeting shouldGreetHelper(String greeter, String greetee) throws URISyntaxException {
		// Build and perform the request
		greeter = HttpUtils.encodeParameterForURL(greeter);
		greetee = HttpUtils.encodeParameterForURL(greetee);
		String endpoint = GreetingResource.ENDPOINT_URI_PATH_GREET_PATTERN.formatted(greeter, greetee);
		URI uri = HttpUtils.getEndpointURI(this.contextRoot.toURI(), endpoint);

		LOGGER.log(Level.FINE, "Requesting {0}", uri.toASCIIString());
		Response response = this.client.target(uri).request(MediaType.APPLICATION_JSON).get();

		// Make sure the request worked
		Assert.assertEquals(200, response.getStatus());

		// Read the response
		String greetingJson = response.readEntity(String.class);
		LOGGER.log(Level.FINE, "Response {0}", greetingJson);

		// Transform the response
		Greeting greeting = JacksonUtils.getInstance().deserialize(greetingJson, Greeting.class);
		GreetingTestHelper.assertNotNull(greeting);

		return greeting;
	}

}
