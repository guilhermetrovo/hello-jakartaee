package ca.trovo.hello;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.trovo.BaseIntegrationTestSuiteHttpClient;
import ca.trovo.utils.HttpUtils;
import jakarta.ws.rs.core.Response;


/**
 * Integration test class for {@link HelloResource} that uses a {@code JAX-RS} client
 * to perform calls to a {@code test.war} that runs inside {@code Arquillian} with the
 * real application code, simulating what would happen when the application is in production.
 */
@RunWith(Arquillian.class)
public class HelloResouceIT extends BaseIntegrationTestSuiteHttpClient {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(HelloResouceIT.class.getName());


	/**
	 * Validates that the {@link HelloResource#ping()} is working and replying {@code pong}.
	 */
	@Test
	public void shouldPing() throws URISyntaxException {
		URI endpoint = HttpUtils.getEndpointURI(this.contextRoot.toURI(), HelloResource.ENDPOINT_URI_PATH_PING_PATTERN);
		Response response = this.client.target(endpoint).request().get();
		Assert.assertEquals(200, response.getStatus());

		String entity = response.readEntity(String.class);
		Assert.assertEquals("pong", entity);
	}

	/**
	 * Validates that the {@link HelloResource#sayHello(String)} is working for a simple name.
	 */
	@Test
	public void shouldSayHelloBackForSimpleName() throws URISyntaxException {
		shouldSayHelloFor("John");
	}

	/**
	 * Validates that the {@link HelloResource#sayHello(String)} is working for a simple name with blank spaces.
	 */
	@Test
	public void shouldSayHelloBackWithSpaces() throws URISyntaxException {
		shouldSayHelloFor("John Smith");
	}

	/**
	 * Validates that the {@link HelloResource#sayHello(String)} is working for all numbers, with dots and commas.
	 */
	@Test
	public void shouldSayHelloBackWithNumbers() throws URISyntaxException {
		shouldSayHelloFor("1234567890");
		shouldSayHelloFor("1.234.567.890");
		shouldSayHelloFor("12.345.678,90");
	}

	/**
	 * Validates that the {@link HelloResource#sayHello(String)} is working for a name with special characters.
	 */
	@Test
	public void shouldSayHelloBackWithSpecialChars() throws URISyntaxException {
		shouldSayHelloFor("(abc)");
		shouldSayHelloFor("[abc]");
		shouldSayHelloFor("{abc}");
		shouldSayHelloFor("<abc>");
		shouldSayHelloFor("!abc!");
		shouldSayHelloFor("@abc@");
		shouldSayHelloFor("#abc#");
		shouldSayHelloFor("$abc$");
		shouldSayHelloFor("%abc%");
		shouldSayHelloFor("^abc^");
		shouldSayHelloFor("&abc&");
		shouldSayHelloFor("*abc*");
		shouldSayHelloFor("_abc_");
		shouldSayHelloFor("-abc-");
		shouldSayHelloFor("=abc=");
		shouldSayHelloFor("+abc+");
		shouldSayHelloFor("~abc~");
		shouldSayHelloFor("`abc`");
		shouldSayHelloFor("\"abc\"");
		shouldSayHelloFor("'abc'");
		shouldSayHelloFor("\\abc\\");
		shouldSayHelloFor("/abc/");
	}

	/**
	 * Helper method to validate {@link HelloResource#sayHello(String)}
	 * and assert that the response was <code>"Hello {myName}"</code>.
	 *
	 * @param myName
	 * 			The parameter to use when calling {@link HelloResource#sayHello(String)}
	 *
	 * @see #shouldSayHelloHelper(String)
	 *
	 */
	private void shouldSayHelloFor(String myName) throws URISyntaxException {
		String response = shouldSayHelloHelper(myName);
		Assert.assertEquals("Hello " + myName, response);
	}

	/**
	 * Helper method that will call {@link HelloResource#sayHello(String)}
	 * and assert that the response was successful, but not its contents.
	 *
	 * @param myName
	 * 			The parameter to use when calling {@link HelloResource#sayHello(String)}.
	 * 			The name is part of the URL (and not query parameter), so it will be encoded as such.
	 *
	 * @return The {@code HTTP Response} entity value.
	 *
	 * @see HttpUtils#encodeParameterForURL(String)
	 * @see HttpUtils#getEndpointURI(URI, String)
	 *
	 */
	private String shouldSayHelloHelper(String myName) throws URISyntaxException {
		// Build and perform the request
		myName = HttpUtils.encodeParameterForURL(myName);
		String endpoint = HelloResource.ENDPOINT_URI_PATH_SAY_NAME_PATTERN.formatted(myName);
		URI uri = HttpUtils.getEndpointURI(this.contextRoot.toURI(), endpoint);

		LOGGER.log(Level.FINE, "Requesting {0}", uri.toASCIIString());
		Response response = this.client.target(uri).request().get();

		// Make sure the request worked
		Assert.assertEquals(200, response.getStatus());

		// Read the response
		String entity = response.readEntity(String.class);
		Assert.assertNotNull(entity);

		return entity;
	}
}
