import fri.shapesge.Obrazok;
import java.util.Random;
/**
 * Vylepšenie - pridá hráčovi, ktorý stúpi na obrázok vylepšenia
 * 2 druhy vylepšení - jedno pridá bombu, druhé pridá život
 */
public class Vylepsenia {
    private final Obrazok vylepseniePlusBomba;
    private final Obrazok vylepseniePlusZivot;
    private Random rand = new Random();
    private boolean znikloVylepsenie;
    private String druhVylepsenia;
     /**
      * Inicializácia vylepšenia
      */   
    public Vylepsenia() {
        this.znikloVylepsenie = false;
        this.vylepseniePlusBomba = new Obrazok("obrazky\\vylepseniePlusBomba.png");     
        this.vylepseniePlusZivot = new Obrazok("obrazky\\vylepseniePlusZivot.png");   
    }
    
    /**
     * Pomocuj knižnice Random máme šancu, že padne vylepšenie
     * Ak padne, na zadanú polohu sa ukáže obrázok s daným vylepšeným
     */
    public Obrazok getVylepsenie(int x, int y) {
        int nahodneCislo = this.rand.nextInt(3);        
        switch (nahodneCislo) {
            case 1:
                this.druhVylepsenia = "Bomba";
                this.vylepseniePlusBomba.zmenPolohu(x, y);
                this.vylepseniePlusBomba.zobraz();
                this.znikloVylepsenie = true;
                return this.vylepseniePlusBomba;
            case 2:
                this.druhVylepsenia = "Zivot";
                this.vylepseniePlusZivot.zmenPolohu(x, y);
                this.vylepseniePlusZivot.zobraz();
                this.znikloVylepsenie = true;
                return this.vylepseniePlusZivot;
        }
        return null;
    }
    
    /**
     * Pošle informáciu či už padľo vylepšenie
     */
    public boolean getExistenciaVylepsenia() {
        return this.znikloVylepsenie;
    }
    
    /**
     * Pošle druh vylepšenia
     */
    public String getDruhVylepsenia() {
        return this.druhVylepsenia;
    }
    
    /**
     * Odstráni vylepšenie
     */
    public void odstranVylepsenie() {
        this.vylepseniePlusZivot.skry();
        this.vylepseniePlusBomba.skry();
        this.znikloVylepsenie = false;
    }
}
