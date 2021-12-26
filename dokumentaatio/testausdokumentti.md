# Testausdokumentti

- Toteutettu automatisoitu yksikkötestaus JUnit-testein.

### Sovelluslogiikan ja DAO-luokkien testit

- Testattu jokaista testattavissa olevaa tictactoe.domainin ja tictactoe.dao:n luokkaa ja metodia.

- Näin ollen sekä sovelluslogiikan koostavat luokat, että DAO-luokat ovat kattavasti testattu.

- Testattu luokkia niin yksikkötestein kuin suurempia kokonaisuuksia integraatiotestein.

- Tietokantojen testaamiseen käytetty erillisiä testitietokantoja usersTest.db ja messagesTest.db, jotka injektoitu config.properties tiedostossa olevien osoitteiden mukaan.

- Erityisesti TictactoeServicen toimintaa testattu laajasti.

### Testauskattavuus

- Testauksen rivikattavuus on 95% ja haaraumakattavuus 92%.

- Tämä johtuu lähes yksinomaan tietokannan lukuun liittyvien poikkeuksien käsittelyyn liittyvistä koodinpätkistä, joita ei poikkeuksien puutteessa käydä läpi.

Testattu luokkia niin yksikkötestein kuin suurempia kokonaisuuksia integraatiotestein.

Tietokantojen testaamiseen käytetty erillisiä testitietokantoja usersTest.db ja messagesTest.db, jotka injektoitu config.properties tiedostossa olevien osoitteiden mukaan.

Erityisesti TictactoeServicen toimintaa testattu laajasti.
