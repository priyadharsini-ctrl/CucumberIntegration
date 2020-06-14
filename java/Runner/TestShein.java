package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/main/java/features/Shein.feature", glue = "steps", 
monochrome = true  , snippets=SnippetType.CAMELCASE )
public class TestShein extends AbstractTestNGCucumberTests{

}
