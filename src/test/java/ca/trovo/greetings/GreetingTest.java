package ca.trovo.greetings;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import ca.trovo.greetings.greeter.GreeterFactoryTestHelper;
import ca.trovo.utils.JacksonUtils;


/**
 * A "unit" test class for {@link Greeting}.
 * <p>
 * This validates the {@link Greeting} with other parts of the system, like {@code JSON} encode.
 *
 */
public class GreetingTest {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(GreetingTest.class.getName());


	/** The factory instance to use when testing. */
	private final GreeterFactory factory;

	public GreetingTest() {
		this.factory = new GreeterFactory();
	}



	@Test
	public void shouldSerializeAndDeserializeAllKnownGreeters() {
		GreeterFactoryTestHelper.VALID_GREETER_NAMES.forEach(this::serializeAndDeserializeByGreeter);
	}

	/**
	 * Helper method that serializes and deserializes a {@link Greeting} using the given {@link Greeter}'s name.
	 *
	 * @param greeterName
	 * 			The {@link Greeter}'s name to use.
	 *
	 * @see JacksonUtils#serialize(Object)
	 * @see JacksonUtils#deserialize(String, Class)
	 *
	 */
	private void serializeAndDeserializeByGreeter(String greeterName) {
		LOGGER.log(Level.FINE, "Getting greeter for {0}", greeterName);
		Greeter greeter = this.factory.get(greeterName);

		LOGGER.log(Level.FINE, "Creating greeting...");
		String greetee = "John";
		Greeting greeting = greeter.greet(greetee);
		GreetingTestHelper.assertNotNull(greeting);

		LOGGER.log(Level.FINE, "Serializing {0}", greeterName);String json = JacksonUtils.getInstance().serialize(greeting);
		Assert.assertNotNull("The serialized json is null.", json);

		LOGGER.log(Level.FINE, "Deserializing {0}", greeterName);
		Greeting deserialized = JacksonUtils.getInstance().deserialize(json, Greeting.class);
		Assert.assertNotNull("The deserialized object is null.", deserialized);
	}

}
