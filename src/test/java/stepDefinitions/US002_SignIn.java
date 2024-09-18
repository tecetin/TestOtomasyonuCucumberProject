package stepDefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.DriverParallel;
import utilities.ReusableMethods;

import java.io.IOException;
import java.util.List;

public class US002_SignIn extends ExceldekiVeriler{

    public US002_SignIn() throws IOException {
    }

    @Then("Login Now sayfasina gelindigini test et")
    public void loginNowSayfasinaGelindiginiTestEt() {
        String formText = "Login Now";
        String actText = to.FormHead.getText();
        Assert.assertTrue(actText.contains(formText));
    }

    @Then("Email ve password alanlarinin gorunur oldugunu dogrula")
    public void emailVePasswordAlanlarininGorunurOldugunuDogrula() {
        softAssert.assertTrue(to.logInEmail.isDisplayed(), "Email alani görünür durumda değil.");
        softAssert.assertTrue(to.logInPassword.isDisplayed(), "Password alani görünür durumda değil.");
    }

    @Then("Gecerli email ve password gir")
    public void gecerliEmailVePasswordGir() {
        to.logInEmail.sendKeys(email);
        to.logInPassword.sendKeys(password);
    }

    @Then("{string} gorunurlugunu test et")
    public void gorunurlugunuTestEt(String elementAdi) throws IOException {
        String isim = elementAdi.replaceAll(" ", "");
        String xpath = exceldeXPathBul(isim);

        try {
            WebElement element = DriverParallel.getDriver().findElement(By.xpath(xpath));
            wait.until(ExpectedConditions.visibilityOf(element));
            ReusableMethods.scroll(element);
            softAssert.assertTrue(element.isDisplayed());

        } catch (Exception e) {
            List<WebElement> element = DriverParallel.getDriver().findElements(By.xpath(xpath));
            wait.until(ExpectedConditions.visibilityOf(element.getFirst()));
            softAssert.assertFalse(element.isEmpty());
        }
    }

    @Then("Kullanici profiline yonlendirildigini dogrula")
    public void kullaniciProfilineYonlendirildiginiDogrula() {
        String expUrl = "https://www.testotomasyonu.com/user-dashboard";
        String actUrl = DriverParallel.getDriver().getCurrentUrl();
        Assert.assertEquals(actUrl, expUrl);
    }
}
