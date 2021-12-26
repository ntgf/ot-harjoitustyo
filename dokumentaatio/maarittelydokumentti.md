# Vaatimusmäärittely
--------------------
## Sovelluksen tarkoitus

Ristinolla-sovellus, jonka avulla käyttäjä voi kirjautua sovellukseen ja pelata tietokonetta vastaan ristinollaa tai neljän suoraa.

--------------------
## Käyttäjät

1) Normaalikäyttäjä

- Sovellukselle saatetaan, ainakin ohjelman kirjoittajan suuresta toiveesta, rakentaa lähitulevaisuudessa laajemmin oikeuksin varustettu ylläpitokäyttäjä.
---------------------
## Käyttöliittymä

- Sovellus koostuu monipuolisesti yhdeksästä eri näkymästä.
- Sovellus avautuu sisäänkirjautumisnäkymään, josta voi siirtyä uuden käyttäjän luomisnäkymään tai sisäänkirjautuneen näkymään.
- Sisäänkirjautuneen näkymästä voi siirtyä pelinäkymiin, tarkastelemaan pelihistoriaa tai sosiaaliseen viestinäkymään.
- Siirtyminen pelinäkymiin tapahtuu vaikeustason valinnan ja aloitusvuoron valinnasta vastaavien näkymien kautta.

## Perustoiminnallisuus

- Normaalikäyttäjiä.
- Käyttäjä pystyy luomaan itselleen tunnuksen ja kirjautumaan sovellukseen.
- Käyttäjä pystyy pelaamaan tietokonetta vastaan ristinollaa 3x3 ruudulla.
- Käyttäjä pystyy pelaamaan tietokonetta vastaan neljän suoraa 7x7 ruudulla.
- Käyttäjä voi valita kolmesta vaikeustasosta: 'easy', 'medium', 'hard'.
- Käyttäjä voi valita 'X' ja aloittavansa tai 'O' ja tietokoneen aloittavan.
- Käyttäjä voi tarkastella kokonaispelihistoriaansa.
- Käyttäjä voi julkaista viestejä ja lukea muiden käyttäjien kirjoittamia viestejä.
- Käyttäjä voi kirjautua ulos sovelluksesta.
---------------------
## Jatkotoiminnallisuusmahdollisuuksia

**Normaalikäyttäjä:**

*Kirjautuminen*
- Käyttäjä voi poistaa tunnuksensa
   - Käyttäjä voi myös valita poistetaanko tunnuksen poiston yhteydessä pelihistoria ja tilin tiedot esimerkiksi High Score -listauksista.
- Käyttäjä voi asettaa tunnukselleen salasanan.
- Käyttäjä voi vaihtaa salasanaansa.

*Pelaaminen*
- Pystyy valitsemaan ristinollalle pelikentän koon.
   
*Lisätoimintoja*
- Käyttäjä voi tarkastella pelihistoriaansa:
   - Kokonaispeliaika tai peliaika pelikenttää kohden.
   - Pelikenttä ja vaikeustaso kohtaisia tuloksia:
       - Nopeimmin voitetut pelit, esimerkiksi siirtojen lukumäärän tai käytetyn ajan suhteen.
       - Top-henkilökohtaiset tai kaikkien pelaajien pistetulokset kutakin vaikeustasoa tai kenttää kohden.
- Viestikenttä:
   - Käyttäjä voi poistaa aiemmin kirjoittamiaan viestejä.
   - Viestikentän toimintaa laajentaa, esim. interaktiotoiminnoin.

**Ylläpitokäyttäjä:**

- Pystyy tarkastelemaan tilastoja sovelluksen käytöstä.
- Pystyy poistamaan käyttäjän tunnuksen.  
- Voi rajoittaa käyttäjän kirjautumista palveluun.
- Pystyy siistimään tuloslistoja sekä viestikenttiä.
    
