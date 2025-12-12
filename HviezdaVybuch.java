/**
 * Z obrázku triedy Vybuch vytvorí hviezdicu (skorej plusko)
 */
public class HviezdaVybuch {
    private Vybuch vybuchStred;
    private Vybuch vybuchHore;
    private Vybuch vybuchDole;
    private Vybuch vybuchLavo;
    private Vybuch vybuchPravo;
    
    /**
     * Inicializácia Vybuchov 
     * Stredných Výbuch je na pozícii, ktorý dostane
     * Ostatné sú posunuté o veľkosť strany vybuchu
     */
    public HviezdaVybuch(int x, int y) {
        this.vybuchStred = new Vybuch();
        this.vybuchStred.zmenPolohu(x, y);
        this.vybuchHore = new Vybuch();
        this.vybuchHore.zmenPolohu(x, y - 30);
        this.vybuchDole = new Vybuch();
        this.vybuchDole.zmenPolohu(x, y + 30);
        this.vybuchLavo = new Vybuch();
        this.vybuchLavo.zmenPolohu(x - 30, y);
        this.vybuchPravo = new Vybuch();
        this.vybuchPravo.zmenPolohu(x + 30, y); 
    }
        
    /**
     * Zobrazí hviezdicu
     */
    public void zobrazVybuch() {
        this.vybuchStred.ukaz();
        this.vybuchHore.ukaz();
        this.vybuchDole.ukaz();
        this.vybuchLavo.ukaz();
        this.vybuchPravo.ukaz();
    }
    
    /**
     * Skryje hviezdicu
     */
    public void skryVybuch() {
        this.vybuchStred.skry();
        this.vybuchHore.skry();
        this.vybuchDole.skry();
        this.vybuchLavo.skry();
        this.vybuchPravo.skry();
    }
    
    /**
     * Zmení polohu hviezdice
     */
    public void zmenPolohu(int x, int y) {
        this.vybuchStred.zmenPolohu(x, y);        
        this.vybuchHore.zmenPolohu(x, y - 30);        
        this.vybuchDole.zmenPolohu(x, y + 30);        
        this.vybuchLavo.zmenPolohu(x - 30, y);        
        this.vybuchPravo.zmenPolohu(x + 30, y);
    }       
}
