package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.DriverParallel;
import utilities.ReusableMethods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class US001_KullaniciKaydi_SD extends ExceldekiVeriler {

    public US001_KullaniciKaydi_SD() throws IOException {
    }

    @Given("{string}`ye git")
    public void urlyeGit(String url) {
        DriverParallel.getDriver().get(ConfigReader.getProperty(url));
    }

    @And("Gorunur durumdaysa {string} butonuna tikla")
    public void gorunurDurumdaysaATikla(String elementAdi) throws IOException {

        String xpath = exceldeXPathBul(elementAdi);

        WebElement element = DriverParallel.getDriver().findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        ReusableMethods.click(element);
    }

    @Then("Sayfaya gittigini dogrula")
    public void sayfayaGittiginiDogrula() {
        String title = "Test Otomasyonu";
        String actTitle = DriverParallel.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title), "Anasayfa Test Otomasyonu başlığını içermiyor");
    }

    @When("Register Now sayfasina gelindigi dogrula")
    public void registerNowSayfasinaGelindigiDogrula() {
        String formText = "Register Now";
        String actText = to.FormHead.getText();
        wait.until(ExpectedConditions.visibilityOf(to.FormHead));
        softAssert.assertEquals(actText, formText);
    }

    @Then("Sayfada {string} gorunurlugunu test et")
    public void kayitSayfasindaGorunurluguTestEt(String elementAdi) throws IOException {

        String isim = elementAdi.replaceAll(" ", "");
        String xpath = "";

        if (isim.equals("Kullaniciismi") || isim.equals("Kullanicisoyismi") || isim.equals("Kullaniciemail")){
            xpath = switch (isim) {
                case "Kullaniciismi" -> "//input[@value='" + firstName + "']";
                case "Kullanicisoyismi" -> "//input[@value='" + lastName + "']";
                case "Kullaniciemail" -> "//input[@value='" + email + "']";
                default -> xpath;
            };
        } else {
            xpath = exceldeXPathBul(isim);
        }

        WebElement element = DriverParallel.getDriver().findElement(By.xpath(xpath));
        softAssert.assertTrue(element.isDisplayed());
    }

    @Then("Tum bilgileri gecerli olarak gir")
    public void tumBilgileriGecerliOlarakGir() throws IOException {

        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();
        actions.sendKeys(firstName + Keys.TAB)
                .sendKeys(lastName + Keys.TAB)
                .sendKeys(email + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(password)
                .perform();
    }

    @Then("Basarili kayit yapildigini test et")
    public void basariliKayitYapildiginiTestEt() {
        boolean success = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(to.successBox));
            success = true;
        } catch (Exception ignored) {

        }
        softAssert.assertTrue(success, "Kayit yapilamadi.");
    }

    @Then("Bilgileri bos birak")
    public void bilgileriBosBirak() {
        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();

        wait.until(ExpectedConditions.visibilityOf(to.signUpButton));
    }

    @Then("Tum bilgiler icin required uyarisi verildigini dogrula")
    public void tumBilgilerIcinRequiredUyarisiVerildiginiDogrula() {
        List<String> requires = ReusableMethods.toStringList(to.required);
        List<String> expReq = Arrays.asList(
                "First name",
                "Last name",
                "Email address",
                "Password",
                "Confirm password does not match"
        );

        for (int i = 0; i < expReq.size(); i++) {
            String word = expReq.get(i);
            boolean var = false;

            for (String each : requires) {

                if (each.contains(word)) {
                    var = true; // Kelime bulundu
                    break;
                }
            }
            softAssert.assertTrue(var, word + " icin gereklilik uyarısı verilmiyor.");
        }

    }

    @And("Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et")
    public void kayitYapilamadigiVeRegisterNowSayfasindaKalindiginiTestEt() {
        String expUrl = "https://www.testotomasyonu.com/customer-register";
        String actUrl = DriverParallel.getDriver().getCurrentUrl();
        Assert.assertEquals(actUrl, expUrl); // Doğrulama

    }

    @Then("Email adresi @ isareti kullanilmadan gir")
    public void emailAdresiIsaretiKullanilmadanGir() {
        String yanlisEmail = email.replace("@", "");

        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();
        actions.sendKeys(firstName + Keys.TAB)
                .sendKeys(lastName + Keys.TAB)
                .sendKeys(yanlisEmail + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(password)
                .perform();

    }

    @Then("Confirm password passworddan farkli gir")
    public void confirmPasswordPassworddanFarkliGir() {
        String yanlisPassword = password.concat("abc");
        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();
        actions.sendKeys(firstName + Keys.TAB)
                .sendKeys(lastName + Keys.TAB)
                .sendKeys(email + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(yanlisPassword)
                .perform();

    }

    @Then("Password hatasi verildigi dogrulanir")
    public void passwordHatasiVerildigiDogrulanir() {
        softAssert.assertTrue(to.confirmPasswordRequired.isDisplayed());
    }

}
