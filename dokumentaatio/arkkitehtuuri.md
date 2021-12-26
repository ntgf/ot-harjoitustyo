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
  


Ensimmäinen arkkitehtuuriluonnos:

![Arkkitehtuuriluonnos](https://user-images.githubusercontent.com/93884822/145096147-f6ad0e8c-2682-4eb5-bf38-e22615107981.jpg)

Sekvenssikaavio:
- Olemassaolevan käyttäjän sisäänkirjautuminen:

![Sekvenssikaavio, olemassaolevan käyttäjän sisäänkirjutuminen](https://user-images.githubusercontent.com/93884822/145096483-b4f9442c-15fc-466c-93d4-9e8fde8e7b20.jpg)


#Arkkitehtuurikuvaus

