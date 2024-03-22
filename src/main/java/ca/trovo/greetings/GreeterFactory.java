package ca.trovo.greetings;

import java.util.Objects;

import ca.trovo.greetings.greeter.ClockGreeter;
import ca.trovo.greetings.greeter.GreeterNotFoundException;
import ca.trovo.greetings.greeter.SimpleGreeter;
import ca.trovo.greetings.greeter.SlangGreeter;
import jakarta.enterprise.context.ApplicationScoped;


/**
 * Builds {@link Greeter} instances.
 */
@ApplicationScoped
public class GreeterFactory {

	/**
	 * TODO: Make db-driven.
	 *
	 * Get a greeter by its name.
	 *
	 * @param name
	 * 			The name of the {@link Greeter} to get.
	 *
	 * @return The {@link Greeter} that is known by the given {@code name}.
	 *
	 * @throws NullPointerException
	 * 				When the given {@code name} is {@code null} or {@code empty}.
	 * @throws GreeterNotFoundException
	 * 				When no {@link Greeter} is found with the given {@code name}.
	 *
	 *
	 */
	public Greeter get(String name) throws NullPointerException, GreeterNotFoundException {
		Objects.requireNonNull(name, "The name is required to get a Greeter.");

		name = name.trim();
		if (name.isEmpty()) {
			throw new NullPointerException("The name is required to get a Greeter.");
		}

		return switch (name) {
			case "simple" -> new SimpleGreeter();
			case "slang" -> new SlangGreeter();
			case "clock" -> new ClockGreeter();
			default -> throw GreeterNotFoundException.forName(name);
		};
	}
}
