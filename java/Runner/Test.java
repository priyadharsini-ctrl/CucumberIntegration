package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/main/java/features/Car.feature", glue = "steps", 
		monochrome = true /*,dryRun = true, snippets = SnippetType.CAMELCASE */)
public class Test extends AbstractTestNGCucumberTests {

}
