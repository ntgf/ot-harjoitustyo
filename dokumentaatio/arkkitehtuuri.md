# Arkkitehtuurikuvaus

## Rakenne

- Ohjelman kerrosarkkitehtuurin toteuttavat kolme kerrosta, jotka jakautuvat pakkauksiin tictactoe.ui, tictactoe.domain ja tictactoe.dao.
- Pakkaukseen tictactoe.ui on sisällytetty sovelluksen käyttöliittymästä vastaavat luokat.
- tictactoe.domain:in luokat vastaavat sovelluslogiikasta.
- tictactoe.dao:n luokat vastaavat DAO-suunnittelumallin mukaisesti tietojen tallennuksesta.

![2](https://user-images.githubusercontent.com/93884822/147408261-a6e04760-897a-4b8d-a079-f280bf09aa43.JPG)

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

- Sovellus avautuu sisäänkirjautumisnäkymään, josta käyttäjällä on mahdollista siirtyä uuden käyttäjän luomisnäkymään ja takaisin.
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


## Toiminnallisuuksia sekvenssikaavioin

- Olemassa olevan käyttäjän sisäänkirjautuminen:

![login](https://user-images.githubusercontent.com/93884822/147415404-f40f8e2a-3db2-4308-b9cb-b3957e592b07.jpg)

- Uuden käyttäjän luominen:

![createnewuser](https://user-images.githubusercontent.com/93884822/147415409-528c790b-f6c1-44cc-add8-b14812c26c18.jpg)

- Peliruudun painaminen kesken pelin, kun tekoäly pelaa X:ää:

![playbutton](https://user-images.githubusercontent.com/93884822/147415412-dcb88426-d9d2-4dad-bf9d-4c042eafa4be.jpg)

- Pelaajan voittojen ja tappioiden haku pelitilastonäkymään:

![gamescore](https://user-images.githubusercontent.com/93884822/147415415-9fa1aeae-725b-45f1-bdd7-2ceef8f83c26.jpg)

## Huomioitavaa

- Checkstyle antaa virheilmoituksen tekoäly-luokkien ja boardChecker-luokkien koosta.
- En ole kuitenkaan pilkkonut näitä tarkoituksella pienempiin osiin, sillä mielestäni tämä rikkoisi luokkien loogisen rakenteen ja single responsibility periaatetta.
- Olen menetellyt vastaavalla tavalla myös muutaman metodin kohdalla.
