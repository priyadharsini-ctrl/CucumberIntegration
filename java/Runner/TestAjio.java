package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/main/java/features/Ajio.feature", glue = "steps", 
monochrome = true , snippets = SnippetType.CAMELCASE )
public class TestAjio extends AbstractTestNGCucumberTests {

}
