package ca.trovo.utils.test;

import org.junit.Assert;
import org.junit.Test;

import ca.trovo.utils.HttpUtils;

/**
 * Unit Test class for {@link HttpUtils}.
 */
public class HttpUtilsTest {

	/**
	 * Tests {@link HttpUtils#encodeParameterForURL(String)} for a simple String,
	 * meaning that there's nothing to encode and the output should be the exact same as the input.
	 */
	@Test
	public void shouldNotEncode() {
		String parameter = "John";
		String expected = parameter;
		String encoded = HttpUtils.encodeParameterForURL(parameter);
		Assert.assertEquals(expected , encoded);
	}
}
