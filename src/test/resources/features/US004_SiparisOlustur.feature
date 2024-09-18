Feature: US_004 Ürünler seçilip sepete eklenmeli

  @smoke @failed
  Scenario Outline: TC_001 Her kategori için random 3 ürün seçilip sepete eklenmeli
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then Sayfada "<kategoriler>" kategorisinin tiklanabilirligini test et
    And "<kategoriler>" kategorisine tikla
    Then Sayfa url'sinin "<linkler>" oldugunu test et
    And Acilan sayfanin "<kategoriler>" bolumu oldugunu test et
    Then Kategoride 0'dan fazla urun bulundugunu dogrula
    Then Rastgele verilen 1. X sirasinda bulunan urunun listede oldugunu dogrula
    Then Urune tikla
    Then Sayfa url'sinin "https://www.testotomasyonu.com/product/" icerdigini test et
    Then Gorunur durumdaysa "Add to Cart" butonuna tikla
    And Product Added To Cart! Yazisinin ciktigini dogrula
    Then Bir onceki sayfaya don
    Then Rastgele verilen 2. X sirasinda bulunan urunun listede oldugunu dogrula
    Then Urune tikla
    And Sayfa url'sinin "https://www.testotomasyonu.com/product/" icerdigini test et
    Then Gorunur durumdaysa "Add to Cart" butonuna tikla
    And Product Added To Cart! Yazisinin ciktigini dogrula
    Then Bir onceki sayfaya don
    Then Rastgele verilen 3. X sirasinda bulunan urunun listede oldugunu dogrula
    Then Urune tikla
    And Sayfa url'sinin "https://www.testotomasyonu.com/product/" icerdigini test et
    Then Gorunur durumdaysa "Add to Cart" butonuna tikla
    Then Product Added To Cart! Yazisinin ciktigini dogrula

    Examples:
      | kategoriler   | linkler                                        |
      | Electronics   | https://testotomasyonu.com/category/7/products |
#      | Men Fashion   | https://testotomasyonu.com/category/1/products |
#      | Women Fashion | https://testotomasyonu.com/category/2/products |
#      | Shoes         | https://testotomasyonu.com/category/3/products |
#      | Furniture     | https://testotomasyonu.com/category/8/products |
#      | Travel        | https://testotomasyonu.com/category/5/products |
#      | Kids Wear     | https://testotomasyonu.com/category/6/products |
      | Grocery       | https://testotomasyonu.com/category/4/products |


  Scenario: TC_002 Sepete eklenen ürünler ile sayfadaki ürünlerin ayni oldugu test edilmeli
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then Ana sayfada "Top Selling Products" bolumune git
    Then Gorunur durumdaysa "View All Products" butonuna tikla
    Then Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
    Then Kategoride 0'dan fazla urun bulundugunu dogrula
    Then Listedeki ilk urunun uzerine git
    And Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
    Then Gorunur durumdaysa "Add to Cart Float" butonuna tikla
    Then Product Added To Cart! Yazisinin ciktigini dogrula
    Then "Your Cart" gorunurlugunu test et
    Then Your Cart butonunun sayisininin 1 oldugunu dogrula
    Then Gorunur durumdaysa "Your Cart" butonuna tikla
    And Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
    Then Sepette urun bulundugunu test et
    Then Sepetteki urunun isminin listede eklenen urunun ismi ile ayni oldugunu test et


  Scenario: TC_003 Sepet bosaltilmali
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then Ana sayfada "Top Selling Products" bolumune git
    Then Gorunur durumdaysa "View All Products" butonuna tikla
    Then Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
    Then Kategoride 0'dan fazla urun bulundugunu dogrula
    Then Listedeki ilk urunun uzerine git
    And Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
    Then Gorunur durumdaysa "Add to Cart Float" butonuna tikla
    Then Product Added To Cart! Yazisinin ciktigini dogrula
    Then "Your Cart" gorunurlugunu test et
    Then Your Cart butonunun sayisininin 1 oldugunu dogrula
    Then Gorunur durumdaysa "Your Cart" butonuna tikla
    And Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
    Then Sepette urun bulundugunu test et
    Then Urun kutusunun kosesindeki X butonunun tiklanabilirligini test et
    Then X butonuna tikla
    Then "Are you sure" gorunurlugunu test et
    Then Gorunur durumdaysa "Yes, remove it!" butonuna tikla
    Then Wait for it... Yazisi kaybolana kadar bekle
    Then "Shopping cart is empty" gorunurlugunu test et

  @regression
  Scenario: TC_004 Ürün miktari arttirilarak ürün sepete eklenmeli ve test edilmeli
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then "Arama kutusu" gorunurlugunu test et
    Then Arama kutusuna phone yaz ve Enter'a bas
    And Sayfa url'sinin "https://testotomasyonu.com/search-product?search=phone&search_category=0" oldugunu test et
    Then Kategoride 0'dan fazla urun bulundugunu dogrula
    Then ilk urune tikla
    And Sayfa url'sinin "https://www.testotomasyonu.com/product/" icerdigini test et
    Then "+ butonu" gorunurlugunu test et
    Then Miktar kutusunda + butonuna 2 kere bas
    Then Gorunur durumdaysa "Add to Cart" butonuna tikla
    Then Product Added To Cart! Yazisinin ciktigini dogrula
    Then "Your Cart" gorunurlugunu test et
    Then Your Cart butonunun sayisininin 3 oldugunu dogrula
    Then Gorunur durumdaysa "Your Cart" butonuna tikla
    And Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
    Then Sepette urun bulundugunu test et
    Then Urun miktarinin 3 oldugunu test et

  @smoke
  Scenario: TC_005 Sepete eklenen ürün siparis verilmeli ve siparis verildigi dogrulanmali
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then Ana sayfada "Top Selling Products" bolumune git
    Then Gorunur durumdaysa "View All Products" butonuna tikla
    Then Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
    Then Kategoride 0'dan fazla urun bulundugunu dogrula
    Then Listedeki ilk urunun uzerine git
    Then Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
    Then Gorunur durumdaysa "Add to Cart Float" butonuna tikla
    Then Product Added To Cart! Yazisinin ciktigini dogrula
    Then "Your Cart" gorunurlugunu test et
    Then Your Cart butonunun sayisininin 1 oldugunu dogrula
    Then Gorunur durumdaysa "Your Cart" butonuna tikla
    And Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
    Then Sepette urun bulundugunu test et
    Then Gorunur durumdaysa "Checkout" butonuna tikla
    Then "Billing Address" gorunurlugunu test et
    Then Gorunur durumdaysa "Add Address" butonuna tikla
    And Name, address, address2, city, postcode, ülke ve sehir bilgilerini doldur
    Then Gorunur durumdaysa "Add Address Form" butonuna tikla
    Then "Billing Address Listesi"nde adres bulundugunu test et
    Then "Billing Address Listesi"nden adres sec
    Then Delivery address same as billing address kutucuguna tikla
    Then Delivery Address bolumunun gorunur olmadigini test et
    Then Delivery address same as billing address kutucugunu unchecked yap
    Then "Delivery Address" gorunurlugunu test et
    Then Gorunur durumdaysa "Add Address2" butonuna tikla
    And Name, address, address2, city, postcode, ülke ve sehir bilgilerini doldur
    Then Gorunur durumdaysa "Add Address Form" butonuna tikla
    Then "Delivery Address Listesi"nde adres bulundugunu test et
    Then "Delivery Address Listesi"nden adres sec
    Then Shipping Methods listesinden kargo sec
    Then Terms and Conditions boxini checkle
    Then Gorunur durumdaysa "Place Order Now" butonuna tikla
    Then Sayfada "Your order is successfully done!" gorunurlugunu test et
