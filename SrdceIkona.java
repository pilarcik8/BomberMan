import fri.shapesge.Obrazok;
/**
 * Ikona srdca predstavu počet životou hráča
 * Srdce môžne byť plné (život má) alebo prázdne (život stratil)
 */
public class SrdceIkona {
    private Obrazok srdce1;
    private Obrazok srdce2;
    private Obrazok srdce3;
    /**
     * Inicializácia 3 obrázkov srdca (hráč má 3 životy)
     */
    public SrdceIkona(int vyska) {
        this.srdce1 = new Obrazok("obrazky\\srdcePlne.png", 390, vyska);
        this.srdce1.zobraz();
        this.srdce2 = new Obrazok("obrazky\\srdcePlne.png", 460, vyska);
        this.srdce2.zobraz();
        this.srdce3 = new Obrazok("obrazky\\srdcePlne.png", 530, vyska);
        this.srdce3.zobraz();
    }
    
    /**
     * Vymení obrázok
     * Indikuje to, že hráč stritil život
     */
    public void zmenSrdce(int cisloSrdcia) {
        switch (cisloSrdcia) {
            case 1:
                this.srdce1.zmenObrazok("obrazky\\srdcePrazdne.png");
                break;
            
            case 2:
                this.srdce2.zmenObrazok("obrazky\\srdcePrazdne.png");
                break;
                
            case 3:
                this.srdce3.zmenObrazok("obrazky\\srdcePrazdne.png");
                break;
        }
    }
    
    /**
     * Vymení obrázok
     * Indikuje to, že hráč získal život
     */
    public void zachovajSrdce(int cisloSrdcia) {
        switch (cisloSrdcia) {
            case 1:
                this.srdce1.zmenObrazok("obrazky\\srdcePlne.png");
                break;
            
            case 2:
                this.srdce2.zmenObrazok("obrazky\\srdcePlne.png");
                break;
                
            case 3:
                this.srdce3.zmenObrazok("obrazky\\srdcePlne.png");
                break;
        }
    }
    
    /**
     * Zmaze ikony
     */
    public void odstranObjektiSrdcia() {
        this.srdce1.skry();
        this.srdce2.skry();
        this.srdce3.skry();
    }
}
