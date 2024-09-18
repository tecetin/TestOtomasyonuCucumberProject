package stepDefinitions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.DriverParallel;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;

public class ExceldekiVeriler {

    private final String pathVeri = "C:/Users/tugba/IdeaProjects/TestOTomasyonuCucumberProject/TestVerileriCucumber.xlsx";
    protected final String firstName = exceldeKullaniciBilgileriBul("firstName");
    protected final String lastName = exceldeKullaniciBilgileriBul("lastName");
    protected final String email = exceldeKullaniciBilgileriBul("email");
    protected final String password = exceldeKullaniciBilgileriBul("password");
    protected final String phone = exceldeKullaniciBilgileriBul("phone");
    protected final String address = exceldeKullaniciBilgileriBul("address");
    protected final String address2 = exceldeKullaniciBilgileriBul("address2");
    protected final String country = exceldeKullaniciBilgileriBul("country");
    protected final String state = exceldeKullaniciBilgileriBul("state");
    protected final String city = exceldeKullaniciBilgileriBul("city");
    protected final String postcode = exceldeKullaniciBilgileriBul("postcode");


    WebDriverWait wait = new WebDriverWait(DriverParallel.getDriver(), Duration.ofSeconds(10));
    SoftAssert softAssert = new SoftAssert();
    TestOtomasyonu to = new TestOtomasyonu();
    Actions actions = new Actions(DriverParallel.getDriver());
    JavascriptExecutor js = (JavascriptExecutor) DriverParallel.getDriver();


    public ExceldekiVeriler() throws IOException {
    }

    public String exceldeXPathBul(String elementAdi) throws IOException {

        String pageXpath = "WebElementXPaths";
        Sheet sheetXpath = ReusableMethods.excelOku(pathVeri, pageXpath);
        elementAdi = elementAdi.replaceAll(" ", "");

        String xpath = "";

        for (Row row : sheetXpath) {
            Cell cell = row.getCell(0); // Hedef sütundaki hücreyi alıyoruz

            if (cell != null && cell.getCellType() == CellType.STRING) {
                String cellValue = cell.getStringCellValue();

                if (cellValue.equals(elementAdi)) { //aradığımız elementin bulunduğu sütuna kadar gider

                    Cell xPathHucre = row.getCell(1); //xpathler 1 indeksli sütunda

                    if (xPathHucre != null) {
                        xpath = xPathHucre.getStringCellValue();
                    }
                }
            }
        }
        return xpath;
    }

    public String exceldeKategoriLinkiBul(String kategori) throws IOException {

        String pageKategori = "Kategoriler";
        Sheet sheetXpath = ReusableMethods.excelOku(pathVeri, pageKategori);
        kategori = kategori.replaceAll(" ", "");

        String link = "";

        for (Row row : sheetXpath) {
            Cell cell = row.getCell(0); // Hedef sütundaki hücreyi alıyoruz

            if (cell != null && cell.getCellType() == CellType.STRING) {
                String cellValue = cell.getStringCellValue();

                if (cellValue.equals(kategori)) { //aradığımız elementin bulunduğu sütuna kadar gider

                    Cell linkHucre = row.getCell(1); //xpathler 1 indeksli sütunda

                    if (linkHucre != null) {
                        link = linkHucre.getStringCellValue();
                    }
                }
            }
        }
        return link;
    }

    public String exceldeKullaniciBilgileriBul(String bilgiCesidi) throws IOException {

        String pageKullanici = "KullaniciBilgileri";
        Sheet sheetXpath = ReusableMethods.excelOku(pathVeri, pageKullanici);
        bilgiCesidi = bilgiCesidi.replaceAll(" ", "");

        String bilgi = "";

        for (Row row : sheetXpath) {
            Cell cell = row.getCell(0); // Hedef sütundaki hücreyi alıyoruz

            if (cell != null && cell.getCellType() == CellType.STRING) {
                String cellValue = cell.getStringCellValue();

                if (cellValue.equals(bilgiCesidi)) { //aradığımız elementin bulunduğu sütuna kadar gider

                    Cell bilgiHucre = row.getCell(1); //xpathler 1 indeksli sütunda

                    if (bilgiHucre != null) {
                        bilgi = bilgiHucre.getStringCellValue();
                    }
                }
            }
        }
        return bilgi;
    }

    public WebElement elementBul(String isim) throws IOException {

        String xpath = exceldeXPathBul(isim);

        return DriverParallel.getDriver().findElement(By.xpath(xpath));
    }

}
