Feature: US_001 Kullanici olarak registration yapmaliyim

  @smoke
  Scenario: TC_001 Registration sayfasinda alti ozellik olmali; firstname, lastname, email, password, confirm password, sign up button
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Gorunur durumdaysa "Account" butonuna tikla
    Then Gorunur durumdaysa "SignUp" butonuna tikla
    When Register Now sayfasina gelindigi dogrula
    Then Sayfada "FirstName" gorunurlugunu test et
    Then Sayfada "LastName" gorunurlugunu test et
    Then Sayfada "Email" gorunurlugunu test et
    Then Sayfada "Password" gorunurlugunu test et
    And Sayfada "ConfirmPassword" gorunurlugunu test et

  Scenario: TC_002 5 ozellik bos birakilamaz ve girilen bilgiler  gecerli olmalidir
  (positive register)
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Gorunur durumdaysa "Account" butonuna tikla
    Then Gorunur durumdaysa "SignUp" butonuna tikla
    When Register Now sayfasina gelindigi dogrula
    Then Tum bilgileri gecerli olarak gir
    And Gorunur durumdaysa "SignUpForm" butonuna tikla
    Then Basarili kayit yapildigini test et

  @regression
  Scenario: TC_003 5 ozellik bos birakilamaz ve girilen bilgiler  gecerli olmalidir
  (negative register)
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Gorunur durumdaysa "Account" butonuna tikla
    Then Gorunur durumdaysa "SignUp" butonuna tikla
    When Register Now sayfasina gelindigi dogrula
    Then Bilgileri bos birak
    And Gorunur durumdaysa "SignUpForm" butonuna tikla
    Then Tum bilgiler icin required uyarisi verildigini dogrula
    And Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et


  Scenario: TC_004 5 ozellik bos birakilamaz ve girilen bilgiler  gecerli olmalidir
  (negative register)
    #Sitede @ işareti  kullanılmadan email girilebiliyor dolayısıyla bu senaryoda test hata verecektir.
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Gorunur durumdaysa "Account" butonuna tikla
    Then Gorunur durumdaysa "SignUp" butonuna tikla
    When Register Now sayfasina gelindigi dogrula
    Then Email adresi @ isareti kullanilmadan gir
    And Gorunur durumdaysa "SignUpForm" butonuna tikla
    And Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et


  Scenario: TC_005 5 ozellik bos birakilamaz ve girilen bilgiler  gecerli olmalidir
  (negative register)
    Given "url"`ye git
    Then Sayfaya gittigini dogrula
    And Gorunur durumdaysa "Account" butonuna tikla
    Then Gorunur durumdaysa "SignUp" butonuna tikla
    When Register Now sayfasina gelindigi dogrula
    Then Confirm password passworddan farkli gir
    And Gorunur durumdaysa "SignUpForm" butonuna tikla
    Then Password hatasi verildigi dogrulanir
    And Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et

