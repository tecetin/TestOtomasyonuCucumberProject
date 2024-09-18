package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/Reports/Cucumber_Regression_Reports/raporRegression.html", //sonuçları görsel olarak incelemek için idealdir.
                "json:target/Reports/Cucumber_Regression_Reports/raporRegression.json", //özellikle diğer araçlarla entegre olmak ve raporu daha sonra işlemek için kullanılır.
        },
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        tags = "@regression",
        dryRun = false
)

public class RunnerParallelRegression extends AbstractTestNGCucumberTests { //TestNG ile çalıştırabilmek için Abstract Class extend edilmelidir.

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}


