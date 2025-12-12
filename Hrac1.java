import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
/**
 * Je to postava, ktorú ovládame
 * Môžme s ňou hýbať horizentálne a vertikálne ale nie šikmo
 * Pohyb je animovaný - animácia pri pohybu alebo ak narazí do steny
 * Dokáže položiť bomby
 * Má tri bomby, druhá a tretia musia byť inicializované inak sa nedajú položiť
 */
public class Hrac1 {    
    private Obrazok hrac;
    private Manazer manazer;
    private SrdceIkona srdcia;
    private BombaIkona bombaIkona;
    private Bomba bomba1;
    private Bomba bomba2;
    private Bomba bomba3;
    private final int pocetObrazkovAnimacie = 2;
    private final int krok = 30;
    private final int odchylkaYHracBomba = 30;
    private int pocetZivotou;
    private int pozX;
    private int pozY;
    private int cisloAnimacie;
    private boolean pohybVodorovne; 
    private boolean pohybHorizontalne;
    private int pocetBomb = 1;
    private boolean mozeIstVlavo;
    private boolean mozeIstVpravo;
    private boolean mozeIstHore;
    private boolean mozeIstDole;
    
    /**
     * Inicializácia hráča
     * Je viditeľný
     * Na začiatku má 1 bombu a 3 životy - to aj ukazujú ikony bombaIkona a srdcia
     */
    public Hrac1(int x, int y) {
        this.pozY = y;
        this.pozX = x;
        this.cisloAnimacie = 1;
        this.pohybHorizontalne = false;
        this.pohybVodorovne = false;
        this.pocetZivotou = 3;        
        this.pocetBomb = 1;
        this.mozeIstVlavo = true;
        this.mozeIstVpravo = true;
        this.mozeIstHore = true;
        this.mozeIstDole = true;
        this.bombaIkona = new BombaIkona(50);
        this.bomba1 = new Bomba(this.pozX, this.pozY); 
        this.hrac = new Obrazok("obrazky\\hracDoleStoj.png", x, y);  
        this.manazer = new Manazer();
        this.srdcia = new SrdceIkona(125);
        this.manazer.spravujObjekt(this);
        this.hrac.zobraz();
    }
    
    /**
     * Posunie hráča o 1 krok dole, po pustení (released) tlačidla šípka dole
     * Pošle správu s menom obrázka metóde animácia (bez číslice a .png)
     * Podmienka: !this.pohybVodorovne zabranuje charakteru ísť šikmo
     * Podmienka: this.mozeIstDole zakazuje hráčovi Ísť do blokov a bomb (naráža)
     * Ak hráč narazí do steni, nastane else
     */
    public void posunDole() {
        if (!this.pohybVodorovne && this.mozeIstDole) {
            this.pohybHorizontalne = true;
            this.pohybVodorovne = false;
            this.pozY += krok;
            this.hrac.zmenPolohu(this.pozX, this.pozY);
            this.animacia("hracDole");
        } else {
            this.hrac.zmenObrazok("obrazky\\hracDoleStoj.png");
        }
    }
        
    /**
     * Posunie hráča o 1 krok hore, po pustení (released) tlačidla šípka hore
     * Pošle správu s menom obrázka metóde animácia (bez číslice a .png)
     * Podmienka: !this.pohybVodorovne zabranuje charakteru ísť šikmo
     * Podmienka: this.mozeIstHore zakazuje hráčovi Ísť do blokov a bomb (naráža)
     * Ak hráč narazí do steni, nastane else
     */
    public void posunHore() {
        if (!this.pohybVodorovne && this.mozeIstHore) {
            this.pohybHorizontalne = true;
            this.pohybVodorovne = false;
            this.pozY -= krok;
            this.hrac.zmenPolohu(this.pozX, this.pozY);
            this.animacia("hracHore");
        } else {
            this.hrac.zmenObrazok("obrazky\\hracHoreStoj.png");
        }
    }
    
    /**
     * Posunie hráča o 1 krok vpravo, po pustení (released) tlačidla šípka vpravo
     * Pošle správu s menom obrázka metóde animácia (bez číslice a .png)
     * Podmienka: !this.pohybHorizontalne zabranuje charakteru ísť šikmo
     * Podmienka: this.mozeIstVpravo zakazuje hráčovi Ísť do blokov a bomb (naráža)
     * Ak hráč narazí do steni, nastane else
     */
    public void posunVpravo() {
        if (!this.pohybHorizontalne && this.mozeIstVpravo) {
            this.pohybVodorovne = true;
            this.pohybHorizontalne = false;
            this.pozX += krok;
            this.hrac.zmenPolohu(this.pozX, this.pozY);
            this.animacia("hracVpravo");  
        } else {
            this.hrac.zmenObrazok("obrazky\\hracVpravoStoj.png");
        }
    }
    
    /**
     * Posunie hráča o 1 krok vlavo, po pustení (released) tlačidla šípka vľavo
     * Pošle správu s menom obrázka metóde animácia (bez číslice a .png)
     * Podmienka: !this.pohybHorizontalne zabranuje charakteru ísť šikmo
     * Podmienka: this.mozeIstVlavo zakazuje hráčovi Ísť do blokov a bomb (naráža)
     * Ak hráč narazí do steni, nastane else
     */
    public void posunVlavo() {
        if (!this.pohybHorizontalne && this.mozeIstVlavo) {
            this.pohybVodorovne = true;
            this.pohybHorizontalne = false;
            this.pozX -= krok;
            this.hrac.zmenPolohu(this.pozX, this.pozY);
            this.animacia("hracVlavo"); 
        }  else {
            this.hrac.zmenObrazok("obrazky\\hracVlavoStoj.png");
        }
    }
    
    /**
     * Položí bombu 1, 2 alebo 3 pod nohy hráča po pustení (released) tlačidla ENTER
     * Položí bombu, iba ak už nie je položená
     * Ak je bomba1 položená pokúsi sa položiť bombu 2 a ak je aj bomba2 položená pokúsi sa položiť bombu 3
     * Bomby 2 a 3 môžu byť položené, iba ak su inicializované (hráč potrebuje získať vylepšenia v hre)
     */
    public void aktivujBombu() { 
        if (!this.bomba1.getStavBomby()) {
            this.bomba1.zmenPolohuBomby(this.pozX, this.pozY + odchylkaYHracBomba);
            this.bomba1.polozBombu();
        } else {
            if (this.bomba2 != null) {
                if (!this.bomba2.getStavBomby() && this.pocetBomb >= 2) {
                    this.bomba2.zmenPolohuBomby(this.pozX, this.pozY + odchylkaYHracBomba);
                    this.bomba2.polozBombu();
                }  else {
                    if (this.bomba3 != null) {
                        if (!this.bomba3.getStavBomby() && this.pocetBomb >= 3) {
                            this.bomba3.zmenPolohuBomby(this.pozX, this.pozY + odchylkaYHracBomba);
                            this.bomba3.polozBombu();                
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Spôsobuje, že pri  pohybe viackrát za sebou do jednej strany sa menia obrázky
     * Všetky 4 strany majú 2 obrázky na pohyb, a 1 na narazenie do steny
     */
    public void animacia(String menoAnimacie) {
        this.hrac.zmenObrazok("obrazky\\" + menoAnimacie + this.cisloAnimacie + ".png");
        this.cisloAnimacie++;        
        
        if (this.cisloAnimacie > pocetObrazkovAnimacie) {
            this.cisloAnimacie = 1;        
        }
    }    
        
    /**
     * Je to časovač
     * Každých 100 milisekúnd nastavý dva booleani na false 
     * Booleani používam pre podmienky pohybu - zabranuje aby išiel charakter šikmo
     * Tvorí to krátku odozvu pri pustení tlačidla, aby hráč nemohol ísť do oboch strán naraz
     */
    public void resetPohybu() {
        this.pohybHorizontalne = false;
        this.pohybVodorovne = false;
    }
    
    /**
     * Jednoduchy getter X pozície hráča pre triedu Hra
     */
    public int getX() {
        return this.pozX;
    }
    
    /**
     * Jednoduchý getter Y pozície hráča pre triedu Hra
     */
    public int getY() {
        return this.pozY;
    }
    
    /**
     * Getter
     * Posieľa dĺžku, od hlavy hráča k jeho nohám
     * odchylkaYHracBomba je nemenná premenná
     * Bez tejto premennej hráč bude narážať do stien s Y pozíciu jeho hlavy, a bobma sa položí na Y pozíciu jeho hlavy
     */
    public int getOdchylkuVysky() {
        return this.odchylkaYHracBomba;
    }
    
    /**
     * Ziťuje, či je bomba položená a pošle informaciu ďalej
     * Parameter vyberá na ktorú bombu sa pýtame
     */
    public boolean getStavBombyHraca(int cisloBomby) { 
        if (this.pocetBomb < cisloBomby) {
            return false;    
        }
        switch (cisloBomby) { 
            case 1:
                return this.bomba1.getStavBomby();
            case 2:
                return this.bomba2.getStavBomby();
            case 3:
                return this.bomba3.getStavBomby();            
        }
        return false;
    }
    
    /**
     * Ziťuje, či bomba práve vybuchal a pošle informaciu ďalej
     * Parameter vyberá na ktorú bombu sa pýtame
     */
    public boolean getBombaVybuchla(int cisloBomby) {
        if (this.pocetBomb < cisloBomby) {
            return false;
        }    
        switch (cisloBomby) {
            case 1:
                return this.bomba1.getBum();
            case 2:
                return this.bomba2.getBum();
            case 3:
                return this.bomba3.getBum();            
        }
        return false;
    }
    
    /**
     * Pošle aktuálny počet životou hráča
     */
    public int getPocetZivotou() {
        return this.pocetZivotou;
    }
    
    /**
     * Pošle aktuálny počet bomb hráča
     */
    public int getPocetBomb() {
        return this.pocetBomb;
    }
    
    /**
     * Zväčší / zmenší počet životou a podľa toho zmení ikoni srdca
     * Maximum je 3 životy
     * Ak má hráč 0 a menej životou zomre
     */
    public void setPocetZivotou(int cislo) {
        this.pocetZivotou += cislo;
        this.srdcia.zmenSrdce(1);
        this.srdcia.zmenSrdce(2);
        this.srdcia.zmenSrdce(3);
        if (this.pocetZivotou >= 3) {
            this.pocetZivotou = 3;
        }
        for (int i = 0; i <= this.pocetZivotou; i++) {
            this.srdcia.zachovajSrdce(i);
        }
        if (this.pocetZivotou <= 0) {
            this.smrtHraca();
        }
    }
    
    /**
     * Odstrány hráča z hry
     */
    public void smrtHraca() {
        this.hrac.skry();        
    }
    
    /**
     * Je to setter
     * Nastavuje do, ktorej strany má hráč povolené/nepovolené ísť
     */
    public void setOkolieHraca(boolean povolenie, String strany) {
        switch (strany) {
            case "Hore":
                this.mozeIstHore = povolenie;
                break;
                
            case "Dole":
                this.mozeIstDole = povolenie;
                break;
                
            case "Vpravo":
                this.mozeIstVpravo = povolenie;
                break;
                
            case "Vlavo":
                this.mozeIstVlavo = povolenie;
                break;
        }
    }
    
    /**
     * Getter
     * Posieľa diaľku, ktorú hráč prejdy pri jednom pustení tľačidla
     * krok je nemenná premenná
     */
    public int getKrok() {
        return this.krok;
    }
    
    /**
     * Zväčší počet bomb hráča o 1
     * Maximálny počet bom sú 3
     * Pridá ikonu bomby na plochu
     */
    public void pridajBombu() {
        this.pocetBomb++;
        switch (this.pocetBomb) {
            case 2:
                this.bomba2 = new Bomba(this.pozX, this.pozY);
                this.bombaIkona.pridajBombu(2);
                break;
            case 3:
                this.bomba3 = new Bomba(this.pozX, this.pozY);
                this.bombaIkona.pridajBombu(3);
                break;            
            default:
                this.pocetBomb = 3;
                break;
        }     
    }
    
    /**
     * Pozastavý bomby
     * Keď sa pauza vypne (v triede Hra) bomby sa neresetujú (obrázok bomby a dĺžka času do výbuchu), ale pokračujú bez zmeny 
     */
    public void pauzaBomby(boolean aktivnaPauza) {
        if (aktivnaPauza) {
            if (this.bomba1 != null) {
                this.manazer.prestanSpravovatObjekt(this.bomba1);
            }
            if (this.bomba2 != null)  {  
                this.manazer.prestanSpravovatObjekt(this.bomba2);
            }
            if (this.bomba3 != null) {
                this.manazer.prestanSpravovatObjekt(this.bomba3);
            }
        } else {
            if (this.bomba1 != null) {
                this.manazer.spravujObjekt(this.bomba1);
            }
            if (this.bomba2 != null) {
                this.manazer.spravujObjekt(this.bomba2);
            }
            if (this.bomba3 != null) {
                this.manazer.spravujObjekt(this.bomba3);
            }
        }
    }
    
    /**
     * Zmaze objekti spojené s hráčom
     */
    public void zmazObjekti() {
        this.hrac.skry();
        this.srdcia.odstranObjektiSrdcia();
        this.bombaIkona.odstranObjektiBomby();
        if (this.bomba1 != null) {
            this.bomba1.odstranObjekBomba();
        }
        if (this.bomba2 != null) {
            this.bomba2.odstranObjekBomba();
        }
        if (this.bomba3 != null) {
            this.bomba3.odstranObjekBomba();
        }
    }
} 



