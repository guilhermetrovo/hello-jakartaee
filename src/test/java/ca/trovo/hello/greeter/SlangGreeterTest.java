package ca.trovo.hello.greeter;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ca.trovo.hello.Greeter;
import ca.trovo.hello.Greeting;
import ca.trovo.hello.GreetingTestHelper;

/**
 * Unit Test class for {@link SlangGreeter}.
 */
public class SlangGreeterTest {

	/**
	 * Tests that {@link SlangGreeter#greet(String)} returns a valid {@link Greeting},
	 * and that the {@code greeting} contains the {@code greetee} value in it.
	 */
	@Test
	public void shouldHaveNameInGreeting() {
		Greeter greeter = new SlangGreeter();
		String greetee = "John";

		Greeting greeting = greeter.greet(greetee);

		GreetingTestHelper.assertNotNull(greeting);
		Assert.assertEquals("Greeter is different", greeting.greeter(), greeter);
		Assert.assertEquals("Greetee is different", greeting.greetee(), greetee);
		Assert.assertTrue(greetee + " is not in the greeting value", greeting.greeting().contains(greetee));
	}

	/**
	 * Tests that {@link SlangGreeter#greet(String)} should return a minimum number of different greetings.
	 */
	@Test
	public void shouldMeetGreetingVarietyMinimum() {
		int minimumUniqueGreetings = 3;
		int maxNumberOfGreetings = 50;
		Set<Greeting> uniqueGreetings = HashSet.newHashSet(maxNumberOfGreetings);

		Greeter greeter = new SlangGreeter();
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
