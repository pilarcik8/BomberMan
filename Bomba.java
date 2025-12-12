import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
/**
 * Zaoberá sa: vytvorením bomby, animáciou (zmenou obrázkov) bomby a výbuchom bomby (vznik triedy Hviezda vybuchu)
 */
public class Bomba {
    private Obrazok bomba;
    private Manazer manazer;
    private HviezdaVybuch vybuch;
    private final int pocetObrazkovAnimacie;    
    private boolean jePolozena; 
    private boolean terazVybuch;
    private int cisloObrazku;
    private int aktPozX;
    private int aktPozY;
    /**
     * Inicializácia bomby a jej potrebních parametrov a objektov
     */
    public Bomba(int x, int y) {
        this.pocetObrazkovAnimacie = 6;
        this.aktPozX = x;
        this.aktPozY = y;
        this.jePolozena = false;
        this.cisloObrazku = 1;
        this.bomba = new Obrazok("obrazky\\bomba1.png", this.aktPozX, this.aktPozY);
        this.manazer = new Manazer();
        this.vybuch = new HviezdaVybuch(this.aktPozX, this.aktPozY);
        this.manazer.spravujObjekt(this);       
    }
    
    /**
     * Položí bombu, len ak bomba nie je už položená (viditeľná)
     */
    public void polozBombu() {
        if (!this.jePolozena) {
            this.jePolozena = true;
            this.bomba.zobraz();            
        }           
    }
    
    /**
     * Premiestni bombu, len ak bomba nie je položená (viditeľná)
     */
    public void zmenPolohuBomby(int x, int y) {
        if (!this.jePolozena) {
            this.bomba.zmenPolohu(x, y);
            this.aktPozX = x;
            this.aktPozY = y;
        }
    }   
    
    /**
     * Je to časovač
     * Ak je bomba položená, každú 1 sekundu zmeny obrázok, podľa čísla v mene obrázku ("obrazky\\bomba1.png" až "obrazky\\bomba6.png"
     * Keď prejde všetkými obrázkami vybuchne
     * terazVybuch - je true iba v chvíli keď hráč má stratiť život v okolí bomby (1 sekunda)
     */
    public void animaciaBomby() { 
        this.vybuch.skryVybuch();
        this.terazVybuch = false;
        if (this.jePolozena) {
            this.cisloObrazku++; 
        }    
        if (this.cisloObrazku > this.pocetObrazkovAnimacie) {
            this.jePolozena = false;
            this.cisloObrazku = 1; 
            this.terazVybuch = true;
            this.bomba.skry();
            this.vybuch.zmenPolohu(this.aktPozX, this.aktPozY);
            this.vybuch.zobrazVybuch();
        }
        this.bomba.zmenObrazok("obrazky\\bomba" + this.cisloObrazku + ".png");
    }
    
    /**
     * Getter pre y pozíciu bomby
     */
    public int getYBomby() {
        return this.aktPozY;
    }
    
    /**
     * Getter pre x pozíciu bomby
     */
    public int getXBomby() {
        return this.aktPozX;
    }
    
    /**
     * Getter stavu bomby (či je položená)
     */
    public boolean getStavBomby() {
        return this.jePolozena;
    }
    
    /**
     * Getter, ktorý posieľa hodnotu true len v tej chvíli, keď bomba práve vybuchla (1 sekunda) - trieda Hra to potrebuje vedieť
     */
    public boolean getBum() {
        return this.terazVybuch;
    }
    
    /**
     * Odstráni bombu a výbuch z hry
     */
    public void odstranObjekBomba() {
        this.bomba.skry();
        this.vybuch.skryVybuch();
    }
}
