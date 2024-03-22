package ca.trovo.greetings.greeter;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ca.trovo.greetings.Greeter;
import ca.trovo.greetings.Greeting;
import ca.trovo.greetings.GreetingTestHelper;

/**
 * Unit Test class for {@link SimpleGreeter}.
 */
public class SimpleGreeterTest {

	/**
	 * Tests that {@link SimpleGreeter#greet(String)} returns a valid {@link Greeting},
	 * and that the {@code greeting} contains the {@code greetee} value in it.
	 */
	@Test
	public void shouldHaveNameInGreeting() {
		Greeter greeter = new SimpleGreeter();
		String greetee = "John";

		Greeting greeting = greeter.greet(greetee);

		GreetingTestHelper.assertNotNull(greeting);
		Assert.assertEquals("Greeter is different", greeting.greeter(), greeter);
		Assert.assertEquals("Greetee is different", greeting.greetee(), greetee);
		Assert.assertTrue(greetee + " is not in the greeting value", greeting.greeting().contains(greetee));
	}

	/**
	 * Tests that {@link SimpleGreeter#greet(String)} should return a minimum number of different greetings.
	 */
	@Test
	public void shouldMeetGreetingVarietyMinimum() {
		int minimumUniqueGreetings = 3;
		int maxNumberOfGreetings = 50;
		Set<Greeting> uniqueGreetings = HashSet.newHashSet(maxNumberOfGreetings);

		Greeter greeter = new SimpleGreeter();
		String greetee = "John";

		for (int i=0; i < maxNumberOfGreetings; i++) {
			if (uniqueGreetings.size() >= minimumUniqueGreetings) {
				break;
			}

			Greeting greeting = greeter.greet(greetee);
			GreetingTestHelper.assertNotNull(greeting);

			uniqueGreetings.add(greeting);
		}

		Assert.assertFalse("No greetings were done. Huh?", uniqueGreetings.isEmpty());
		Assert.assertTrue("Only " + uniqueGreetings.size() + " unique greetings. Minium: " + minimumUniqueGreetings, uniqueGreetings.size() >= minimumUniqueGreetings);
	}
}
