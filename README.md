# TicTacToeApp
Sovelluksen avulla käyttäjän on mahdollista luoda tunnus, kirjautua järjestelmään ja pelata ristinollaa tietokonetta vastaan.

## Dokumentaatio
[Työaikakirjanpito](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Arkkitehtuuriluonnos1](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Arkkitehtuurikuvaus(1)](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/Arkkitehtuurikuvaus1.jpg)

[Arkkitehtuurikuvaus(2)](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/Arkkitehtuurikuvaus2.jpg)

[Arkkitehtuurikuvaus(3)](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/Arkkitehtuurikuvaus3.jpg)

[Kayttoohje](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/Kayttoohje.jpg)

## Releaset

### Viikko 5

[Release](https://github.com/ntgf/ot-harjoitustyo/releases/tag/viikko5)

Ohjelman ja testien toiminta edellyttää tiedostoja:

- users.txt (tulisi generoitua automaattisesti)
- usersTest.txt testien toimimista varten
- config.properties alkukonfiguraatioita varten.

Kyseisten tiedostojen tulisi olla jar-tiedoston kanssa samassa hakemistossa.

## Komentorivikomennot

- Testaus: “mvn test”
- Testikattavuusraportin luonti: “mvn jacoco:report”
  - Löytyy avaamalla tiedoston target/site/jacoco/index.html selaimessa.
- jar-tiedoston luonti: “mvn package"
  - Edellyttää testien onnistumista ja tiedostoa usersTest.txt
- Checkstyle-raportti: “mvn jxr:jxr checkstyle:checkstyle”
  - Löytyy avaamalla tiedoston target/site/checkstyle.html selaimessa.

## Viikko 6

[Release 6](https://github.com/ntgf/ot-harjoitustyo/releases/tag/Viikko6.1)

Ohjelma kansiossa otm-todoapp-master22

[Huomioita](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/viikko6.txt)
