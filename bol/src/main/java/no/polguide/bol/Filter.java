package no.polguide.bol;

public class Filter {
    public String navn = "";
    public String beskrivelse = "";
    public String kategori = "";
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

    public boolean navn(String navn){return navn.contains(this.navn);}
    public boolean beskrivelse(String beskrivelse){return beskrivelse.contains(this.beskrivelse);}
    public boolean kategori(String kategori){return kategori == "" || this.kategori.equals(kategori);}
}
