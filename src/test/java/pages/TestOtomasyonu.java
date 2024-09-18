package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverParallel;

import java.util.List;

public class TestOtomasyonu {

    public TestOtomasyonu(){
        PageFactory.initElements(DriverParallel.getDriver(), this);
    }

    @FindBy (className = "login-form")
    public WebElement FormHead;

    @FindBy (id = "email")
    public WebElement logInEmail;

    @FindBy (id = "password")
    public WebElement logInPassword;

    @FindBy (id = "firstName")
    public WebElement signupfirstName;

    @FindBy (xpath = "/html/body/div[3]/div") //  //*[@class='fa fa-check']
    public WebElement successBox;

    @FindBy (id = "btn-submit-form")
    public WebElement signUpButton;

    @FindBy (className = "text-danger")
    public List<WebElement> required;

    @FindBy (xpath = "//span[text()='Confirm password does not match']")
    public WebElement confirmPasswordRequired;

    @FindBy (className = "current")
    public WebElement kategoriBasligi;

    @FindBy (xpath = "//nav/ul/li[@class='has-sub']")
    public List<WebElement> kategoriListe;

    @FindBy (xpath = "//div[2]/form/div/a")
    public WebElement selectCategory;


    @FindBy (css = ".search-dropdown.open ul li")
    public List<WebElement> selectCategoryListe;


    @FindBy (xpath = "//section[6]/div[2]/ul/li")
    public List<WebElement> mostPopularProductKategoriList;

    @FindBy (className = "product-count-text")
    public WebElement sonuc;

    @FindBy (className = "add-to-cart")
    public WebElement addToCart;

}
