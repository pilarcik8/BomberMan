import fri.shapesge.Obrazok;
/**
 * Zadáva obrázok pred pred hru ak je splnená niektorá podmienka
 */
public class ObrazokStavuHry {
    private Obrazok obr;
    /**
     * Inicializácia obrázku
     */
    public ObrazokStavuHry() {
        this.obr = new Obrazok("obrazky\\hracDoleStoj.png", 0, 0);
    }
    
    /**
     * Ukáže obrázok podľa enumu StavHry
     */
    public void dajObr(StavHry stav) {
        if (stav == StavHry.PAUZA) {
            this.obr.zmenObrazok("obrazky\\pauza.png");
            this.obr.zobraz();
        }
        if (stav == StavHry.REMIZA) {
            this.obr.zmenObrazok("obrazky\\remiza.png");
            this.obr.zobraz();
        }
        if (stav == StavHry.VYHRA_HRAC1) {
            this.obr.zmenObrazok("obrazky\\vyhralHrac1.png");
            this.obr.zobraz();
        }
        if (stav == StavHry.VYHRA_HRAC2) {
            this.obr.zmenObrazok("obrazky\\vyhralHrac2.png");
            this.obr.zobraz();
        }
        if (stav == StavHry.HRA_POKRACUJE) {
            this.obr.skry();
        }
    }
}
