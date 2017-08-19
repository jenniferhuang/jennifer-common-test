import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/4/2017
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"}
)
public class RunTest {
}


//Running the same tests would now produce an html result and also a json output. You can see the same in the target folder inside your project.
