import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Biblio {
    private Livre[] biblio;
    //-------------- Methode de la classe biblio ----------------//
    public Livre[] updateBiblio() {
        List<Livre> livres = new ArrayList<>();
        String csvFile = "bibliotheque.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // ignore l'en-tête
                }
                String[] data = line.split(cvsSplitBy);
                if (data.length >= 7) {
                    int id = Integer.parseInt(data[0]);
                    String auteur = data[1];
                    String nom = data[2];
                    String date = data[3];
                    int page = Integer.parseInt(data[4]);
                    String descriptif = data[5];
                    int available = Integer.parseInt(data[6]);
                    livres.add(new Livre(id, auteur, nom, date, page, descriptif, available));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        biblio = livres.toArray(new Livre[0]);
        return biblio;
    }

    public void addLivre(Livre livre) {
        // ajoute un livre dans le fichier CSV
        StringBuilder sb = new StringBuilder();
        sb.append(livre.getId()).append(",");
        sb.append(livre.getAuteur()).append(",");
        sb.append(livre.getNom()).append(",");
        sb.append(livre.getDate()).append(",");
        sb.append(livre.getPage()).append(",");
        sb.append(livre.getDescriptif()).append(",");
        sb.append(livre.getAvailable()).append("\n");
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("bibliotheque.csv", true))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteLivre(int id) {
        String csvFile = "bibliotheque.csv";
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        java.io.BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    lines.add(line);
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 1 && Integer.parseInt(data[0]) != id) {
                    lines.add(line);
                }
            }
            br.close();
            bw = new java.io.BufferedWriter(new java.io.FileWriter(csvFile, false));
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chercherLivre(String nom) {
        boolean found = false;
        for (Livre livre : biblio) {
            if (livre.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Livre trouve :");
                System.out.println("ID: " + livre.getId());
                System.out.println("Auteur: " + livre.getAuteur());
                System.out.println("Nom: " + livre.getNom());
                System.out.println("Date: " + livre.getDate());
                System.out.println("Pages: " + livre.getPage());
                System.out.println("Description: " + livre.getDescriptif());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Aucun livre trouvé avec le nom \"" + nom + "\".");
        }
    }

    public void afficherLivreListe() {
        updateBiblio();
        for (Livre livre : biblio) {
            if (livre.getAvailable() == 1) {
                System.out.println(livre.getId() + " " + livre.getNom());
            }
        }
    }

    public int getFreeId() {
        int freeId = 1;
if (biblio != null) {
    boolean found;
    do {
        found = false;
        for (Livre livre : biblio) {
            if (livre.getId() == freeId) {
                freeId++;
                found = true;
                break;
            }
        }
    } while (found);
}
return freeId;
    }


    public void chercherLivreParAuteur(String auteurRecherche) {
        for (Livre livre : biblio) {
            if (livre.getAuteur() == auteurRecherche) {
                System.out.println("ID: " + livre.getId());
                System.out.println("Auteur: " + livre.getAuteur());
                System.out.println("Nom: " + livre.getNom());
                System.out.println("Date: " + livre.getDate());
                System.out.println("Pages: " + livre.getPage());
                System.out.println("Description: " + livre.getDescriptif());
            }
        }

    }

    public int countLivre() {
        return biblio.length;
    }

    public Livre[] getLivreListe() {
        return biblio;
    }
}



