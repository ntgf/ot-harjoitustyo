# Vaatimusmäärittely
--------------------
## Sovelluksen tarkoitus

Ristinolla-sovellus, jonka avulla käyttäjä voi kirjautua sovellukseen ja pelata tietokonetta vastaan.

--------------------
## Käyttäjät

Sovellukseen on tarkoitus luoda kahdenlaisia käyttäjiä:
- [x] 1) Normaalikäyttäjä
2) Ylläpitokäyttäjä
---------------------
## Perustoiminnallisuus

- [x] Ainoastaan normaalikäyttäjä.
- [x] Käyttäjä pystyy luomaan itselleen tunnuksen ja kirjautumaan sovellukseen.
- [x] Käyttäjä pystyy pelaamaan tietokonetta vastaan 3x3 ruudulla.
- [x] Käyttäjä voi kirjautua ulos sovelluksesta.
---------------------
## Jatkotoiminnallisuus

**Normaalikäyttäjä:**

*Kirjautuminen*
- Käyttäjä voi poistaa tunnuksensa
   - Käyttäjä voi myös valita poistetaanko tunnuksen poiston yhteydessä pelihistoria ja tilin tiedot esimerkiksi High Score -listauksista.
- Käyttäjä voi asettaa tunnukselleen salasanan.
- Käyttäjä voi vaihtaa salasanaansa.

*Pelaaminen*
- Pystyy valitsemaan pelikentän koon:
   - 3x3, 5x5, iso.
- Pystyy valitsemaan vaikeustason:
   - Helppo, medium, vaikea.
   
*Lisätoimintoja*
- [x] Käyttäjä voi tarkastella pelihistoriaansa:
   - Kokonaispeliaika tai peliaika pelikenttää kohden.
   - [x] Pelikenttä ja vaikeustaso kohtaisia tuloksia:
       - [x] Voitot/tappiot.
       - Nopeimmin voitetut pelit, esimerkiksi siirtojen lukumäärän tai käytetyn ajan suhteen.
       - Top-henkilökohtaiset tai kaikkien pelaajien pistetulokset kutakin vaikeustasoa tai kenttää kohden.
- Viestikenttä:
   - Käyttäjä voi jättää kullekin pelikentälle viestejä, joita muut käyttäjät voivat lukea ja myöhemmin vastata. 
       
**Ylläpitokäyttäjä:**

- Pystyy tarkastelemaan tilastoja sovelluksen käytöstä.
- Pystyy poistamaan käyttäjän tunnuksen.  
- Voi rajoittaa käyttäjän kirjautumista palveluun.
- Pystyy siistimään tuloslistoja sekä viestikenttiä.
    
