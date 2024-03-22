package ca.trovo;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;


/**
 * Base class for all integration tests for this project that are going to run in {@code Arquillian}.
 */
public abstract class BaseIntegrationTestSuite extends ArquillianDeploymentBuilder {

	/** The {@code .war} file name that is the output of this project. Defined/Created by {@code Maven pom.xml}. */
	private static final String DEPLOYMENT_FILE_NAME = "hello-jakartaee.war";


	/**
	 * {@code Arquillian} executes this method during setup phase to:
	 * <ol>
	 *     <li>The real application's {@link #DEPLOYMENT_FILE_NAME} into its container (e.g. {@code Wildfly})</li>
	 *     <li>Add, build and deploy the test classes defined in this package as part of the deployment.</li>
	 * </ol>
	 * This will include all classes under this package
	 *
	 * @return The {@code Arquillian} archive for deployment.
	 *
	 * @see #buildTestWar(Collection)
	 */
	@Deployment
    public static Archive<?> buildTestWar() {
		return buildTestWar(
			findPackageNamesStartingWith(BaseIntegrationTestSuite.class.getPackageName())
		);
	}


	/**
	 * Helper method that will build the test {@code .war} file for this application using the given test packages.
	 *
	 * @param packages
	 * 			The test packages to deploy.
	 *
	 * @return The {@code Arquillian} archive for deployment.
	 *
	 * @see #buildTestWar(Collection, File)
	 *
	 */
	public static Archive<?> buildTestWar(Collection<String> packages) {
		return buildTestWar(packages, null);
	}


	/**
	 * Helper method that will build the test {@code .war} file for this application using the given test packages.
	 *
	 * @param packages
	 * 			The test packages to deploy.
	 * @param exportedTestWarOutput
	 * 			The {@link File} where the {@code test.war} will be written.
	 * 			Used only for debugging the test code generation.
	 *
	 * @return The {@code Arquillian} archive for deployment.
	 *
	 * @see ArquillianDeploymentBuilder#buildTestWar(String, String, Collection, File)
	 * @see ArquillianDeploymentBuilder#DEPLOYMENT_FILE_PATH
	 * @see #DEPLOYMENT_FILE_NAME
	 *
	 */
	public static Archive<?> buildTestWar(Collection<String> packages, File exportedTestWarOutput) {
		return ArquillianDeploymentBuilder.buildTestWar(ArquillianDeploymentBuilder.DEPLOYMENT_FILE_PATH, DEPLOYMENT_FILE_NAME, packages, exportedTestWarOutput);
	}


	/**
	 * Helper method to find all packages in the classpath where the name starts with the given {@code prefix}.
	 *
	 * @param prefix
	 * 			The package's prefix to filter.
	 *
	 * @return The name of the packages in the classpath where it starts with the given {@code prefix}.
	 *
	 */
	public static Collection<String> findPackageNamesStartingWith(String prefix) {
	    return Arrays.stream(Package.getPackages())
	        .map(Package::getName)
	        .filter(name -> name.startsWith(prefix))
	        .collect(Collectors.toList());
	}
}
