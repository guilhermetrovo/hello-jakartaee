package ca.trovo.hello.greeter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import ca.trovo.hello.Greeter;
import ca.trovo.hello.Greeting;
import ca.trovo.hello.GreetingTestHelper;


/**
 * Unit Test class for {@link ClockGreeter}.
 */
public class ClockGreeterTest {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(ClockGreeterTest.class.getName());


	/**
	 * Tests that {@link ClockGreeter#greet(String)} replies {@code Good morning} from <em>5am to 11am</em> (inclusive).
	 */
	@Test
	public void shouldReplyGoodMorning() {
		List<LocalDateTime> morningHours = List.of(
			LocalDate.now().atTime(5, 0),
			LocalDate.now().atTime(6, 0),
			LocalDate.now().atTime(7, 0),
			LocalDate.now().atTime(8, 0),
			LocalDate.now().atTime(9, 0),
			LocalDate.now().atTime(10, 0),
			LocalDate.now().atTime(11, 0)
		);

		String expectedGreeting = "Good morning";
		shouldReply(morningHours, expectedGreeting);
	}


	/**
	 * Tests that {@link ClockGreeter#greet(String)} replies {@code Good afternoon} from <em>12pm to 5pm</em> (inclusive).
	 */
	@Test
	public void shouldReplyGoodAfternoon() {
		List<LocalDateTime> afternoonHours = List.of(
			LocalDate.now().atTime(12, 0),
			LocalDate.now().atTime(13, 0),
			LocalDate.now().atTime(14, 0),
			LocalDate.now().atTime(15, 0),
			LocalDate.now().atTime(16, 0),
			LocalDate.now().atTime(17, 0)
		);

		String expectedGreeting = "Good afternoon";
		shouldReply(afternoonHours, expectedGreeting);
	}


	/**
	 * Tests that {@link ClockGreeter#greet(String)} replies {@code Good evening} from <em>6pm to 8pm</em> (inclusive).
	 */
	@Test
	public void shouldReplyGoodEvening() {
		List<LocalDateTime> eveningHours = List.of(
			LocalDate.now().atTime(18, 0),
			LocalDate.now().atTime(19, 0),
			LocalDate.now().atTime(20, 0)
		);

		String expectedGreeting = "Good evening";
		shouldReply(eveningHours, expectedGreeting);
	}


	/**
	 * Tests that {@link ClockGreeter#greet(String)} replies {@code Good night} from <em>21pm to 4am</em> (inclusive).
	 */
	@Test
	public void shouldReplyGoodNight() {
		List<LocalDateTime> nightHours = List.of(
			LocalDate.now().atTime(0, 0),
			LocalDate.now().atTime(1, 0),
			LocalDate.now().atTime(2, 0),
			LocalDate.now().atTime(3, 0),
			LocalDate.now().atTime(4, 0),
			LocalDate.now().atTime(21, 0),
			LocalDate.now().atTime(22, 0),
			LocalDate.now().atTime(23, 0)
		);

		String expectedGreeting = "Good night";
		shouldReply(nightHours, expectedGreeting);
	}


	/**
	 * Helper method to check what the reply should be at the given times.
	 *
	 * @param times
	 * 			The time of the day to greet.
	 * @param expectedGreeting
	 * 			The expected greeting.
	 *
	 */
	private void shouldReply(Collection<LocalDateTime> times, String expectedGreeting) {
		String greetee = "John";

		for (LocalDateTime hour : times) {
			LOGGER.log(Level.FINE, "Creating a ClockGreeter at " + hour);
			Greeter greeter = new ClockGreeter(hour);

			Greeting greeting = greeter.greet(greetee);
			GreetingTestHelper.assertNotNull(greeting);

			Assert.assertTrue("The greeting is not " + expectedGreeting, greeting.greeting().contains(expectedGreeting));
			Assert.assertTrue(greetee + " is not in the greeting value", greeting.greeting().contains(greetee));
		}
	}
}
