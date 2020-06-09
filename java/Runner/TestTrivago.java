package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/main/java/features/Trivago.feature", glue = "steps", 
		monochrome = true /* ,dryRun = true */)
public class TestTrivago extends AbstractTestNGCucumberTests {

}
