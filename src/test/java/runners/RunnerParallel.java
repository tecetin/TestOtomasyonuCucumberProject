package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {
                "html:target/Reports/Cucumber_mvn_Reports/rapor.html",
                "json:target/Reports/Cucumber_mvn_Reports/rapor.json", //özellikle diğer araçlarla entegre olmak ve raporu daha sonra işlemek için kullanılır.

        },
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        tags = "@smoke and @regression",
        dryRun = false
)

public class RunnerParallel extends AbstractTestNGCucumberTests { //TestNG ile çalıştırabilmek için Abstract Class extend edilmelidir.

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}


