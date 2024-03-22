package ca.trovo.greetings.greeter;

import java.util.NavigableMap;

import ca.trovo.greetings.Greeter;


/**
 * A generic {@link Greeter} based on a threshold that uses {@link NavigableMap#floorEntry(Object)}
 * to find the closest greeting for a given threshold.
 *
 * @param <T>
 * 			The threshold type. Must comply with the {@link NavigableMap}'s key rules.
 *
 */
abstract class ThresholdGreeter<T> implements Greeter {

	/**
	 * The map where the key is the lower threshold and the value is the appropriated greeting.
	 * @see #getGreeting(T)
	 */
	protected final NavigableMap<T, String> greetings;

	/**
	 * Constructor with the threshold map to use
	 * @param greetings
	 */
	protected ThresholdGreeter(NavigableMap<T, String> greetings) {
		this.greetings = greetings;
	}


	/**
	 * Get closest greeting for a given threshold.
	 *
	 * @param threshold
	 * 			The threshold to get the greeting.
	 *
	 * @return The greeting that is the closest to the given threshold.
	 *
	 * @see NavigableMap#floorEntry(Object)
	 *
	 */
	protected final String getGreeting(T threshold) {
		String greeting = this.greetings.floorEntry(threshold).getValue();
		return greeting;
	}

}
