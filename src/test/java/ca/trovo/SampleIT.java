package ca.trovo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Sample integration test that runs in {@code Arquillian} where it creates a {@code test.war} file.
 * Serves as "health-check" to ensure tests are running correctly, as this should always pass.
 *
 * @see BaseIntegrationTestSuite#buildTestWar()
 *
 */
@RunWith(Arquillian.class)
public class SampleIT extends BaseIntegrationTestSuite {

	/** The logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(SampleIT.class.getName());


    /**
     * A sample test, also serves as sanity test to validate things are running...
     * This should always succeed.
     */
    @Test
    public void test() {
        LOGGER.log(Level.INFO, "Sample test has been invoked.");
        Assert.assertTrue(true);
    }
}
