package ca.trovo.greetings.greeter;

import java.util.Collection;
import java.util.Map;

import ca.trovo.greetings.Greeter;
import ca.trovo.greetings.GreeterFactory;


/**
 * A helper class for tests with {@link GreeterFactory}.
 */
public class GreeterFactoryTestHelper {

	/**
	 * A list of all known and valid greeters implementation classes and their names.
	 * <p>
	 * Entries here are valid input and output type of the {@link GreeterFactory#get(String)}.
	 */
	public static final Map<String, Class<? extends Greeter>> VALID_GREETERS = Map.ofEntries(
		Map.entry("simple", SimpleGreeter.class),
		Map.entry("slang", SlangGreeter.class),
		Map.entry("clock", ClockGreeter.class)
	);

	/**
	 * A list of all known and valid greeters names.
	 */
	public static final Collection<String> VALID_GREETER_NAMES = VALID_GREETERS.keySet();
}
