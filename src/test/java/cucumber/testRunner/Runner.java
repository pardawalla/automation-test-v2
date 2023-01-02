package cucumber.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions (
    features = "src/test/java/cucumber/features/SearchMotors.feature",
    glue = {"tepDefinitions"},
    snippets = SnippetType.CAMELCASE,
    monochrome = true,
    dryRun = true
)
public class Runner extends AbstractTestNGCucumberTests {
    
}
