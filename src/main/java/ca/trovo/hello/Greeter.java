package ca.trovo.hello;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Represents a greeter, one who greets someone else. The host (person) acting and creating {@link Greeting}.
 * <p>
 * Serializes with the implementation class information on it (using {@code Jackson}). For example:
 * <code>{
 *     "greeter":{"@class":"ca.trovo.hello.greeter.SimpleGreeter"}
 * }</code>
 */
@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY
)
public interface Greeter {

	/**
	 * Perform the interaction with {@code greetee}, creating a {@link Greeting}.
	 * <p>
	 * How this <b>greeter</b> <em>greets</em> the <b>greetee</b>.
	 * </p>
	 *
	 * @param greetee
	 * 			One how is greeted.
	 *
	 * @return The interaction, how this <b>greeter</b> has <em>greeted</em> the <b>greetee</b>.
	 *
	 */
	public Greeting greet(String greetee);

}
