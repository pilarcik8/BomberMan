import fri.shapesge.Obrazok;
/**
 * Trieda Mapa sa zaoberá polohou všetkých zničiteľných a nezničiteľných blokov a polohou všetkých vylepšení a či existujú (či už znikli)
 * Mapa môže mať v jednom čase 0 až 4 vylepšenia
 * Máme na výber medzi 3 mapami, každá má inak porozdelované polohyy zničiteľných a nezničiteľných blokov
 */
public class Mapa {
    private Blok[][] mapa; 
    private final int pocetBlokovStrany = 11;
    private final int stranaBloku = 30;
    private final int prvaPoziciaBloku = 0;
    private int pocetObjektov;
    private Obrazok plocha;
    private boolean vyl1Exituje;
    private boolean vyl2Exituje;
    private boolean vyl3Exituje;
    private boolean vyl4Exituje;
    private String druhVylepsenia1;
    private String druhVylepsenia2;
    private String druhVylepsenia3;
    private String druhVylepsenia4;
    private int polohaVylepseniaJednaY;
    private int polohaVylepseniaJednaX;
    private int polohaVylepseniaDvaY;
    private int polohaVylepseniaDvaX;
    private int polohaVylepseniaTriY;
    private int polohaVylepseniaTriX;
    private int polohaVylepseniaStyriY;
    private int polohaVylepseniaStyriX;
    private Vylepsenia vylepsenie1;
    private Vylepsenia vylepsenie2;
    private Vylepsenia  vylepsenie3;
    private Vylepsenia vylepsenie4;
    private int xBloku;
    private int yBloku;
    
    /**
     * Plocha je obrázok, na ktorom sa v triede Hra ukazuje počet životou a bomb hráčov
     * Podľa zadaného parametru vytvorí mapu 1, mapu 2 alebo mapu 3
     */
    public Mapa(int cisloMapy) {
        this.plocha = new Obrazok("obrazky\\plocha.png", 325, -2);
        this.plocha.zobraz();
        this.mapa = new Blok[this.pocetBlokovStrany][this.pocetBlokovStrany];
        this.pocetObjektov = 0;
        this.vylepsenie1 = new Vylepsenia(); 
        this.vylepsenie2 = new Vylepsenia();
        this.vylepsenie3 = new Vylepsenia();      
        this.vylepsenie4 = new Vylepsenia();
        
        switch (cisloMapy) {
            case 1:
                this.vytvorMapu1();    
                break;
                
            case 2:
                this.vytvorMapu2();    
                break; 
                
            default:
                this.vytvorMapu3();    
                break;   
        }       
    }
     
    /**
     * Vráti počet blokov jednej strani
     * Tým sa myslí koľko blokov je položená z hora obrazovky dole (to tvorí stranu štvorca)
     */
    public int getPocetBlokov() {
        return this.pocetBlokovStrany;
    }
    
    /**
     * Vráti x pozíciu prvého zniknutého bloku
     */
    public int getPrvuPoziciuBloku() {
        return this.prvaPoziciaBloku;
    }
    
    /**
     * Vráti počet vytvoreních blokov mapy
     */
    public int getPocetVytvorenichBlokov() {
        return this.pocetObjektov;
    }    
    
    /**
     * Vráti x-ovú polohu bloku, ktorú identifikuje podľa zadaného stĺcpa a riadku
     */
    public int getPoziciuXBloku(int riadok, int stlpec) {
        if (this.mapa[riadok][stlpec] != null) {
            return this.mapa[riadok][stlpec].getX();
        }
        return -69420;
    }
    
    /**
     * Vráti y polohu bloku, ktorú identifikuje podľa zadaného stĺcpa a riadku
     */
    public int getPoziciuYBloku(int riadok, int stlpec) {
        if (this.mapa[riadok][stlpec] != null) {
            return this.mapa[riadok][stlpec].getY();
        }
        return -69420;
    }
    
    /**
     * Pošle správu, aby zadaný blok bol zničený a na jeho mieste sa vytvorilo vylepšenie
     * Ak je blok nezničniteľný, nič sa nestane
     */
    public void znicZnicitelnyBlok(int riadok, int stlpec) {
        if (this.mapa[riadok / this.stranaBloku][stlpec / this.stranaBloku] != null) {
            this.vytvorVylepsenie(riadok, stlpec);
            this.mapa[riadok / this.stranaBloku][stlpec / this.stranaBloku].znicBlok();
        }
    }
    
    /**
     * Ak je blok zadanej polohy zničitelný, pošle správu aby na jeho polohu vzniklo vylepšenie (šanca jedna ku trom je vznikne)
     */
    public void vytvorVylepsenie(int riadok, int stlpec) {
        if (this.mapa[riadok / this.stranaBloku][stlpec / this.stranaBloku].getZnicitelnost()) {
            this.vylepsenia(stlpec, riadok);
        }
    }
    
    /**
     * Ak vzniklo vylepšenie priradí im atribúti kde môže vylepšenie uložiť polohu a existenciu (či vznikli)
     * Ak existujú 4 vylepšenia, nové vylepšenie neznikne dokým aspoň 1 vylepšenie nevezme hráč
     */
    public void vylepsenia(int stlpec, int riadok) {
        this.vyl1Exituje = this.vylepsenie1.getExistenciaVylepsenia();
        this.vyl2Exituje = this.vylepsenie2.getExistenciaVylepsenia();
        this.vyl3Exituje = this.vylepsenie3.getExistenciaVylepsenia();
        this.vyl4Exituje = this.vylepsenie4.getExistenciaVylepsenia();
            
        if (!this.vyl1Exituje) { 
            this.vylepsenie1.getVylepsenie(riadok, stlpec);
            this.vyl1Exituje = this.vylepsenie1.getExistenciaVylepsenia();
            this.druhVylepsenia1 = this.vylepsenie1.getDruhVylepsenia();
            if (this.vyl1Exituje) {
                this.polohaVylepseniaJednaY = stlpec;
                this.polohaVylepseniaJednaX = riadok;
            }
        } else {
            if (!this.vyl2Exituje) { 
                this.vylepsenie2.getVylepsenie(riadok, stlpec);
                this.vyl2Exituje = this.vylepsenie2.getExistenciaVylepsenia();
                this.druhVylepsenia2 = this.vylepsenie2.getDruhVylepsenia();
                if (this.vyl2Exituje) {
                    this.polohaVylepseniaDvaY = stlpec;
                    this.polohaVylepseniaDvaX = riadok;
                }
            } else {
                if (!this.vyl3Exituje) {
                    this.vylepsenie3.getVylepsenie(riadok, stlpec);
                    this.vyl3Exituje = this.vylepsenie3.getExistenciaVylepsenia();
                    this.druhVylepsenia3 = this.vylepsenie3.getDruhVylepsenia();
                    if (this.vyl3Exituje) {
                        this.polohaVylepseniaTriY = stlpec;
                        this.polohaVylepseniaTriX = riadok;
                    } 
                } else {
                    if (!this.vyl4Exituje) {
                        this.vylepsenie4.getVylepsenie(riadok, stlpec);
                        this.vyl4Exituje = this.vylepsenie4.getExistenciaVylepsenia();
                        this.druhVylepsenia4 = this.vylepsenie4.getDruhVylepsenia();
                        if (this.vyl4Exituje) {
                            this.polohaVylepseniaStyriY = stlpec;
                            this.polohaVylepseniaStyriX = riadok;
                        }
                    }
                }
            }
        }
    }    
    
    /**
     * Vráti y polohu vylepšenia jedna až štyri, podľa zadaného parametra
     */
    public int getPolohuVylepseniaY(int cisloVylepsenia) {
        switch (cisloVylepsenia) {
            case 1:
                return this.polohaVylepseniaJednaY;
            case 2:
                return this.polohaVylepseniaDvaY;
            case 3:
                return this.polohaVylepseniaTriY;
            case 4: 
                return this.polohaVylepseniaStyriY;
        }
        return -69420;
    }
    
    /**
     * Vráti x polohu vylepšenia jedna až štyri, podľa zadaného parametra
     */
    public int getPolohuVylepseniaX(int cisloVylepsenia) {
        switch (cisloVylepsenia) {
            case 1:
                return this.polohaVylepseniaJednaX;
            case 2:
                return this.polohaVylepseniaDvaX;
            case 3:
                return this.polohaVylepseniaTriX;
            case 4: 
                return this.polohaVylepseniaStyriX;
        }
        return -69420;
    }
    
    /**
     * Vráti druh vylepšenia jedna až štyri, podľa zadaného parametra
     */
    public String getDruhVylepsenia(int cisloVylepsenia) {
        switch (cisloVylepsenia) {
            case 1:
                return this.druhVylepsenia1;
            case 2:
                return this.druhVylepsenia2;
            case 3:
                return this.druhVylepsenia3;
            case 4: 
                return this.druhVylepsenia4;
        }
        return null;
    }
    
    /**
     * Zmení x polohu vylepšenia jedna až štyri, na novú zadanú polohu
     */
    public void setPolohuVylepseniaX(int cisloVylepsenia, int novaHodnota) {
        switch (cisloVylepsenia) {
            case 1:
                this.polohaVylepseniaJednaX = novaHodnota;
                break;
            case 2:
                this.polohaVylepseniaDvaX = novaHodnota;
                break;
            case 3:
                this.polohaVylepseniaTriX = novaHodnota;
                break;
            case 4:
                this.polohaVylepseniaStyriX = novaHodnota;
                break;
        }
    }
    
    /**
     * Zmení y polohu vylepšenia jedna až štyri, na novú zadanú polohu
     */
    public void setPolohuVylepseniaY(int cisloVylepsenia, int novaHodnota) {
        switch (cisloVylepsenia) {
            case 1:
                this.polohaVylepseniaJednaY = novaHodnota;
                break;
            case 2:
                this.polohaVylepseniaDvaY = novaHodnota;
                break;
            case 3:
                this.polohaVylepseniaTriY = novaHodnota;
                break;
            case 4:
                this.polohaVylepseniaStyriY = novaHodnota;
                break;
        }
    }
    
    /**
     * Odstráni vylepšenie jedna až štyri, podľa zadaného parametra
     */
    public void vylepseniePrec(int cisloVylepsenia) {
        switch (cisloVylepsenia) {
            case 1:
                this.vylepsenie1.odstranVylepsenie();
                break;
            case 2:
                this.vylepsenie2.odstranVylepsenie();
                break;
            case 3:
                this.vylepsenie3.odstranVylepsenie();
                break;
            case 4:
                this.vylepsenie4.odstranVylepsenie();
                break;
        }        
    }
    
    /**
     * Vráti true ak vylepšenie jedna až štyri exizistuje (podľa zadaného parametra)
     */
    public boolean getExistujeVylepsenie(int cisloVylepsenia) {
        switch (cisloVylepsenia) {
            case 1:
                return this.vyl1Exituje;
            case 2:
                return this.vyl2Exituje;
            case 3:
                return this.vyl3Exituje;
            case 4:
                return this.vyl4Exituje;
        }
        return false;
    }
    
    /**
     * Vytvorí mapu 1
     * Na začiatočnú pozíciu hráča 1 a 2 nič nepostavý
     * Na okraji mapy vytvorí rad nezničiteľných blokov
     * Zbytok nežničiteľných blokov postaví podľa špeciálnej premennej, v tretom if statemente
     * Ostané bloky sa vytvoria a premenia na zničiteľné
     */
    public void vytvorMapu1() {
        for (int cisloRiadku = this.prvaPoziciaBloku; cisloRiadku < this.pocetBlokovStrany; cisloRiadku++) {
            for (int cisloStlpca = this.prvaPoziciaBloku; cisloStlpca < this.pocetBlokovStrany; cisloStlpca++) {
                this.yBloku = cisloStlpca * stranaBloku;
                this.xBloku = cisloRiadku * stranaBloku;
                if (cisloRiadku == 8 && cisloStlpca == 1 || cisloRiadku == 9 && cisloStlpca == 1 || cisloRiadku == 9 && cisloStlpca == 2 ) {
                    continue;
                }
                if (cisloRiadku == 9 && cisloStlpca == 9 || cisloRiadku == 9 && cisloStlpca == 8 || cisloRiadku == 8 && cisloStlpca == 9 ) {
                    continue;
                }
                if (cisloRiadku == this.prvaPoziciaBloku || cisloStlpca == this.prvaPoziciaBloku || cisloRiadku == this.pocetBlokovStrany - 1 || cisloStlpca == this.pocetBlokovStrany - 1 || cisloRiadku == cisloStlpca || 10 % cisloRiadku == 10 % cisloStlpca) {
                    this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                } else {
                    this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                    this.mapa[cisloRiadku][cisloStlpca].premenNaZnicitelny();
                }
                this.pocetObjektov++;
            }
        }
    }
    
    /**
     * Vytvorí mapu 2
     * Na začiatočnú pozíciu hráča 1 a 2 nič nepostavý
     * Na okraji mapy vytvorí rad nezničiteľných blokov
     * Zbytok nežničiteľných blokov postaví podľa špeciálnej premennej, v tretom if statemente
     * Ostané bloky sa vytvoria a premenia na zničiteľné
     */
    public void vytvorMapu2() {
        for (int cisloRiadku = this.prvaPoziciaBloku; cisloRiadku < this.pocetBlokovStrany; cisloRiadku++) {
            for (int cisloStlpca = this.prvaPoziciaBloku; cisloStlpca < this.pocetBlokovStrany; cisloStlpca++) {
                this.yBloku = cisloStlpca * stranaBloku;
                this.xBloku = cisloRiadku * stranaBloku;
                if (cisloRiadku == 8 && cisloStlpca == 1 || cisloRiadku == 9 && cisloStlpca == 1 || cisloRiadku == 9 && cisloStlpca == 2 ) {
                    continue;
                }
                if (cisloRiadku == 9 && cisloStlpca == 9 || cisloRiadku == 9 && cisloStlpca == 8 || cisloRiadku == 8 && cisloStlpca == 9 ) {
                    continue;
                }
                if (cisloRiadku == this.prvaPoziciaBloku || cisloStlpca == this.prvaPoziciaBloku || cisloRiadku == this.pocetBlokovStrany - 1 || cisloStlpca == this.pocetBlokovStrany - 1) {
                    this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                } else {
                    if (cisloRiadku % 2 == 0 && cisloStlpca % 2 == 0) {
                        this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                    } else {
                        this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                        this.mapa[cisloRiadku][cisloStlpca].premenNaZnicitelny();
                    }
                }
                this.pocetObjektov++;
            }
        }
    }
    
    /**
     * Vytvorí mapu 3
     * Na začiatočnú pozíciu hráča 1 a 2 nič nepostavý
     * Na okraji mapy vytvorí rad nezničiteľných blokov
     * Zbytok nežničiteľných blokov postaví podľa špeciálnej premennej, v tretom if statemente
     * Ostané bloky sa vytvoria a premenia na zničiteľné
     */
    public void vytvorMapu3() {
        for (int cisloRiadku = this.prvaPoziciaBloku; cisloRiadku < this.pocetBlokovStrany; cisloRiadku++) {
            for (int cisloStlpca = this.prvaPoziciaBloku; cisloStlpca < this.pocetBlokovStrany; cisloStlpca++) {
                this.yBloku = cisloStlpca * stranaBloku;
                this.xBloku = cisloRiadku * stranaBloku;
                if (cisloRiadku == 8 && cisloStlpca == 1 || cisloRiadku == 9 && cisloStlpca == 1 || cisloRiadku == 9 && cisloStlpca == 2 ) {
                    continue;
                }
                if (cisloRiadku == 9 && cisloStlpca == 9 || cisloRiadku == 9 && cisloStlpca == 8 || cisloRiadku == 8 && cisloStlpca == 9 ) {
                    continue;
                }
                if (cisloRiadku == this.prvaPoziciaBloku || cisloStlpca == this.prvaPoziciaBloku || cisloRiadku == this.pocetBlokovStrany - 1 || cisloStlpca == this.pocetBlokovStrany - 1) {
                    this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                } else {
                    if ((cisloRiadku == 5 || cisloStlpca == 5) && cisloRiadku != 1 && cisloStlpca != 1 && cisloRiadku != 9 && cisloStlpca != 9) {
                        this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                    } else {
                        this.mapa[cisloRiadku][cisloStlpca] = new Blok(this.xBloku, this.yBloku);
                        this.mapa[cisloRiadku][cisloStlpca].premenNaZnicitelny();
                    }
                }
                this.pocetObjektov++;
            }
        }
    }
    
    /**
     * Zmaze mapu a vylepšenia
     */
    public void zmazMapu() {
        for (int cisloRiadku = this.prvaPoziciaBloku; cisloRiadku < this.pocetBlokovStrany; cisloRiadku++) {
            for (int cisloStlpca = this.prvaPoziciaBloku; cisloStlpca < this.pocetBlokovStrany; cisloStlpca++) {
                if (this.mapa[cisloRiadku][cisloStlpca] != null ) {
                    this.mapa[cisloRiadku][cisloStlpca].premenNaZnicitelny();
                    this.mapa[cisloRiadku][cisloStlpca].znicBlok();
                    this.plocha.skry();
                    this.vylepsenie1.odstranVylepsenie();
                    this.vylepsenie2.odstranVylepsenie();
                    this.vylepsenie3.odstranVylepsenie();
                }
            }
        }    
    }
    
    /**
     * Ujistí sa že blok existuje
     * Parameter je x a y pozicia nie číslo riadku a stĺpca
     */
    public boolean nullExeptionOdstranovac(int x, int y) {
        if (this.mapa[x / this.stranaBloku][y / stranaBloku] != null) {
            return true;
        }
        return false;
    }
}