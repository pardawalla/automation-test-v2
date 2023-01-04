package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions (
    features = "src/test/java/cucumber/features/SearchMotors.feature",
    glue = {"stepDefinitions.SearchMotorsTest"},
    snippets = SnippetType.CAMELCASE,
    dryRun = true
)
public class Runner extends AbstractTestNGCucumberTests {
    
}
