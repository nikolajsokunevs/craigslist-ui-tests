import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "steps",
        plugin = {"pretty", "html:target/cucumber-html-report.html", "json:target/cucumber.json"})
public class RunCucumberTest {

}