package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin = {"pretty", //konsolda daha okunabilir bir rapor üretilmesini sağlar. Bu, özellikle hata ayıklama sırasında faydalıdır.
                "html:target/Cucumber_Reports/html-reports/rapor.html", //sonuçları görsel olarak incelemek için idealdir.
                "json:target/Cucumber_Reports/json-reports/rapor.json", //özellikle diğer araçlarla entegre olmak ve raporu daha sonra işlemek için kullanılır.
                "json:target/Cucumber_Reports/json-reports/rapor.xml"
                },
        monochrome = true,                           // Konsolda daha temiz bir çıktı
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        tags = "@smoke",
        dryRun = false
)
public class Runner extends AbstractTestNGCucumberTests { //TestNG ile çalıştırabilmek için Abstract Class extend edilmelidir.
}
