/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chandler
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti rahatonkortti;
    Maksukortti rahallinenkortti;
    
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        rahatonkortti = new Maksukortti(100);
        rahallinenkortti = new Maksukortti(500);
    }
    
    @Test
    public void luodunKassapaatteenRahamaaraOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void luodunKassapaatteenMyydytMaukkaatLounaatOikein() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void luodunKassapaatteenMyydytEdullisetLounaatOikein() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    
    //Edullisen lounaan osto, kun rahamaara riittava:
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaKassaaOikeinKunMaksuRiittava() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKateisostonVaihtorahaOikeinKunMaksuRiittava() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
    }
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaMyytyjaEdullisiaLounaitaOikeinKunMaksuRiittava() {
        kassapaate.syoEdullisesti(240);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    
    //Edullisen lounaan osto, kun rahamaara ei riittava:
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaKassaaOikeinKunMaksuEiRiittava() {
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKateisostonVaihtorahaOikeinKunMaksuEiRiittava() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
    }
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaMyytyjaEdullisiaLounaitaOikeinKunMaksuEiRiittava() {
        kassapaate.syoEdullisesti(200);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    
    
    //Maukkaan lounaan osto, kun rahamaara riittava:
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaKassaaOikeinKunMaksuRiittava() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKateisostonVaihtorahaOikeinKunMaksuRiittava() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaMyytyjaMaukkaitaLounaitaOikeinKunMaksuRiittava() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    //Maukkaan lounaan osto, kun rahamaara ei riittava:
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaKassaaOikeinKunMaksuEiRiittava() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKateisostonVaihtorahaOikeinKunMaksuEiRiittava() {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
    }
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaMyytyjaMaukkaitaLounaitaOikeinKunMaksuEiRiittava() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    //Edullisen lounaan korttiosto, kun rahamaara riittava:
    
    @Test
    public void josKortillaRahaaEdullisenLounaanVeloitusOikein() {
        kassapaate.syoEdullisesti(rahallinenkortti);
        assertEquals(260, rahallinenkortti.saldo());
    }
    
    @Test
    public void josKortillaRahaaEdullisenLounaanOstoPalauttaaTrue() {
        assertEquals(true, kassapaate.syoEdullisesti(rahallinenkortti));
    }
    
    @Test
    public void josKortillaRahaaEdullistenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(rahallinenkortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    
    //Edullisen lounaan korttiosto, kun rahamaara ei riittava:
    
    @Test
    public void josKortillaEiRahaaEdullisenLounaanVeloitusOikein() {
        kassapaate.syoEdullisesti(rahatonkortti);
        assertEquals(100, rahatonkortti.saldo());
    }
    
    @Test
    public void josKortillaEiRahaaEdullisenLounaanOstoPalauttaaFalse() {
        assertEquals(false, kassapaate.syoEdullisesti(rahatonkortti));
    }
    
    @Test
    public void josKortillaEiRahaaEdullistenLounaidenMaaraEiKasva() {
        kassapaate.syoEdullisesti(rahatonkortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaEdullisenLounaanOstoEiKasvataKassanRahamaaraa() {
        kassapaate.syoEdullisesti(rahatonkortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    
    //Maukkaan lounaan korttiosto, kun rahamaara riittava:
    
    @Test
    public void josKortillaRahaaMaukkaanLounaanVeloitusOikein() {
        kassapaate.syoMaukkaasti(rahallinenkortti);
        assertEquals(100, rahallinenkortti.saldo());
    }
    
    @Test
    public void josKortillaRahaaMaukkaanLounaanOstoPalauttaaTrue() {
        assertEquals(true, kassapaate.syoMaukkaasti(rahallinenkortti));
    }
    
    @Test
    public void josKortillaRahaaMaukkaidenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(rahallinenkortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    //Maukkaan lounaan korttiosto, kun rahamaara ei riittava:
    
    @Test
    public void josKortillaEiRahaaMaukkaanLounaanVeloitusOikein() {
        kassapaate.syoMaukkaasti(rahatonkortti);
        assertEquals(100, rahatonkortti.saldo());
    }
    
    @Test
    public void josKortillaEiRahaaMaukkaanLounaanOstoPalauttaaFalse() {
        assertEquals(false, kassapaate.syoMaukkaasti(rahatonkortti));
    }
    
    @Test
    public void josKortillaEiRahaaMaukkaidenLounaidenMaaraEiKasva() {
        kassapaate.syoMaukkaasti(rahatonkortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaMaukkaanLounaanOstoEiKasvataKassanRahamaaraa() {
        kassapaate.syoMaukkaasti(rahatonkortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    
    //kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva rahamäärä kasvaa ladatulla summalla:
    
    @Test
    public void kortillePositiivinenSummaLadattaessaKortinSaldoMuuttuuOikein() {
        kassapaate.lataaRahaaKortille(rahatonkortti, 100);
        assertEquals(200, rahatonkortti.saldo());
    }
    
    @Test
    public void kortillePositiivinenSummaLadattaessaKassanRahamaaraMuuttuuOikein() {
        kassapaate.lataaRahaaKortille(rahatonkortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleNegatiivinenSummaLadattaessaKortinSaldoMuuttuuOikein() {
        kassapaate.lataaRahaaKortille(rahatonkortti, -100);
        assertEquals(100, rahatonkortti.saldo());
    }
    
    @Test
    public void kortilleNegatiivinenSummaLadattaessaKassanRahamaaraMuuttuuOikein() {
        kassapaate.lataaRahaaKortille(rahatonkortti, -100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
}
