/**
 * Enum piatich možných stavov hry
 * VYHRA_HRAC1 nastane vyhrá hráč 1
 * VYHRA_HRAC2 nastane vyhrá hráč 2
 * REMIZA nastane obaja hráči zomrú
 * PAUZA je dočasný stav, ktorý nastane ak keď je hrá pozastavená
 * HRA_POKRACUJE je začiatočný stav, ktorý nastáva ak ostatné stavy nie sú aktívne
 */
public enum StavHry {
    VYHRA_HRAC1(),
    VYHRA_HRAC2(),
    REMIZA(),
    HRA_POKRACUJE(),
    PAUZA();
}
