package ca.trovo.greetings.greeter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

import ca.trovo.greetings.Greeter;
import ca.trovo.greetings.Greeting;


/**
 * A {@link Greeter} based on the current time of the day.
 */
public final class ClockGreeter extends ThresholdGreeter<Integer> {

	/**
	 * The default map to use for this greeter.
	 */
	private static final NavigableMap<Integer, String> GREETINGS;
	static {
		NavigableMap<Integer, String> greetings = new TreeMap<>();
		greetings.put(0, "Good night %s.");
		greetings.put(5, "Good morning %s.");
		greetings.put(12, "Good afternoon %s.");
		greetings.put(18, "Good evening %s.");
		greetings.put(21, "Good night %s.");
		GREETINGS = Collections.unmodifiableNavigableMap(greetings);
	}


	/** The time to use in this clock. */
	protected final LocalDateTime time;


	/**
	 * Default constructor that defaults to the current time.
	 */
	public ClockGreeter() {
		this(LocalDateTime.now());
	}

	/**
	 * Constructor with the time to use.
	 *
	 * @param time
	 * 			The time to use in this clock.
	 *
	 */
	public ClockGreeter(LocalDateTime time) {
		super(GREETINGS);
		this.time = time;
	}


	/**
	 * Helper method to get a raw greeting based on the current time, the greeting needs to be formatted.
	 * @return A time-based greeting from the pre-defined list.
	 */
	private String getGreeting() {
		int hour = this.time.getHour();
		return getGreeting(hour);
	}


	@Override
	public Greeting greet(String greetee) {
		String greeting = getGreeting().formatted(greetee);
		return new Greeting(this, greetee, greeting);
	}

}
