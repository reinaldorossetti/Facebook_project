package facebook.com.facebook.automation;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	// path to feature.
	features = "resource/",
	
	// the plugin will generate the report in html and json to integration continues.
	plugin = { "pretty",
	        "html:target/site/cucumber-pretty",
	        "json:target/cucumber.json" } )

public class RunnerTest {

}
