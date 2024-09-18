package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.DriverParallel;
import utilities.ReusableMethods;

import java.io.IOException;
import java.util.List;

public class US003_KategoriKontrol extends ExceldekiVeriler {

    WebElement element;
    List<String> anasayfaKategoriler;
    List<String> aramaKutusuKategoriler;
    List<String> mostPopularProductsKategoriler;

    public US003_KategoriKontrol() throws IOException {
    }

    @Then("Sayfada {string} kategorisinin tiklanabilirligini test et")
    public void sayfadaKategorisininTiklanabilirliginiTestEt(String kategori) throws IOException {

        String kategoriDuzeltme = kategori.replaceAll(" ", "");

        String xpath = exceldeXPathBul(kategoriDuzeltme);

        element = DriverParallel.getDriver().findElement(By.xpath(xpath));

        wait.until(ExpectedConditions.elementToBeClickable(element));
        softAssert.assertTrue(element.isEnabled());

    }

    @Then("{string} kategorisine tikla")
    public void kategorisineTikla(String kategori) {
        ReusableMethods.click(element);
    }

    @Then("Sayfa url'sinin {string} oldugunu test et")
    public void sayfaUrlSininOldugunuTestEt(String expUrl) {

        String actUrl = DriverParallel.getDriver().getCurrentUrl();
        softAssert.assertEquals(actUrl, expUrl);
    }

    @And("Acilan sayfanin {string} bolumu oldugunu test et")
    public void acilanSayfaninBolumuOldugunuTestEt(String kategori) {

        wait.until(ExpectedConditions.visibilityOf(to.kategoriBasligi));
        softAssert.assertEquals(to.kategoriBasligi.getText(), kategori);
    }

    @And("Anasayfada kategorilerin gorunurlugunu test et")
    public void anasayfadakategorilerinGorunurlugunuTestEt() {

        softAssert.assertFalse(to.kategoriListe.isEmpty());
    }

    @Then("Anasayfa kategori isimlerini liste olarak kaydet")
    public void anasayfaKategoriIsimleriniListeOlarakKaydet() {

        anasayfaKategoriler = ReusableMethods.toStringList(to.kategoriListe);
    }

    @And("Arama kutusundaki Select Category butonunun gorunurlugunu test et")
    public void aramaKutusundakiSelectCategoryButonununGorunurlugunuTestEt() {

        softAssert.assertTrue(to.selectCategory.isDisplayed());
    }

    @Then("Kategori listesindeki isimleri liste olarak kaydet")
    public void kategoriListesindekiIsimleriListeOlarakKaydet() {

        aramaKutusuKategoriler = ReusableMethods.toStringList(to.selectCategoryListe);
        aramaKutusuKategoriler.removeFirst(); //listede ilk sırada Select Category var
    }

    @And("Anasayfa kategori listesi ile Arama kutusu kategori listesi uzunluklarinin ayni oldugunu dogrula")
    public void anasayfaKategoriListesiIleAramaKutusuKategoriListesiUzunluklarininAyniOldugunuDogrula() {

        int AnasayfaListSize = anasayfaKategoriler.size();
        int AramaListSize = aramaKutusuKategoriler.size();
        softAssert.assertEquals(AnasayfaListSize, AramaListSize);
    }

//    @Then("Anasayfa kategori listesindeki {string}in {string} kategori listesinde oldugunu dogrula")
//    public void anasayfaKategoriListesindekiInKategoriListesindeOldugunuDogrula(String bolum) {
//
//        List<String> bolumKategorileri;
//        int size;
//        String bolumIsmi = bolum.replaceAll(" ", "").toLowerCase();
//
//        if (bolumIsmi.contains("most")){
//            bolumKategorileri = mostPopularProductsKategoriler;
//            size = bolumKategorileri.size();
//        } else {
//            bolumKategorileri = aramaKutusuKategoriler;
//            size = bolumKategorileri.size();
//        }
//
//        for (int i = 0; i < size; i++) {
//            String kategori = anasayfaKategoriler.get(i);
//            softAssert.assertTrue(bolumKategorileri.contains(kategori),
//                    "Kategori bulunamadı: " + kategori);
//        }
//        softAssert.assertAll();
//    }

    @Then("Anasayfa kategori listesindeki {string}in {string} kategori listesinde oldugunu dogrula")
    public void anasayfaKategoriListesindekiInKategoriListesindeOldugunuDogrula(String kategori, String bolum) {

        List<String> bolumKategorileri;
        int size;
        String bolumIsmi = bolum.replaceAll(" ", "").toLowerCase();

        if (bolumIsmi.contains("most")){
            bolumKategorileri = mostPopularProductsKategoriler;
            size = bolumKategorileri.size();
        } else {
            bolumKategorileri = aramaKutusuKategoriler;
            size = bolumKategorileri.size();
        }

            softAssert.assertTrue(bolumKategorileri.contains(kategori),
                    "Kategori bulunamadı: " + kategori);

        softAssert.assertAll();
    }

    @Then("Ana sayfada {string} bolumune git")
    public void anaSayfadaBolumuneGit(String elementAdi) throws IOException {

        String elementT = elementAdi.replaceAll(" ", "");

        String xpath = exceldeXPathBul(elementT);

        WebElement element = DriverParallel.getDriver().findElement(By.xpath(xpath));

        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Then("Most Popular Products Kategori listesindeki isimleri liste olarak kaydet")
    public void mostPopularProductsKategoriListesindekiIsimleriListeOlarakKaydet() {

        mostPopularProductsKategoriler = ReusableMethods.toStringList(to.mostPopularProductKategoriList);
    }

    @Then("Anasayfa kategori listesi ile Most Popular Products kategori listesi uzunluklarinin ayni oldugunu dogrula")
    public void anasayfaKategoriListesiIleMostPopularProductsKategoriListesiUzunluklarininAyniOldugunuDogrula() {

        int anaSayfa = anasayfaKategoriler.size();

        softAssert.assertEquals(anaSayfa, mostPopularProductsKategoriler.size());
    }

    @Then("Anasayfa kategori listesindeki {string}in Most Popular Products kategori listesinde oldugunu dogrula")
    public void anasayfaKategoriListesindekiInMostPopularProductsKategoriListesindeOldugunuDogrula() {


        for (int i = 0; i < mostPopularProductsKategoriler.size(); i++) {
            String kategori = anasayfaKategoriler.get(i);
            softAssert.assertTrue(mostPopularProductsKategoriler.contains(kategori),
                    "Kategori bulunamadı: " + kategori);
        }
        softAssert.assertAll();
    }

}