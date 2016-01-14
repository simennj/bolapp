package no.polguide.bol;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public String navn = "";
    public String beskrivelse = "";
    public List<Kategori> kategorier = new ArrayList<>();
    public String produsent = "";
    public String land = "";
    public double pris_min = 0;
    public double pris_max = 0;
    public double volum_min = 0;
    public double volum_max = 0;
    public double alkohol_min = 0;
    public double alkohol_max = 0;
    public double alkoholpris_min = 0;
    public double alkoholpris_max = 0;

    public boolean navn(String navn) {
        return navn.contains(this.navn);
    }

    public boolean beskrivelse(String beskrivelse) {
        return beskrivelse.contains(this.beskrivelse);
    }

    public boolean kategori(Kategori kategori) {
        return this.kategorier.size() == 0 || this.kategorier.contains(kategori);
    }

    public boolean produsent(String produsent) {
        return produsent.contains(this.produsent);
    }

    public boolean land(String land) {
        return land.contains(this.land);
    }

    public boolean pris(double pris) {
        return (this.pris_min == 0 || pris >= this.pris_min) && (this.pris_max == 0 || pris <= this.pris_max);
    }

    public boolean volum(double volum) {
        return (this.volum_min == 0 || volum >= this.volum_min) && (this.volum_max == 0 || volum <= this.volum_max);
    }

    public boolean alkohol(double alkohol) {
        return (this.alkohol_min == 0 || alkohol >= this.alkohol_min) && (this.alkohol_max == 0 || alkohol <= this.alkohol_max);
    }

    public boolean alkoholpris(double alkoholpris) {
        return (this.alkoholpris_min == 0 || alkoholpris >= this.alkoholpris_min) && (this.alkoholpris_max == 0 || alkoholpris <= this.alkoholpris_max);
    }

    public boolean accepted(Product item) {
                //System.out.println(navn(item.navn));
                //System.out.println(beskrivelse(item.beskrivelse));
                //System.out.println(kategori(item.kategori));
                //System.out.println(produsent(item.produsent));
                //System.out.println(land(item.land) && pris(item.pris));
                //System.out.println(volum(item.volum) && alkohol(item.alkohol));
                //System.out.println(alkoholpris(item.alkoholpris));
        return navn(item.navn) &&
                beskrivelse(item.beskrivelse) &&
                kategori(item.kategori) &&
                produsent(item.produsent) &&
                land(item.land) && pris(item.pris) &&
                volum(item.volum) && alkohol(item.alkohol) &&
                alkoholpris(item.alkoholpris);
    }

}
