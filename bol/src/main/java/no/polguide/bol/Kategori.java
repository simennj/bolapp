package no.polguide.bol;

import com.google.gson.annotations.SerializedName;

public enum Kategori {

    @SerializedName("ol")
    ol("Øl"),

    @SerializedName("sprit")
    sprit("Sprit"),

    @SerializedName("aperitif-dessert")
    aperitif_dessert("Diverse"),

    @SerializedName("mousserande-viner")
    mousserande_viner("Musserende vin"),

    @SerializedName("roda-viner")
    roda_viner("Rødvin"),

    @SerializedName("cider-och-blanddrycker")
    cider_och_blanddrycker("Cider og Blandedrikker"),

    @SerializedName("vita-viner")
    vita_viner("Hvitvin"),

    @SerializedName("alkoholfritt")
    alkoholfritt("Alkoholfritt"),

    @SerializedName("roseviner")
    roseviner("Rosévin");

    private String kategori;

    Kategori(String kategori) {
        this.kategori = kategori;
    }

    public static Kategori getKategori(String kategori) {
        switch (kategori) {
            case "ol": return Kategori.ol;
            case "sprit": return Kategori.sprit;
            case "aperitif-dessert": return Kategori.aperitif_dessert;
            case "mousserande-viner": return Kategori.mousserande_viner;
            case "roda-viner": return Kategori.roda_viner;
            case "cider-och-blanddrycker": return Kategori.cider_och_blanddrycker;
            case "vita-viner": return Kategori.vita_viner;
            case "alkoholfritt": return Kategori.alkoholfritt;
            case "roseviner": return Kategori.roseviner;
        }
        return Kategori.sprit;
    }

    @Override
    public String toString() {
        return kategori;
    }
}
