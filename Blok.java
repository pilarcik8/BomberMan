import fri.shapesge.Obrazok;
/**
 * Blok je obrázok kocky
 * Blok môže byť zničiteľný (zBlok) alebo nezničiteľný (nBlok)
 * jeZnicitelny - ak vedľa neho vybuchne bomba odstráni sa
 */
public class Blok {
    private final Obrazok blok;
    private int xBl;
    private int yBl;
    private boolean jeZnicitelny;
    /**
     * Inicializuje a zobrazí Blok na zadanom mieste
     * Jeho základný stav je, že je nezničiteľný
     */
    public Blok(int x, int y) {
        this.blok = new Obrazok("obrazky\\nBlok.png", x, y);
        this.jeZnicitelny = false;
        this.xBl = x;
        this.yBl = y;
        this.blok.zobraz();
    }
     
    /**
     * Getter pre x polohu Bloku
     */
    public int getX() {
        return this.xBl;
    }
    
    /**
     * Getter pre y polohu Bloku
     */
    public int getY() {
        return this.yBl;
    }
    
    /**
     * Premeni blok na zničiteľný - ak vedľa neho vybuchne bomba, odstráni sa
     */
    public void premenNaZnicitelny() {
        this.jeZnicitelny = true;
        this.blok.zmenObrazok("obrazky\\zBlok.png");
    }
    
    /**
     * Odstráni blok, ak je blok zničiteľný
     * Hodnotu -69420 požívam v triede Mapa na prefiltrovanie zničených blokov v dvojdimenzionálnom poli pre hodnotu y a x blokov mapy
     */
    public void znicBlok() {
        if (this.jeZnicitelny) {
            this.blok.skry();
            this.xBl = -69420;
            this.yBl = -69420;
        }
    }
    
    /**
     * Getter, ktorý oznámi či je blok zničiteľný alebo nezničiteľný
     */
    public boolean getZnicitelnost() {
        return this.jeZnicitelny;
    }    
}
