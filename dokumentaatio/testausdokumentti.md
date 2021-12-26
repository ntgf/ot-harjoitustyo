# Testausdokumentti

- Toteutettu automatisoitu yksikkötestaus JUnit-testein.

### Sovelluslogiikan ja DAO-luokkien testit

- Testattu jokaista testattavissa olevaa tictactoe.domainin ja tictactoe.dao:n luokkaa ja metodia.

- Näin ollen sekä sovelluslogiikan koostavat luokat, että DAO-luokat ovat kattavasti testattu.

- Testattu luokkia niin yksikkötestein kuin suurempia kokonaisuuksia integraatiotestein.

- Tietokantojen testaamiseen käytetty erillisiä testitietokantoja usersTest.db ja messagesTest.db, jotka injektoitu config.properties tiedostossa olevien osoitteiden mukaan.

- Erityisesti TictactoeServicen toimintaa testattu laajasti.

### Testauskattavuus

<img width="982" alt="3" src="https://user-images.githubusercontent.com/93884822/147409906-932356bb-ea38-4a06-b6cf-c945a82d3c0e.png">

- Testauksen rivikattavuus on 95% ja haaraumakattavuus 92%.

- Tämä johtuu lähes yksinomaan tietokannan lukuun liittyvien poikkeuksien käsittelyyn liittyvistä koodinpätkistä, joita ei poikkeuksien puutteessa käydä läpi.

### Järjestelmätestit

- Testattu järjestelmää laajasti OSX- ja Linux-ympäristöissä.
- Suoritettu testejä manuaalisesti.
- Sovellus toimii käyttöohjeissa määriteltyjen tiedostojen kanssa.
- Sovellus ei ole kertaakaan kaatunut testauksen aikana, eikä tulostanut tai antanut virheilmoituksia.
- Sovellus ei ole muutenkaan jäätynyt tai bugaillut järjestelmätasolla.
- Käyty kaikki toiminnallisuudet läpi.
- Testattu myös käyttäjän antamia virhesyötteitä, joihin on laajasti varauduttu.

### Sovellukseen jääneet puutteet

- Tällä hetkellä vaikeustasoilla 'easy' ja 'medium' ristinolla ai jättää joskus harvoin vuoron välistä.
  - Tämä johtuu tekoälyn koodista, joka alunperin kehitetty mahdollisimman haastavaksi ja vaikeaa vaikeustasoa varten.
  - Koodissa ei ole varauduttu siten "niin huonoihin" tilanteisiin, mihin helpommilla vaikeustasoilla saatetaan päästä, eli että tekoälyllä ei ole enää muita kuin huonoja vaihtoehtoja jäljellä.
  - Tämä olisi helposti korjattavissa (mikäli aika riittää).

- Lisäksi neljän suoran tekoälyn toiminta on suurimman osan aikaa kelvollista, mutta taipumuksena aloituksessa antaa vaihtelevalla todennäköisyydellä pelaajalle suoraan kolmen vapaan suoran aloituksessa ja voiton.
- Tämäkin lienisi ajan kanssa korjattavissa.

- Koska kurssin tarkoituksena kuin ei myöskään arvosteluperusteissa arvioida tekoälyä, eivät nämä liene ongelmia kurssin laajuuden ja määrittelyn puitteissa.
