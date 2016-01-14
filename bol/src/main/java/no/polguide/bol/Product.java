package no.polguide.bol;

public class Product {
    public final double nr;
    public final String navn;
    public final String beskrivelse;
    public final String kategori;
    public final String produsent;
    public final String land;
    public final double pris;
    public final double volum;
    public final double alkohol;
    public final double alkoholpris;
    public final String url;

    public Product(double nr, String navn, String beskrivelse, String kategori, String produsent, String land, double pris, double volum, double alkohol, double alkoholpris, String url) {
        this.nr = nr;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.kategori = kategori;
        this.produsent = produsent;
        this.land = land;
        this.pris = pris;
        this.volum = volum;
        this.alkohol = alkohol;
        this.alkoholpris = alkoholpris;
        this.url = url;
    }

    @Override
    public String toString() {
        return
                "navn='" + navn + '\'' +
                        ", kategori='" + kategori + '\'' +
                        ", pris=" + pris +
                        ", volum=" + volum +
                        ", alkohol=" + alkohol +
                        ", alkoholpris=" + alkoholpris
                ;
    }
}
