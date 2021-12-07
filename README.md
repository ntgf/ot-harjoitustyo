# TicTacToeApp
Sovelluksen avulla käyttäjän on mahdollista luoda tunnus, kirjautua järjestelmään ja pelata ristinollaa tietokonetta vastaan.

## Dokumentaatio
[Työaikakirjanpito](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Arkkitehtuuri](https://github.com/ntgf/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

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
- Checkstyle-raportti: “mvn jar:jxr checkstyle:checkstyle”
  - Löytyy avaamalla tiedoston target/site/checkstyle.html selaimessa.
