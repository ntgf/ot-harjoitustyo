# Arkkitehtuurikuvaus

## Rakenne

- Ohjelman kerrosarkkitehtuurin toteuttavat kolme kerrosta, jotka jakautuvat pakkauksiin tictactoe.ui, tictactoe.domain ja tictactoe.dao.
- Pakkaukseen tictactoe.ui on sisällytetty sovelluksen käyttöliittymästä vastaavat luokat.
- tictactoe.domain:in luokat vastaavat sovelluslogiikasta.
- tictactoe.dao:n luokat vastaavat DAO-suunnittelumallin mukaisesti tietojen tallennuksesta.

## Käyttöliittymä

- Käyttöliittymä koostuu yhdeksästä näkymästä, jotka jakautuvat seuraaviin:
  - I) Kirjautumiseen liittyvät näkymät:
    - 1) Sisäänkirjautumisnäkymä
    - 2) Uuden käyttäjän luomisnäkymä
  - II) Sisäänkirjautumisen näkymä
    - 3) Sisäänkirjautuneen näkymä
  - III) Pelaamiseen liittyvät näkymät
    - 4) Vaikeustason valinta -näkymä
    - 5) Aloitusvuoron ja X:n tai O:n valintanäkymä
    - 6) 3x3 peliruudukon ristinollanäkymä
    - 7) Neljän suoran pelinäkymä
  - IV) Pelitilastot ja statistiikka
    - 8) Pelitilastojen tarkastelunäkymä
  - V) Ohjelman sosiaaliset toiminnot
    - 9) Käyttäjien viestialueen näkymä

- Sovellus avautuu sisäänkirjautumisnäkymään, josta käyttäjällä on mahdollista siirtyä uuden käyttäjän luominäkymään ja takaisin.
- Käyttöliittymän näkymien keskiössä on sisäänkirjautuneen näkymä, josta pelaajan on mahdollista siirtyä pelinäkymiin, pelitilastojen tarkasteluun tai viestinäkymään kuin myös kirjautua ulos.
- Pelinäkymiin siirrytään sisäänkirjautuneen näkymästä peliä vastaavan napin painalluksella.
- Pelivalinnan jälkeen siirrytään vaikeustason valintaan ja aloitusvuoron valintaan liittyvien näkymien jälkeen valittua peliä vastaavaan näkymään.
- Pelaaja voi halutessaan luovuttaa ja siirtyä välittömästi takaisin sisäänkirjautuneen näkymään, tai pelata pelin loppuun ja valita tämän jälkeen uuden pelin tai poistumisen sisäänkirjautuneen näkymään väliltä.
- Uuden pelin valinta ohjaa vaikeustason ja aloitusvuoron valinnan jälkeen uuteen peliin.
  
## Sovelluslogiikka

- Sovelluksessa on valittavana kaksi peliä, joissa käyttäjä voi pelata tietokonetta vastaan.
  - 1) 3x3 ristinolla
  - 2) neljän suora
- Kummallakin pelillä on oma tekoälynsä TictactoeAI ja ConnectFourAI.
  - 1) TictactoeAI on alpha-beta pruningiin perustuva tekoäly, joka käy tehokkaasti koko pelipuuta läpi luoden vahvan tekoälyn.
  - 2) ConnectFourAI hyödyntää pelikentän kokoon nähden rajallisemman laskentavoiman seurauksena syvyysrajoitettua alpha-beta pruningia, jossa pelitilanteen arvo ei ole absoluuttinen vaan perustuu arvioon laudan arvosta.
- Kumpikin tekoälyistä hyödyntää erillistä pelilaudan pelitilanteita arvioivaa luokkaa:
  - 1) TictactoeAI -> BoardChecker
  - 2) ConnectFourAI -> ConnectFourBoardChecker
- Johtuen tekoälyjen eri toimintalogiikasta ja tarpeista on myös boardcheckereiden toiminta näitä vastaten erilaista.
- Pelin tekoälyt vastaavat tietokoneen tekemistä siirroista ja pelilautojen käsittely on laskennan tehostamiseksi koodattu niihin String-tyyppisenä.


- TictactoeService vastaa toiminnallaan kaikista sovelluksen käyttäjiin liittyvästä toiminnallisuudesta, kuten uuden käyttäjän luontiin, sisään- ja uloskirjautumiseen sekä käyttäjien viestitoimintaan liittyvästä toiminnallisuudesta.
- TictactoeService hyödyntää DAO-suunnittelumallin mukaisesti luokkia FileUserDao ja FileMessageDao rajapintojen UserDao ja MessageDao välityksellä.
- FileUserDao ja FileMessageDao -luokkien hyödyntämät tietokannat injektoidaan niiden konstruktorikutsujen yhteydessä erillisessä config.properties tiedostossa määritellyistä osoitteista.

- Viimeisenä sovelluslogiikan luokkana User vastaa käyttäjien ilmentymistä.


## Tietojen talletus

- Käyttäjien tiedot on talletettu tietokantaan users.db
  - Muodossa: id INTEGER PRIMARY KEY, username TEXT, name TEXT, wins INTEGER, losses INTEGER
- Viestien tiedot on talletettu tietokantaan messages.db
  - Muodossa: id INTEGER PRIMARY KEY, username TEXT, message TEXT, time TEXT
- Tietokantoja hyödynnetään sovelluksessa niin tiedonlukuun, kirjoittamiseen, tiedon etsintään kuin päivittämiseen.
- Tietokannat on injektoitu luokille FileUserDao ja FileMessageDao konstruktorikutsujen yhteydessä.


Ensimmäinen arkkitehtuuriluonnos:

![Arkkitehtuuriluonnos](https://user-images.githubusercontent.com/93884822/145096147-f6ad0e8c-2682-4eb5-bf38-e22615107981.jpg)

Sekvenssikaavio:
- Olemassaolevan käyttäjän sisäänkirjautuminen:

![Sekvenssikaavio, olemassaolevan käyttäjän sisäänkirjutuminen](https://user-images.githubusercontent.com/93884822/145096483-b4f9442c-15fc-466c-93d4-9e8fde8e7b20.jpg)


#Arkkitehtuurikuvaus

