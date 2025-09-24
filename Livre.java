public class Livre {
    private int id;
    private String auteur;
    private String nom;
    private String date;
    private int page;
    private String descriptif;
    private int available;

    public Livre(int id, String auteur, String nom, String date, int page, String descriptif, int available) {
        this.id = id;
        this.auteur = auteur;
        this.nom = nom;
        this.date = date;
        this.page = page;
        this.descriptif = descriptif;
        this.available = available;
    }
 // Getters and Setters
    public int getId() {
        return id;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public int getPage() {
        return page;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public int getAvailable() {
        return available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}