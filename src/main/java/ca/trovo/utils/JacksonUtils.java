package ca.trovo.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utility class for {@code Jackson JSON}.
 */
public class JacksonUtils {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(JacksonUtils.class.getName());

	/** Cached empty {@code JSON}. */
	private static final String EMPTY_JSON = "{}";


	/** The {@code Jackson} mapper to use when serializing and deserializing objects. */
	private ObjectMapper mapper;

	/**
	 * Utility method to create a default {@code Jackson} mapper.
	 *
	 * @return The {@code Jackson} mapper with default setup.
	 *
	 */
	public static ObjectMapper getDefaultMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return mapper;
	}




	/**
	 * Build a new instance of this utility class that will use the given {@code mapper} for {@code Jackson}.
	 *
	 * @param mapper
	 * 			The {@code Jackson} mapper to use.
	 *
	 */
	public JacksonUtils(ObjectMapper mapper) {
		this.mapper = mapper;
	}


	/** A singleton instance that uses default dependencies, lazily initialized. */
	private static volatile JacksonUtils INSTANCE;

	/**
	 * Entry-point method of this class with default configuration.
	 *
	 * @return A singleton instance that uses default dependencies.
	 *
	 * @see #getDefaultMapper()
	 * @see #JacksonUtils(ObjectMapper)
	 *
	 */
	public static JacksonUtils getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}

		synchronized (JacksonUtils.class) {
			if (INSTANCE != null) {
				return INSTANCE;
			}

			INSTANCE = new JacksonUtils(getDefaultMapper());
		}

		return INSTANCE;
	}



	/**
	 * Serializes an object to a {@code JSON} {@link String}.
	 *
	 * @param object
	 * 			The object to serialize as {@code JSON}.
	 *
	 * @return The serialized object as {@code JSON}.
	 * 			An empty {@code JSON} (<code>{}</code>) if an error happen during serialization.
	 *
	 */
	public String serialize(Object object) {
		String json;

		try {
			json = this.mapper.writeValueAsString(object);
		} catch (JsonProcessingException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			json = EMPTY_JSON;
		}

		return json;
	}


	/**
	 * Deserializes a {@code JSON} {@link String} into an object of the given {@code type}.
	 *
	 * @param <T>
	 * 			The object's type to serialize into.
	 *
	 * @param json
	 * 			The {@code JSON} representation of the object.
	 * @param type
	 * 			The object's type class to serialize into.
	 *
	 * @return An instance of an object of the given {@code type}, with information read from the given {@code json}.
	 * 			Or {@code null} if an error happens during deserialization.
	 */
	public <T> T deserialize(String json, Class<T> type) {
		try {
			T obj = this.mapper.readValue(json, type);
			return obj;
		} catch (JsonProcessingException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			return null;
		}
	}

}
