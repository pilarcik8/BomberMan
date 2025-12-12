import fri.shapesge.Obrazok;
/**
 * Obrázok, ktorého treba päť k jednej bombe
 * Predstavuje miesto, kde hráč stratí život, ak na ňom stojí
 */
public class Vybuch {
    private Obrazok vybuch;
    /**
     * Inicializácia obrázka
     */
    public Vybuch() {
        this.vybuch = new Obrazok("obrazky\\vybuch.png");        
    }
    
    /**
     * Obrázok zmení polohu
     */
    public void zmenPolohu(int x, int y) {
        this.vybuch.zmenPolohu(x, y);
    }
    
    /**
     * Obrázok sa zobrazí
     */
    public void ukaz() {
        this.vybuch.zobraz();
    }
    
    /**
     * Obrázok sa sková
     */
    public void skry() {
        this.vybuch.skry();
    }
}
