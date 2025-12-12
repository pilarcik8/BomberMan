import fri.shapesge.Obrazok;
/**
 * Ikona bomby predstavuje počet získaných bomb hráča
 */
public class BombaIkona {
    private Obrazok bomba1;
    private Obrazok bomba2;
    private Obrazok bomba3;
    /**
     * Inicializácia troch obrázkov bomb (hráč má maximálne 3 bombi)
     * Na začiatku je zobrazení iba prvý obrázok (hráč má na začiatku len jednu bombu)
     */
    public BombaIkona(int vyska) {
        this.bomba1 = new Obrazok("obrazky\\bombaIkona.png", 390, vyska);
        this.bomba1.zobraz();
        this.bomba2 = new Obrazok("obrazky\\bombaIkona.png", 460, vyska);
        this.bomba3 = new Obrazok("obrazky\\bombaIkona.png", 530, vyska);
    }
    
    /**
     * Zobrazí obrázok bomby podľa inputu
     */
    public void pridajBombu(int cisloSrdcia) {
        switch (cisloSrdcia) {
            case 2:
                this.bomba2.zobraz();
                break;
            case 3:
                this.bomba3.zobraz();
                break;
        }
    }
    
    /**
     * Zmaze ikony
     */
    public void odstranObjektiBomby() {
        this.bomba1.skry();
        this.bomba2.skry();
        this.bomba3.skry();
    }
}