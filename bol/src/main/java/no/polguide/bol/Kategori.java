package no.polguide.bol;

import com.google.gson.annotations.SerializedName;

public enum Kategori {

    @SerializedName("ol")
    ol("ol"),

    @SerializedName("sprit")
    sprit("sprit"),

    @SerializedName("aperitif-dessert")
    aperitif_dessert("aperitif-dessert"),

    @SerializedName("mousserande-viner")
    mousserande_viner("mousserande-viner"),

    @SerializedName("roda-viner")
    roda_viner("roda-viner"),

    @SerializedName("cider-och-blanddrycker")
    cider_och_blanddrycker("cider-och-blanddrycker"),

    @SerializedName("vita-viner")
    vita_viner("vita-viner"),

    @SerializedName("alkoholfritt")
    alkoholfritt("alkoholfritt"),

    @SerializedName("roseviner")
    roseviner("roseviner");

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
