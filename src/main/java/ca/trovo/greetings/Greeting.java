package ca.trovo.greetings;

/**
 * Represents a Greeting.
 *
 * @param greeter
 * 			One who greets someone else. the host, person acting and creating {@code greeting}.
 * @param greetee
 * 			One how is greeted.
 * @param greeting
 * 			The result of the interaction, how the <b>greeter</b> has <em>greeted</em> the <b>greetee</b>.
 *
 */
public record Greeting (Greeter greeter, String greetee, String greeting) {

}
