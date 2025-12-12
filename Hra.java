import fri.shapesge.Manazer;
/**
 * Trieda hra má za úlohu prepojiť polohu blokov, bomb, výbuchov, vylepšení a hráčov medzi sebou
 * Hráč nesmie byť schopný stúpiť na miesto, kde je položený zničiteľný alebo nezničiteľný blok, bomba hráča jedna alebo dva
 * Hráč musí získať bombu navyše (ak nemá maximálny počet bomb) ak stúpi na miesto kde je položené vylepšenie
 * Trieda Hra mení StavyHry a teda aj ObrázkyStavuHry
 */
public class Hra {
    private final int odchylkaVyskyHraca;
    private final int krok;
    private int pocetBombH1;
    private int xHraca1;
    private int yHraca1;
    private int yBomby1H1;
    private int yBomby2H1;
    private int yBomby3H1;
    private int xBomby1H1;
    private int xBomby2H1; 
    private int xBomby3H1;
    private boolean bombaVybuchla1H1;
    private boolean bombaVybuchla2H1;
    private boolean bombaVybuchla3H1;
    private boolean stavBomby1H1;
    private boolean stavBomby2H1;
    private boolean stavBomby3H1;
    private boolean bombaTika1H1;
    private boolean bombaTika2H1;
    private boolean bombaTika3H1;
    private Hrac1 hrac1;
    private int pocetBombH2;
    private int xHraca2;
    private int yHraca2;
    private int yBomby1H2;
    private int yBomby2H2;
    private int yBomby3H2;
    private int xBomby1H2;
    private int xBomby2H2;
    private int xBomby3H2;
    private boolean bombaVybuchla1H2;
    private boolean bombaVybuchla2H2;
    private boolean bombaVybuchla3H2;
    private boolean stavBomby1H2;
    private boolean stavBomby2H2;
    private boolean stavBomby3H2;
    private boolean bombaTika1H2;
    private boolean bombaTika2H2;
    private boolean bombaTika3H2;
    private Hrac2 hrac2;
    private final int pocetObjektovBlok;    
    private int[][] polePozicBlokov;
    private Manazer manazer;
    private StavHry stav;
    private Mapa mapa;
    private ObrazokStavuHry stavObr;
    private boolean mameVylepsenie1;
    private boolean mameVylepsenie2;
    private boolean mameVylepsenie3;
    private boolean mameVylepsenie4;
    private int polohaVylepsenia1Y;
    private int polohaVylepsenia1X;
    private int polohaVylepsenia2Y;
    private int polohaVylepsenia2X;
    private int polohaVylepsenia3Y;
    private int polohaVylepsenia3X;
    private int polohaVylepsenia4Y;
    private int polohaVylepsenia4X;
    private String druhVylepseniaJedna;
    private String druhVylepseniaDva;
    private String druhVylepseniaTri;
    private String druhVylepseniaStyri;
    private final int pocetHracov;
    private boolean aktivnaPauza;
    
    /**
     * Vytvorí hráčov, mapu podľa výberu
     * Zadá začiatočný stavHry
     */
    public Hra(int cisloMapy) {
        this.pocetHracov = 2;
        this.aktivnaPauza = false;
        this.stav = StavHry.HRA_POKRACUJE;
        this.stavObr = new ObrazokStavuHry();
        this.manazer = new Manazer();
        this.mapa = new Mapa(cisloMapy);
        this.hrac1 = new Hrac1(270, 0);
        this.hrac2 = new Hrac2(270, 240);
        this.pocetBombH2 = this.hrac2.getPocetBomb();
        this.pocetBombH1 = this.hrac1.getPocetBomb();
        this.manazer.spravujObjekt(this);
        this.krok = this.hrac1.getKrok();
        this.pocetObjektovBlok = this.mapa.getPocetVytvorenichBlokov();        
        this.odchylkaVyskyHraca = this.hrac1.getOdchylkuVysky();        
        this.polePozicBlokov = new int[this.pocetObjektovBlok][];
        this.ulozeniePozicBlokov();
    } 
    
    /**
     * Časovač, ktorý každých 100 milisekúnd pošle správu 2 metódam
     */
    public void aktualizaciaPolohyBomb() {
        this.aktualizaciaPolohyH1();
        this.aktualizaciaPolohyH2();
    }
    
    /**
     * stavBomby - je True, keď je položená bomba
     * bombaTika - zabraňuje  tomu, aby poloha bomby sa menila, keď je položená bomba
     * Keď zavoláme na túto metódu, algoritmus skontroluje, či je bomba položná, či práve nevybuchla
     * V chvíli keď hráč 1 položí bombu, uloží sa jej poloha - funguje u všetkých troch bomb hráča 1
     */
    public void aktualizaciaPolohyH1() {
        this.xHraca1 = this.hrac1.getX();
        this.yHraca1 = this.hrac1.getY() + this.odchylkaVyskyHraca;
        this.stavBomby1H1 = this.hrac1.getStavBombyHraca(1);        
        this.stavBomby2H1 = this.hrac1.getStavBombyHraca(2);
        this.stavBomby3H1 = this.hrac1.getStavBombyHraca(3);        
        
        if (this.stavBomby1H1 && !this.bombaTika1H1) {
            this.xBomby1H1 = this.xHraca1;
            this.yBomby1H1 = this.yHraca1;
            this.bombaTika1H1 = true;
        }        
        if (!this.stavBomby1H1) {
            this.bombaTika1H1 = false;
        }    
        if (this.stavBomby2H1 && !this.bombaTika2H1) {
            this.xBomby2H1 = this.xHraca1;
            this.yBomby2H1 = this.yHraca1;
            this.bombaTika2H1 = true;
        }        
        if (!this.stavBomby2H1) {
            this.bombaTika2H1 = false;
        }    
        if (this.stavBomby3H1 && !this.bombaTika3H1) {
            this.xBomby3H1 = this.xHraca1;
            this.yBomby3H1 = this.yHraca1;
            this.bombaTika3H1 = true;
        }        
        if (!this.stavBomby3H1) {
            this.bombaTika3H1 = false;
        }
    }
    
    /**
     * stavBomby - je True, keď je položená bomba
     * bombaTika - zabraňuje  tomu, aby poloha bomby sa menila, keď je položená bomba
     * Keď zavoláme na túto metódu, algoritmus skontroluje, či je bomba položná, či práve nevybuchla
     * V chvíli keď hráč 2 položí bombu, uloží sa jej poloha - funguje u všetkých troch bomb hráča 2
     */
    public void aktualizaciaPolohyH2() {
        this.xHraca2 = this.hrac2.getX();
        this.yHraca2 = this.hrac2.getY() + this.odchylkaVyskyHraca;
        this.stavBomby1H2 = this.hrac2.getStavBombyHraca(1);        
        this.stavBomby2H2 = this.hrac2.getStavBombyHraca(2);
        this.stavBomby3H2 = this.hrac2.getStavBombyHraca(3);        
        
        if (this.stavBomby1H2 && !this.bombaTika1H2) {
            this.xBomby1H2 = xHraca2;
            this.yBomby1H2 = yHraca2;
            this.bombaTika1H2 = true;
        }        
        if (!this.stavBomby1H2) {
            this.bombaTika1H2 = false;
        }    
        if (this.stavBomby2H2 && !this.bombaTika2H2) {
            this.xBomby2H2 = xHraca2;
            this.yBomby2H2 = yHraca2;
            this.bombaTika2H2 = true;
        }        
        if (!this.stavBomby2H2) {
            this.bombaTika2H2 = false;
        }    
        if (this.stavBomby3H2 && !this.bombaTika3H2) {
            this.xBomby3H2 = xHraca2;
            this.yBomby3H2 = yHraca2;
            this.bombaTika3H2 = true;
        }        
        if (!this.stavBomby3H2) {
            this.bombaTika3H2 = false;
        }
    }
    
    /**
     * Časovač, ktorý každú sekundu kontroluje, či práve jedna z bomb nevybuchla
     */
    public void kontrolaBomb() {
        this.bombaVybuchla1H1 = this.hrac1.getBombaVybuchla(1);
        this.bombaVybuchla2H1 = this.hrac1.getBombaVybuchla(2);
        this.bombaVybuchla3H1 = this.hrac1.getBombaVybuchla(3);
        
        this.bombaVybuchla1H2 = this.hrac2.getBombaVybuchla(1);
        this.bombaVybuchla2H2 = this.hrac2.getBombaVybuchla(2);
        this.bombaVybuchla3H2 = this.hrac2.getBombaVybuchla(3);
    }
    
    /**
     * Ak bomba práve vybuchla pošle jej pozíciu ďalej a resetuje pozíciu bomby aby hráč mohol stúpiť na dané miesto
     * Funguje pre oboch hračov a všetkých šesť bomb
     */
    public void rosahVybuchuBombyVsetkychBomb() {
        if (this.pocetBombH1 <= 1) {
            if (this.bombaVybuchla1H1 && this.stav == StavHry.HRA_POKRACUJE) {
                this.rozsahVybuchuBomby(this.xBomby1H1, this.yBomby1H1);
                this.xBomby1H1 = -69420;
                this.yBomby1H1 = -69420;
            }
        }
        if (this.pocetBombH1 <= 2) {
            if (this.bombaVybuchla2H1 && this.stav == StavHry.HRA_POKRACUJE) {
                this.rozsahVybuchuBomby(this.xBomby2H1, this.yBomby2H1);
                this.xBomby2H1 = -69420;
                this.yBomby2H1 = -69420;
            }
        }
        if (this.pocetBombH1 <= 3) {
            if (this.bombaVybuchla3H1 && this.stav == StavHry.HRA_POKRACUJE) {
                this.rozsahVybuchuBomby(this.xBomby3H1, this.yBomby3H1);
                this.xBomby3H1 = -69420;
                this.yBomby3H1 = -69420;
            }
        }
        if (this.pocetBombH2 <= 1) {
            if (this.bombaVybuchla1H2 && this.stav == StavHry.HRA_POKRACUJE) {
                this.rozsahVybuchuBomby(this.xBomby1H2, this.yBomby1H2);
                this.xBomby1H2 = -69420;
                this.yBomby1H2 = -69420;
            }
        }
        if (this.pocetBombH2 <= 2) {
            if (this.bombaVybuchla2H2 && this.stav == StavHry.HRA_POKRACUJE) {
                this.rozsahVybuchuBomby(this.xBomby2H2, this.yBomby2H2);
                this.xBomby2H2 = -69420;
                this.yBomby2H2 = -69420;
            }
        }
        if (this.pocetBombH2 <= 3) {
            if (this.bombaVybuchla3H2 && this.stav == StavHry.HRA_POKRACUJE) {
                this.rozsahVybuchuBomby(this.xBomby3H2, this.yBomby3H2);
                this.xBomby3H2 = -69420;
                this.yBomby3H2 = -69420;
            }
        }
    }   
    
    /**
     * Požiada o zničenie blokov v okolí bomby (blok sa zničí len ak je zničiteľný)
     * Potom pošle správu aby sa uložili nové pozície blokov, zničené pozície sa zmazú z tohto dvojdimenzionálneho poľa
     */
    public void rozsahVybuchuBomby(int x, int y) { 
        if (this.mapa.nullExeptionOdstranovac(x + 30, y)) {
            this.mapa.znicZnicitelnyBlok(x + 30, y);
        }
        if (this.mapa.nullExeptionOdstranovac(x - 30, y)) {
            this.mapa.znicZnicitelnyBlok(x - 30, y);
        }
        if (this.mapa.nullExeptionOdstranovac(x, y - 30)) {
            this.mapa.znicZnicitelnyBlok(x, y - 30);
        }
        if (this.mapa.nullExeptionOdstranovac(x, y + 30)) {
            this.mapa.znicZnicitelnyBlok(x, y + 30);
        }
        this.ulozeniePozicBlokov();
    }
    
    /**
     * Prebere všetky pozície blokov z dvojdimenzionálneho poľa mapy a dá ich do vlastného poľa pre dvojdimenzionálny int
     * -69420 je hodnota, ktorú používam lebo int nemôže používať null, čiže som nastavil, aby getter mapy vrátil hodnotu -69420 keď blok, na ktorý sa pítam, neexistuje
     * -69420 odfiltrujem predtým ako hodnoti pozíc blokov dám do poľa polePozicBlokov
     */
    public void ulozeniePozicBlokov() {
        int pocetOpakovany = this.mapa.getPocetBlokov();
        int poziciaBloku1 = this.mapa.getPrvuPoziciuBloku();
        int indexPola = 0;
        int yBloku;
        int xBloku;
        for (int stlpec = poziciaBloku1; stlpec <  pocetOpakovany; stlpec++) {
            for (int riadok = poziciaBloku1; riadok <  pocetOpakovany; riadok++) {
                xBloku = this.mapa.getPoziciuXBloku(riadok, stlpec);
                yBloku = this.mapa.getPoziciuYBloku(riadok, stlpec);
                if (xBloku != -69420 || yBloku != -69420) {
                    this.polePozicBlokov[indexPola] = new int[]{xBloku, yBloku};
                    indexPola++; 
                }
            }
        }
    }
    
    /**
     * Časovač, ktorý každú milisekundu pošle správu o kontrole kadiaľ hráči majú dovoléné ísť
     */
    public void koltrolaCesty() {
        for (int cisloHraca = 1; cisloHraca <= pocetHracov; cisloHraca++) {
            this.koliziaSBlokmi(cisloHraca);
        }
    }
        
    /**
     * Podľa čísla zadaného v parametri kontroľuje okolie hráča 1 alebo 2 - spravuje kolíziu hráča s blokmi
     * Keďže všetky pozície mám v pole polí int, ak nasledujúca poloha v poli sa rovná budúcej polohe hráča (poloha hráča plus/mínus 1 krok do strany y/x), zakáže mu tade ísť
     *  - funguje to preto lebo z poľa sa odstránuje poloha každého zničeného bloku
     */
    public void koliziaSBlokmi(int cisloHraca) {
        int yHraca;
        int xHraca;
        switch (cisloHraca) {
            case 1:
                xHraca = this.xHraca1;
                yHraca = this.yHraca1;
                this.hrac1.setOkolieHraca(true, "Vpravo");
                this.hrac1.setOkolieHraca(true, "Vlavo");
                this.hrac1.setOkolieHraca(true, "Dole");
                this.hrac1.setOkolieHraca(true, "Hore");
                break;
            case 2:
                xHraca = this.xHraca2;
                yHraca = this.yHraca2;
                this.hrac2.setOkolieHraca(true, "Vpravo");
                this.hrac2.setOkolieHraca(true, "Vlavo");
                this.hrac2.setOkolieHraca(true, "Dole");
                this.hrac2.setOkolieHraca(true, "Hore");
        
                break;
            default:
                xHraca = -54444;
                yHraca = -54444;
                break;
        }
                
        for (int j = 0; j < this.polePozicBlokov.length; j++) {
            if (j + 1 < this.polePozicBlokov.length && j - 1 > this.mapa.getPrvuPoziciuBloku()) {                
                if ((this.polePozicBlokov[j + 1][0]) == (xHraca + this.krok) && (this.polePozicBlokov[j][1]) == (yHraca)) {
                    if (cisloHraca == 1) {
                        this.hrac1.setOkolieHraca(false, "Vpravo");
                    } else {
                        if (cisloHraca == 2) {
                            this.hrac2.setOkolieHraca(false, "Vpravo");
                        }
                    }
                }
                if ((this.polePozicBlokov[j - 1][0]) == (xHraca - this.krok) && (this.polePozicBlokov[j][1]) == (yHraca)) {
                    if (cisloHraca == 1) {
                        this.hrac1.setOkolieHraca(false, "Vlavo");
                    } else {
                        if (cisloHraca == 2) {
                            this.hrac2.setOkolieHraca(false, "Vlavo");
                        }
                    }
                }
                if ((this.polePozicBlokov[j][0]) == (xHraca) && (this.polePozicBlokov[j + 1][1]) == (yHraca + this.krok)) {
                    if (cisloHraca == 1) {
                        this.hrac1.setOkolieHraca(false, "Dole");
                    } else {
                        if (cisloHraca == 2) {
                            this.hrac2.setOkolieHraca(false, "Dole");
                        }
                    }
                }
                if ((this.polePozicBlokov[j][0]) == (xHraca) && (this.polePozicBlokov[j - 1][1]) == (yHraca - this.krok)) {
                    if (cisloHraca == 1) {
                        this.hrac1.setOkolieHraca(false, "Hore");
                    } else {
                        if (cisloHraca == 2) {
                            this.hrac2.setOkolieHraca(false, "Hore");
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Je to časovač
     * Keď vznikne vylepšenie uloží si jeho polohu a druh
     * Ak hráč 1 alebo 2 sa postavý na polohu vylepšenia, vylepšenie zmizne a hráč buď získa bombu navyše alebo život 
     */
    public void kontrolaKolizieVylepsenia() {
        this.mameVylepsenie1 = this.mapa.getExistujeVylepsenie(1);
        this.mameVylepsenie2 = this.mapa.getExistujeVylepsenie(2);
        this.mameVylepsenie3 = this.mapa.getExistujeVylepsenie(3);
        this.mameVylepsenie4 = this.mapa.getExistujeVylepsenie(4);
        
        if (this.mameVylepsenie1) {
            this.polohaVylepsenia1X = this.mapa.getPolohuVylepseniaX(1);
            this.polohaVylepsenia1Y = this.mapa.getPolohuVylepseniaY(1);
            this.druhVylepseniaJedna = this.mapa.getDruhVylepsenia(1);
        }
        if (this.mameVylepsenie2) {
            this.polohaVylepsenia2X = this.mapa.getPolohuVylepseniaX(2);
            this.polohaVylepsenia2Y = this.mapa.getPolohuVylepseniaY(2);
            this.druhVylepseniaDva = this.mapa.getDruhVylepsenia(2);
        }
        if (this.mameVylepsenie3) {
            this.polohaVylepsenia3X = this.mapa.getPolohuVylepseniaX(3);
            this.polohaVylepsenia3Y = this.mapa.getPolohuVylepseniaY(3);
            this.druhVylepseniaTri = this.mapa.getDruhVylepsenia(3);
        }
        if (this.mameVylepsenie4) {
            this.polohaVylepsenia4X = this.mapa.getPolohuVylepseniaX(4);
            this.polohaVylepsenia4Y = this.mapa.getPolohuVylepseniaY(4);
            this.druhVylepseniaStyri = this.mapa.getDruhVylepsenia(4);
        }
        
        if (this.polohaVylepsenia1X == this.xHraca1 && this.polohaVylepsenia1Y == this.yHraca1) {
            if (this.druhVylepseniaJedna.equals("Bomba")) {
                this.hrac1.pridajBombu();
            }
            if (this.druhVylepseniaJedna.equals("Zivot")) {
                this.hrac1.setPocetZivotou(1);
            }
            this.mapa.vylepseniePrec(1);
            this.mapa.setPolohuVylepseniaX(1, -69420);
            this.mapa.setPolohuVylepseniaY(1, -69420);
        } else {
            if (this.polohaVylepsenia1X == this.xHraca2 && this.polohaVylepsenia1Y == this.yHraca2) {
                if (this.druhVylepseniaJedna.equals("Bomba")) {
                    this.hrac2.pridajBombu();
                }
                if (this.druhVylepseniaJedna.equals("Zivot")) {
                    this.hrac2.setPocetZivotou(1);
                }
                this.mapa.vylepseniePrec(1);
                this.mapa.setPolohuVylepseniaX(1, -69420);
                this.mapa.setPolohuVylepseniaY(1, -69420);
            }   
        }
        
        if (this.polohaVylepsenia2X == this.xHraca1 && this.polohaVylepsenia2Y == this.yHraca1) {
            if (this.druhVylepseniaDva.equals("Bomba")) {
                this.hrac1.pridajBombu(); 
            }    
            if (this.druhVylepseniaDva.equals("Zivot")) {
                this.hrac1.setPocetZivotou(1);
            }
            this.mapa.vylepseniePrec(2);
            this.mapa.setPolohuVylepseniaX(2, -69420);
            this.mapa.setPolohuVylepseniaY(2, -69420);
        } else {
            if (this.polohaVylepsenia2X == this.xHraca2 && this.polohaVylepsenia2Y == this.yHraca2) {
                if (this.druhVylepseniaDva.equals("Bomba")) {
                    this.hrac2.pridajBombu(); 
                }    
                if (this.druhVylepseniaDva.equals("Zivot")) {
                    this.hrac2.setPocetZivotou(1);
                }
                this.mapa.vylepseniePrec(2);
                this.mapa.setPolohuVylepseniaX(2, -69420);
                this.mapa.setPolohuVylepseniaY(2, -69420);
            }
        }
        
        if (this.polohaVylepsenia3X == this.xHraca1 && this.polohaVylepsenia3Y == this.yHraca1) {
            if (this.druhVylepseniaTri.equals("Bomba")) {
                this.hrac1.pridajBombu(); 
            }    
            if (this.druhVylepseniaTri.equals("Zivot")) {
                this.hrac1.setPocetZivotou(1);
            }
            this.mapa.vylepseniePrec(3);
            this.mapa.setPolohuVylepseniaX(3, -69420);
            this.mapa.setPolohuVylepseniaY(3, -69420);
        } else {
            if (this.polohaVylepsenia3X == this.xHraca2 && this.polohaVylepsenia3Y == this.yHraca2) {
                if (this.druhVylepseniaTri.equals("Bomba")) {
                    this.hrac2.pridajBombu(); 
                }    
                if (this.druhVylepseniaTri.equals("Zivot")) {
                    this.hrac2.setPocetZivotou(1);
                }
                this.mapa.vylepseniePrec(3);
                this.mapa.setPolohuVylepseniaX(3, -69420);
                this.mapa.setPolohuVylepseniaY(3, -69420);
            }
        }
        
        if (this.polohaVylepsenia4X == this.xHraca1 && this.polohaVylepsenia4Y == this.yHraca1) {
            if (this.druhVylepseniaStyri.equals("Bomba")) {
                this.hrac1.pridajBombu(); 
            }    
            if (this.druhVylepseniaStyri.equals("Zivot")) {
                this.hrac1.setPocetZivotou(1);
            }
            this.mapa.vylepseniePrec(4);
            this.mapa.setPolohuVylepseniaX(4, -69420);
            this.mapa.setPolohuVylepseniaY(4, -69420);
        } else {
            if (this.polohaVylepsenia4X == this.xHraca2 && this.polohaVylepsenia4Y == this.yHraca2) {
                if (this.druhVylepseniaStyri.equals("Bomba")) {
                    this.hrac2.pridajBombu(); 
                }    
                if (this.druhVylepseniaStyri.equals("Zivot")) {
                    this.hrac2.setPocetZivotou(1);
                }
                this.mapa.vylepseniePrec(4);
                this.mapa.setPolohuVylepseniaX(4, -69420);
                this.mapa.setPolohuVylepseniaY(4, -69420);
            }
        }
    } 
    
    /**
     * Časovač, ktorý každú sekundu pošle správu o kontrole či hráč 1 alebo 2 nestojí na mieste výbuchu
     */
    public void koliziaUjmiHracov() {
        for (int cisloHraca = 1; cisloHraca <= pocetHracov; cisloHraca++) {
            this.minusZivotHracaH1(cisloHraca);
            this.minusZivotHracaH2(cisloHraca);
        }
    }
    
    /**
     * Skontroluje či hráč 1 alebo 2 stojí vo výbuchu z bomby hráča 1
     * Ak áno pošle správu aby zobrali hráčovi život
     */
    public void minusZivotHracaH1(int cisloHraca) {
        int xHraca;
        int yHraca;
        switch (cisloHraca) {
            case 1:
                xHraca = this.xHraca1;
                yHraca = this.yHraca1;
                break;
            
            case 2:
                xHraca = this.xHraca2;
                yHraca = this.yHraca2;
                break;
                
            default:
                xHraca = -42069;
                yHraca = -42069;
                break;
        }
        
        if (this.bombaVybuchla1H1 && !this.bombaTika1H1 && !this.stavBomby1H1) {            
            if (xHraca == this.xBomby1H1 && yHraca == this.yBomby1H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H1 + this.krok && yHraca == this.yBomby1H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H1 && yHraca == this.yBomby1H1  + this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H1 - this.krok && yHraca == this.yBomby1H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H1 && yHraca == this.yBomby1H1  - this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
        }
        
        if (this.bombaVybuchla2H1 && !this.bombaTika2H1 && !this.stavBomby2H1) {            
            if (xHraca == this.xBomby2H1 && yHraca == this.yBomby2H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H1 + this.krok && yHraca == this.yBomby2H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H1 && yHraca == this.yBomby2H1  + this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H1 - this.krok && yHraca == this.yBomby2H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H1 && yHraca == this.yBomby2H1  - this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
        }
        
        if (this.bombaVybuchla3H1 && !this.bombaTika3H1 && !this.stavBomby3H1) {            
            if (xHraca == this.xBomby3H1 && yHraca == this.yBomby3H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H1 + this.krok && yHraca == this.yBomby3H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H1 && yHraca == this.yBomby3H1  + this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H1 - this.krok && yHraca == this.yBomby3H1) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H1 && yHraca == this.yBomby3H1  - this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
        }
    }
    
    /**
     * Skontroluje či hráč 1 alebo 2 stojí vo výbuchu z bomby hráča 2
     * Ak áno pošle správu aby zobrali hráčovi život
     */
    public void minusZivotHracaH2(int cisloHraca) {
        int xHraca;
        int yHraca;
        switch (cisloHraca) {
            case 1:
                xHraca = this.xHraca1;
                yHraca = this.yHraca1;
                break;
            
            case 2:
                xHraca = this.xHraca2;
                yHraca = this.yHraca2;
                break;
                
            default:
                xHraca = -42069;
                yHraca = -42069;
                break;
        }
        
        if (this.bombaVybuchla1H2 && !this.bombaTika1H2 && !this.stavBomby1H2) {            
            if (xHraca == this.xBomby1H2 && yHraca == this.yBomby1H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H2 + this.krok && yHraca == this.yBomby1H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H2 && yHraca == this.yBomby1H2  + this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H2 - this.krok && yHraca == this.yBomby1H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby1H2 && yHraca == this.yBomby1H2 - this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
        }
        
        if (this.bombaVybuchla2H2 && !this.bombaTika2H2 && !this.stavBomby2H2) {            
            if (xHraca == this.xBomby2H2 && yHraca == this.yBomby2H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H2 + this.krok && yHraca == this.yBomby2H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H2 && yHraca == this.yBomby2H2 + this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H2 - this.krok && yHraca == this.yBomby2H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby2H2 && yHraca == this.yBomby2H2 - this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
        }
        
        if (this.bombaVybuchla3H2 && !this.bombaTika3H2 && !this.stavBomby3H2) {            
            if (xHraca == this.xBomby3H2 && yHraca == this.yBomby3H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H2 + this.krok && yHraca == this.yBomby3H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H2 && yHraca == this.yBomby3H2 + this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H2 - this.krok && yHraca == this.yBomby3H2) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
            if (xHraca == this.xBomby3H2 && yHraca == this.yBomby3H2 - this.krok) {
                this.vyberHracaMinusZivot(cisloHraca);
            }
        }
    }
    
    /**
     * Zobere 1 život zadanému hráčovi
     */
    public void vyberHracaMinusZivot(int cisloHraca) {
        switch (cisloHraca) {
            case 1:
                this.hrac1.setPocetZivotou(-1);
                break;
            
            case 2:
                this.hrac2.setPocetZivotou(-1);
                break;
        }
    }
    
    /**
     * Časovač, ktorý každú sekundu pošle správu o kontrole či hráč 1 alebo 2 nestojí veďla bombi
     */
    public void koliziaSBombami() {
        for (int cisloHrac = 1; cisloHrac <= 2; cisloHrac++) {
            this.koliziaSBomb(cisloHrac);
        }
    }
    
    /**
     * Zakazuje hráčom aby stúpili na miesto, kde je položená bomba
     */
    public void koliziaSBomb(int cisloHraca) {
        int yHraca;
        int xHraca;
        switch (cisloHraca) {
            case 1:
                xHraca = this.xHraca1;
                yHraca = this.yHraca1;
                break;
            case 2:
                xHraca = this.xHraca2;
                yHraca = this.yHraca2;
                break;
            default:
                xHraca = -54444;
                yHraca = -54444;
                break;
        }
        
        if ((this.xBomby1H1 + this.krok == xHraca && this.yBomby1H1 == yHraca) || (this.xBomby2H1 + this.krok == xHraca && this.yBomby2H1 == yHraca) || (this.xBomby3H1  + this.krok == xHraca && this.yBomby3H1 == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Vlavo");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Vlavo");
                    break;
            }
        }
        if ((this.xBomby1H2  + this.krok == xHraca && this.yBomby1H2 == yHraca) || (this.xBomby2H2 + this.krok == xHraca && this.yBomby2H2 == yHraca) || (this.xBomby3H2  + this.krok == xHraca && this.yBomby3H2 == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Vlavo");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Vlavo");
                    break;
            }
        }
        if ((this.xBomby1H1 - this.krok == xHraca && this.yBomby1H1 == yHraca) || (this.xBomby2H1 - this.krok == xHraca && this.yBomby2H1 == yHraca) || (this.xBomby3H1 - this.krok == xHraca && this.yBomby3H1 == yHraca)) { 
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Vpravo");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Vpravo");
                    break;
            }
        }
        if ((this.xBomby1H2  - this.krok == xHraca && this.yBomby1H2 == yHraca) || (this.xBomby2H2 - this.krok == xHraca && this.yBomby2H2 == yHraca) || (this.xBomby3H2 - this.krok == xHraca && this.yBomby3H2 == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Vpravo");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Vpravo");
                    break;
            }
        }    
        if ((this.xBomby1H1 == xHraca && this.yBomby1H1 + this.krok == yHraca) || (this.xBomby2H1 == xHraca && this.yBomby2H1 + this.krok == yHraca) || (this.xBomby3H1 == xHraca && this.yBomby3H1 + this.krok == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Hore");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Hore");
                    break;
            }
        }
        if ((this.xBomby1H2 == xHraca && this.yBomby1H2 + this.krok == yHraca) || (this.xBomby2H2 == xHraca && this.yBomby2H2 + this.krok == yHraca) || (this.xBomby3H2 == xHraca && this.yBomby3H2 + this.krok == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Hore");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Hore");
                    break;
            }
        }   
        if ((this.xBomby1H1 == xHraca && this.yBomby1H1 - this.krok == yHraca) || (this.xBomby2H1 == xHraca && this.yBomby2H1 - this.krok == yHraca) || (this.xBomby3H1 == xHraca && this.yBomby3H1 - this.krok == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Dole");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Dole");
                    break;
            }
        }    
        if ((this.xBomby1H2 == xHraca && this.yBomby1H2 - this.krok == yHraca) || (this.xBomby2H2 == xHraca && this.yBomby2H2 - this.krok == yHraca) || (this.xBomby3H2 == xHraca && this.yBomby3H2 - this.krok == yHraca)) {
            switch (cisloHraca) {
                case 1:
                    this.hrac1.setOkolieHraca(false, "Dole");
                    break;
                    
                case 2:
                    this.hrac2.setOkolieHraca(false, "Dole");
                    break;
            }
        }
    }
    
    /**
     * Nastavuje enum stavHry - výsledok hry (REMIZA, VYHRA_HRAC1, VYHRA_HRAC2), pauza (PAUZA) alebo hra je aktívna(HRA_POKRACUJE)
     * Potom požiada o obrázok triedy ObrazokStavuHry
     */
    public void stavHry() {
        int pocetZivotouH1 = this.hrac1.getPocetZivotou();
        int pocetZivotouH2 = this.hrac2.getPocetZivotou();
        if (this.stav != StavHry.PAUZA) {
            if (pocetZivotouH1 <= 0 && pocetZivotouH2 <= 0 && this.stav != StavHry.VYHRA_HRAC1 && this.stav != StavHry.VYHRA_HRAC2) {
                this.stav = StavHry.REMIZA;
            }
            if (pocetZivotouH1 > 0 && pocetZivotouH2 <= 0) {
                this.stav = StavHry.VYHRA_HRAC1;
            }
            if (pocetZivotouH1 <= 0 && pocetZivotouH2 > 0) {
                this.stav = StavHry.VYHRA_HRAC2;
            }
        }
        if (this.stav == StavHry.REMIZA || this.stav == StavHry.VYHRA_HRAC1 || this.stav == StavHry.VYHRA_HRAC2) {           
            this.zastavHru(true);
        }
        this.stavObr.dajObr(this.stav);
    }
    
    /**
     * Pozastaví hru pri stlačený tlačidla P a znovi spustí pri stačený tlačidla P
     */
    public void pauza() {
        if (!this.aktivnaPauza) {
            this.aktivnaPauza = true;
            this.zastavHru(true);
            this.stav = StavHry.PAUZA;
        } else {
            this.aktivnaPauza = false;
            this.zastavHru(false);
            this.stav = StavHry.HRA_POKRACUJE;
        }
    }
    
    /**
     * Úplne zastavý hru (true) alebo ju zapne (false), záleží na parametri
     */
    public void zastavHru(boolean zastavit) {
        if (zastavit) {
            this.manazer.prestanSpravovatObjekt(this.hrac1);
            this.manazer.prestanSpravovatObjekt(this.hrac2);
        } else {
            this.manazer.spravujObjekt(this.hrac1);
            this.manazer.spravujObjekt(this.hrac2);
        }
        this.hrac1.pauzaBomby(zastavit);
        this.hrac2.pauzaBomby(zastavit);
    }
    
    /**
     * Getter pre enum StavHry
     */
    public StavHry getStavHry() {
        return this.stav;
    }
    
    /**
     * Zmaze všetky objekti aby sa uvoľnila pamäť
     */
    public void odstranObjekti() {
        this.hrac1.zmazObjekti();
        this.hrac2.zmazObjekti();
        this.mapa.zmazMapu();
        this.manazer.prestanSpravovatObjekt(this.hrac1);
        this.manazer.prestanSpravovatObjekt(this.hrac2);
        this.manazer.prestanSpravovatObjekt(this.mapa);
        this.manazer.prestanSpravovatObjekt(this);
    }
}