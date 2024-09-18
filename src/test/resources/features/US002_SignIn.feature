
Feature: US_002 Kullanici olarak Sign in yapilabilmeli

  @smoke @regression
  Scenario: TC_001 email ve sifre girilmeli ve sistemde Account bolumunde bilgiler gorunmeli
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then Gorunur durumdaysa "Account" butonuna tikla
    Then Login Now sayfasina gelindigini test et
    Then Email ve password alanlarinin gorunur oldugunu dogrula
    Then Gecerli email ve password gir
    Then Gorunur durumdaysa "Sign In" butonuna tikla
    Then "Login Success" gorunurlugunu test et
    Then Kullanici profiline yonlendirildigini dogrula
    Then Sayfada "Kullanici ismi" gorunurlugunu test et
    Then Sayfada "Kullanici soyismi" gorunurlugunu test et
    Then Sayfada "Kullaniciemail" gorunurlugunu test et

  @regression
  Scenario: TC_002 MyProfile, My Orders, Wishlist, Manage Adress,change password ve Logout gorulmeli
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    Then Gorunur durumdaysa "Account" butonuna tikla
    Then Login Now sayfasina gelindigini test et
    Then Email ve password alanlarinin gorunur oldugunu dogrula
    Then Gecerli email ve password gir
    Then Gorunur durumdaysa "SignIn" butonuna tikla
    Then "LoginSuccess" gorunurlugunu test et
    Then Kullanici profiline yonlendirildigini dogrula
    Then Sayfada "My Profile" gorunurlugunu test et
    Then Sayfada "Wishlist" gorunurlugunu test et
    Then Sayfada "Manage Address" gorunurlugunu test et
    Then Sayfada "Change Password" gorunurlugunu test et
    Then Sayfada "Logout" gorunurlugunu test et

