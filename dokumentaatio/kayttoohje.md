# Käyttöohje

### Konfiguraatiot
Sovellus tarvitsee toimiakseen:
- *jar-tiedoston*
- *config.properties* -tiedoston, jossa määritellään ohjelman käyttämät tietokannat
- tietokantatiedostot: *users.db*, *messages.db*
- testauksen tietokantatiedostot: *usersTest.db*, *messagesTest.db*

  - Kyseisten tiedostojen tulisi olla sijoitettuna ohjelman käynnistyshakemistoon. (Siis hakemistoon, josta löytyy muun muassa hakemisto src).
  
### Ohjelman käynnistys

Komentorivikomennot:

- jar-tiedoston luonti: “mvn package"
  - Luonti edellyttää testien onnistumista ja tiedostoja config.properties, usersTest.db, messagesTest.db
  - Luotu jar-tiedosto löytyy kansiosta target nimellä 'Tictactoe-1.0-SNAPSHOT.jar'
- jar-tiedoston voi ajaa komennolla java -jar Tictactoe-1.0-SNAPSHOT.jar
  - jar-tiedoston ajaminen edellyttää tiedostojen config.properties, users.db ja messages.db löytymistä jar-tiedoston kanssa samasta hakemistosta.
- Testaus: “mvn test”
- Testikattavuusraportin luonti: “mvn jacoco:report”
  - Löytyy avaamalla tiedoston target/site/jacoco/index.html selaimessa.
- Checkstyle-raportti: “mvn jxr:jxr checkstyle:checkstyle”
  - Löytyy avaamalla tiedoston target/site/checkstyle.html selaimessa.
- JavaDocin luonti: "mvn javadoc:javadoc"
  - Löytyy hakemistosta target/site/apidocs/

### Sovelluksen käyttö

1) Sisäänkirjautuminen / uuden käyttäjän luonti
    - Aloitusnäkymässä käyttäjä voi kirjautua sisään tai siirtyä luomaan uuden käyttäjän.
  
2) Sisäänkirjautuneen valikko
    - Tämä on sovelluksen ydinnäkymä.
    - Käyttäjä voi valita pelien pelaamisen, pelihistorian tarkastelemisen ja sosiaalisen viestisovelluksen välillä tai kirjautua ulos.
  
3) Pelinäkymät
    - Pelinäkymiä on kaksi:
      - 3x3 Ristinolla
      - Neljän suora
    - Pelinäkymiin siirrytään sisäänkirjautuneen näkymästä pelin nimeä vastaavan napin painalluksella.
    - Pelaaminen tapahtuu pelin mukaisesti sopivien ruutujen painalluksella.
  
4) Vaikeustason ja aloitusvuoron valinnan näkymät
    - Siirtyminen sisäänkirjautuneen näkymastä pelinäkymään tapahtuu näiden näkymien kautta.
    - Pelaaja voi valita kolmen vaikeustason: 'easy', 'medium' ja 'hard' välillä.
    - Pelaaja voi valita 'X' ja aloittavansa tai 'O' ja tietokoneen aloittavan.
  
5) Pelitilastot-näkymä
    - Siirryttävissä vastaavan napin painalluksella sisäänkirjautuneen näkymästä.
    - Käyttäjä voi tarkastella kokonaisvoittoja ja -tappioitaan.
  
6) Sosiaalinen ja viestinäkymä
    - Siirryttävissä vastaavan napin painalluksella sisäänkirjautuneen näkymastä.
    - Pelaaja voi jättää viestejä ja lukea muiden pelaajien kirjoittamia viestejä.
  
7) Uloskirjautuminen
    - Käyttäjä voi kirjautua ulos.
    - Käyttäjän pelihistoria ja viestihistoria säilyvät.
