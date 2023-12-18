package ca.trovo.hello;

import org.junit.Assert;


/**
 * A helper class for tests with {@link Greeting}.
 */
public abstract class GreetingTestHelper {

	/** A private constructor to avoid initialization. All methods are static helpers that does not depend on state. */
	private GreetingTestHelper() {}


	/**
	 * Asserts that the {@link Greeting} is not null. If it is an {@link AssertionError} is thrown.
	 *
	 * @param greeting
	 * 			The greeting to assert.
	 *
	 * @see Assert#assertNotNull(String, Object)
	 *
	 */
	public static void assertNotNull(Greeting greeting) {
		Assert.assertNotNull("Greeting is null", greeting);
		Assert.assertNotNull("The greeter of the greeting is null", greeting.greeter());
		Assert.assertNotNull("The greetee of the greeting is null", greeting.greetee());
		Assert.assertNotNull("The value of the greeting is null", greeting.greeting());
	}

}
