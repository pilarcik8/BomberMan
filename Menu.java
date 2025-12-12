import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
/**
 * Túto trietu tvorí menu pre výber mapy
 * Mapu vybereme myšou ľavím tlačidlom
 * Vždy, keď hra skončí sa vypíše kto vyhral, počet výhier a dá sa vybrať dalšia mapa
 */
public class Menu {
    private final Obrazok level;
    private final Obrazok bielaPlocha;
    private final Obrazok menu;
    private final Manazer manazer;
    private Hra hra;
    private StavHry stav;
    private boolean hraJeZapnuta = false;
    private int pocetVyhierH1;
    private int pocetVyhierH2;
    private BlokTextu vypisPoctuVyhier;
    private String sprava;
    /**
     * Inicializácia a zobrazenie menu
     */
    public Menu() {
        this.vypisPoctuVyhier = new BlokTextu("inicializacia", 230, 250);
        this.vypisPoctuVyhier.zmenFont("Times New Roman", StylFontu.BOLD, 30);
        this.vypisPoctuVyhier.zmenFarbu("white");
        this.vypisPoctuVyhier.zobraz();
        this.pocetVyhierH1 = 0;
        this.pocetVyhierH2 = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.menu = new Obrazok("obrazky\\menu.png", 0, 0);
        this.menu.zobraz();
        this.level = new Obrazok("obrazky\\level.png", 0, 200);
        this.level.zobraz();
        this.bielaPlocha = new Obrazok("obrazky\\bielaPlocha.png", 0, 0);
    }
        
    /**
     * Výber mapy, podľa polohy stiknutia ľavým tlačidlom myši
     */
    public void vyberMapu(int x, int y) {
        if (x > 10 && x < 210 && !this.hraJeZapnuta) {
            if (y > 200 && y < 230) {
                this.vytvorMapu(1);
            }
            if (y > 240 && y < 270) {
                this.vytvorMapu(2);
            }
            if (y > 280 && y < 310) {
                this.vytvorMapu(3);
            }
        }
    }
    
    /**
     * Vytvorí zadanú mapu (Hra), skryje menu
     */
    public void vytvorMapu(int cislo) {
        this.menu.skry();
        this.level.skry();
        this.bielaPlocha.zobraz();
        switch (cislo) {
            case 1:
                this.hra = new Hra(1);
                break;
                
            case 2:
                this.hra = new Hra(2);
                break;  
             
            case 3:
                this.hra = new Hra(3);
                break;  
        }
        this.hraJeZapnuta = true;
    }
    
    /**
     * Je to časovač
     * Kontroluje či hra neskončila
     * Ak áno započíta výhru výhercovi, vypíše počet výhier oboch hráčov a zmaze objekti hry
     * Nechá nás vybrať daľšiu mapu
     */
    public void znovozapinacHry() {
        if (this.hraJeZapnuta) {
            this.stav = this.hra.getStavHry();
            this.vypisPoctuVyhier.skry();
            if (this.stav == StavHry.REMIZA || this.stav == StavHry.VYHRA_HRAC1 || this.stav == StavHry.VYHRA_HRAC2) {
                if (this.stav == StavHry.VYHRA_HRAC1) {
                    this.pocetVyhierH1++;
                }
                if (this.stav == StavHry.VYHRA_HRAC2) {
                    this.pocetVyhierH2++;
                }    
                this.bielaPlocha.skry();
                this.level.zobraz();
                this.hraJeZapnuta = false;
                this.sprava = "Počet výhier HRÁČA 1: " + this.pocetVyhierH1 + "\nPočet výhier HRÁČA 2: " + this.pocetVyhierH2;
                this.vypisPoctuVyhier.zmenText(this.sprava);
                this.vypisPoctuVyhier.zobraz();
                this.hra.odstranObjekti();
            }
        }
    }
}
