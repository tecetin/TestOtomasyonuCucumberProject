Feature: US_003 Ana sayfadaki kategori linkleri doğrulanmalı

  @smoke
  Scenario Outline: TC_001 Kategori linkleri tiklandiktan sonra dogrulama yapilmali
    Given "url"`ye git
    And Sayfaya gittigini dogrula
    Then Sayfada "<kategoriler>" kategorisinin tiklanabilirligini test et
    And "<kategoriler>" kategorisine tikla
    Then Sayfa url'sinin "<linkler>" oldugunu test et
    And Acilan sayfanin "<kategoriler>" bolumu oldugunu test et
    Examples:
      | kategoriler   | linkler                                        |
      | Electronics   | https://testotomasyonu.com/category/7/products |
      | Men Fashion   | https://testotomasyonu.com/category/1/products |
      | Women Fashion | https://testotomasyonu.com/category/2/products |
      | Shoes         | https://testotomasyonu.com/category/3/products |
      | Furniture     | https://testotomasyonu.com/category/8/products |
      | Travel        | https://testotomasyonu.com/category/5/products |
      | Kids Wear     | https://testotomasyonu.com/category/6/products |
      | Grocery       | https://testotomasyonu.com/category/4/products |

  @regression
  Scenario Outline: TC_002 Arama kutusundaki kategoriler ile ana sayfadaki kategorilerin ayni oldugu dogrulanmali
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Anasayfada kategorilerin gorunurlugunu test et
    Then Anasayfa kategori isimlerini liste olarak kaydet
    And Arama kutusundaki Select Category butonunun gorunurlugunu test et
    Then Gorunur durumdaysa "SelectCategory" butonuna tikla
    And "KategoriListesi" gorunurlugunu test et
    Then Kategori listesindeki isimleri liste olarak kaydet
    And Anasayfa kategori listesi ile Arama kutusu kategori listesi uzunluklarinin ayni oldugunu dogrula
    Then Anasayfa kategori listesindeki "<kategoriler>"in "Arama kutusu" kategori listesinde oldugunu dogrula
    Examples:
      | kategoriler   |
      | Electronic    |
      | Men Fashion   |
      | Women Fashion |
      | Shoes         |
      | Furniture     |
      | Travel        |
      | Kids Wear     |
      | Grocery       |


  Scenario Outline: TC_003 Most Popular Products bolumundeki kategorilerinde ayni oldugu dogrulanmali
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Anasayfada kategorilerin gorunurlugunu test et
    Then Anasayfa kategori isimlerini liste olarak kaydet
    Then Ana sayfada "Most Popular Products" bolumune git
    Then "Most Popular Products Kategorileri" gorunurlugunu test et
    Then Most Popular Products Kategori listesindeki isimleri liste olarak kaydet
    Then Anasayfa kategori listesi ile Most Popular Products kategori listesi uzunluklarinin ayni oldugunu dogrula
    Then Anasayfa kategori listesindeki "<kategoriler>"in "Most Popular Products" kategori listesinde oldugunu dogrula
    Examples:
      | kategoriler   |
      | Electronics   |
      | Men Fashion   |
      | Women Fashion |
      | Shoes         |
      | Furniture     |
      | Travel        |
      | Kids Wear     |
      | Grocery       |