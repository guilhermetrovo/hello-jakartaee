package ca.trovo.hello.greeter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import ca.trovo.hello.Greeter;


/**
 * Unit Test class for {@link GreeterFactory}.
 */
public class GreeterFactoryTest {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(GreeterFactoryTest.class.getName());


	/** The factory instance to use when testing. */
	private final GreeterFactory factory;

	/**
	 * Constructor of this class that initializes all the dependencies.
	 */
	public GreeterFactoryTest() {
		this.factory = new GreeterFactory();
	}


	/**
	 * Validates that the greeters are created successfully.
	 * @see #shouldGetGreetersByName()
	 */
	@Test
	public void shouldGetGreetersByName() {
		GreeterFactoryTestHelper.VALID_GREETERS.forEach(this::shouldGetGreetersByName);
	}

	/**
	 * Helper method that validates {@link GreeterFactory#get(String)} returns the expected {@link Greeter}.
	 *
	 * @param name
	 * 			The name of the greeter to validate.
	 * @param clazz
	 * 			The expected class of the greeter.
	 *
	 */
	private void shouldGetGreetersByName(String name, Class<? extends Greeter> clazz) {
		LOGGER.log(Level.FINE, "Getting greeter from name {0}", name);
		Greeter greeter = this.factory.get(name);

		Assert.assertNotNull("The greeter is null", greeter);
		Assert.assertEquals(
			"The greeter with name %s is of type %s instead of expected type %s".formatted(name, greeter.getClass().getCanonicalName(), clazz.getCanonicalName()),
			clazz, greeter.getClass()
		);
	}


	/**
	 * Validates that {@link GreeterFactory#get(String)} throws exception when greeter is not found by its name.
	 */
	@Test
	public void shouldNotGetGreeterByName() {
		List<String> invalidGreeterNames = List.of(
			// the class names
			"SimpleGreeter",
			"SlangGreeter",
			"ClockGreeter",

			// the names with different casing
			"Simple",
			"Slang",
			"Clock",
			"SIMPLE",
			"SLANG",
			"CLOCK",

			// wrong
			"wrong",
			"mom"
		);

		invalidGreeterNames.forEach(name -> {
			LOGGER.log(Level.FINE, "Getting greeter from name {0}", name);
			Assert.assertThrows(GreeterNotFoundException.class, () -> this.factory.get(name));
		});
	}
}
