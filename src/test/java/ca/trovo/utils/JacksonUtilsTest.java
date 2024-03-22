package ca.trovo.utils;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit Test class for {@link JacksonUtils}.
 */
public class JacksonUtilsTest {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(JacksonUtilsTest.class.getName());


	/**
	 * Inner test class to use for serialization and deserialization.
	 */
	public static class SerializationClass {

		/** A "default" instance to facilitate asserting test results. */
		public static final SerializationClass TEST_INSTANCE =
				new SerializationClass("serialization class tester!");

		/** A "default" serialized value to facilitate asserting test results. */
		public static final String TEST_INSTANCE_SERIALIZED =
				"""
		        {"name":"serialization class tester!"}"""; // important to keep """ here, otherwise Java adds a trailing \n!

		private String name;
		public SerializationClass() {}
		public SerializationClass(String name) {
			this.name = name;
		}


		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.name);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof SerializationClass other)) {
				return false;
			}

			return Objects.equals(this.name, other.name);
		}
	}


	/**
	 * Inner test record to use for serialization and deserialization.
	 */
	public static record SerializationRecord (String name) {

		/** A "default" instance to facilitate asserting test results. */
		public static final SerializationRecord TEST_INSTANCE =
				new SerializationRecord("serialization record tester!");

		/** A "default" serialized value to facilitate asserting test results. */
		public static final String TEST_INSTANCE_SERIALIZED =
				"""
		        {"name":"serialization record tester!"}"""; // important to keep """ here, otherwise Java adds a trailing \n!
	}


	@Test
	public void shouldSerializeClass() {
		LOGGER.log(Level.FINER, "Serializing default test instance.");
		String json = JacksonUtils.getInstance().serialize(SerializationClass.TEST_INSTANCE);

		// Visually compare if need to debug the test
		LOGGER.log(Level.FINE, "Serialized object: \t\t{0}", json);
		LOGGER.log(Level.FINE, "Default String: \t\t\t{0}", SerializationClass.TEST_INSTANCE_SERIALIZED);

		Assert.assertNotNull("The serialized json is null.", json);
		Assert.assertFalse("the serialized json is empty.", json.isBlank());
		Assert.assertEquals("The serialized json is invalid.", SerializationClass.TEST_INSTANCE_SERIALIZED, json);
	}

	@Test
	public void shouldDeserializeClass() {
		LOGGER.log(Level.FINER, "Deserializing default test value.");
		SerializationClass obj = JacksonUtils.getInstance().deserialize(SerializationClass.TEST_INSTANCE_SERIALIZED, SerializationClass.class);

		Assert.assertNotNull("The deserialized object is null.", obj);
		Assert.assertEquals("The deserialized object is invalid.", SerializationClass.TEST_INSTANCE, obj);
	}


	@Test
	public void shouldSerializeRecord() {
		LOGGER.log(Level.FINER, "Serializing default test instance.");
		String json = JacksonUtils.getInstance().serialize(SerializationRecord.TEST_INSTANCE);

		// Visually compare if need to debug the test
		LOGGER.log(Level.FINE, "Serialized object: \t\t{0}", json);
		LOGGER.log(Level.FINE, "Default String: \t\t\t{0}", SerializationRecord.TEST_INSTANCE_SERIALIZED);

		Assert.assertNotNull("The serialized json is null.", json);
		Assert.assertFalse("the serialized json is empty.", json.isBlank());
		Assert.assertEquals("The serialized json is invalid.", SerializationRecord.TEST_INSTANCE_SERIALIZED, json);
	}

	@Test
	public void shouldDeserializeRecord() {
		LOGGER.log(Level.FINER, "Deserializing default test value.");
		SerializationRecord obj = JacksonUtils.getInstance().deserialize(SerializationRecord.TEST_INSTANCE_SERIALIZED, SerializationRecord.class);

		Assert.assertNotNull("The deserialized object is null.", obj);
		Assert.assertEquals("The deserialized object is invalid.", SerializationRecord.TEST_INSTANCE, obj);
	}
}
