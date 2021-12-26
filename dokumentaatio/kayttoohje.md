# Käyttöohje

### Konfiguraatiot
Sovellus tarvitsee toimiakseen:
- *jar-tiedoston*
- *config.properties* -tiedoston, jossa määritellään ohjelman käyttämät tietokannat
- tietokantatiedostot: *users.db*, *messages.db*
- testauksen tietokantatiedostot: *usersTest.db*, *messagesTest.db*

  - Kyseisten tiedostojen tulisi olla sijoitettuna ohjelman käynnistyshakemistoon. (Siis hakemistoon, josta löytyy muun muassa hakemisto src).
  
### Ohjelman käynnistys

- Ohjelman käynnistys tapahtuu komennolla: *java -jar todoapp.jar*

### Sovelluksen käyttö

1) Sisäänkirjautuminen / Uuden käyttäjän luonti
  - Aloitusnäkymässä käyttäjä voi kirjautua sisään tai siirtyä luomaan uuden käyttäjän.
  
2) Sisäänkirjautuneen valikko
  - Tämä on sovelluksen ydinnäkymä.
  - Käyttäjä voi valita pelien pelaamisen, pelihistorian tarkastelemisen ja sosiaalisen viestisovelluksen välillä tai kirjautua ulos.
  
3) Pelinäkymät
  - Pelinäkymiä on kaksi:
    - 3x3 Ristinolla
    - Neljän suora
  - Pelinäkymiin siirrytään sisäänkirjautuneen näkymistä vastaavan pelin nimeä vastaavan napin painalluksella.
  
4) Vaikeustason ja aloitusvuoron valinnan näkymät
  - Siirtyminen sisäänkirjautuneen näkymastä pelinäkymään tapahtuu näiden näkymien kautta.
  - Pelaaja voi valita kolmen vaikeustason: 'easy', 'medium' ja 'hard' välillä.
  - Pelaaja voi valita 'X' ja aloittavansa tai 'O' ja tietokoneen aloittavan.
  
5) Pelitilastot-näkymä
  - Siirryttävissä vastaavan napin painalluksella sisäänkirjautuneen näkymastä.
  - Käyttäja voi tarkastella kokonaisvoittoja ja -tappioitaan.
  
6) Sosiaalinen ja viestinäkymä
  - Siirryttävissä vastaavan napin painalluksella sisäänkirjautuneen näkymastä.
  - Pelaaja voi jättää viestejä ja lukea muiden pelaajien kirjoittamia viestejä.
  
7) Uloskirjautuminen
  - Käyttäja voi kirjautua ulos.
  - Käyttäjän pelihistoria ja viestihistoria säilyvät.
