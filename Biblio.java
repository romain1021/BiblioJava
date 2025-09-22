import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Biblio {
    private Livre[] biblio;

    public static void main(String[] args) {

        Biblio biblioApp = new Biblio();
        biblioApp.updateBiblio();
        biblioApp.afficherLivreListe();
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("Bienvenue dans votre bibliothèque !");
            System.out.println("Choissisez ce que vous souhaitez faire:");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Chercher un livre");
            System.out.println("4. Afficher la liste des livres");
            System.out.println("5. Quitter");
            int choix = scanner.nextInt();

            scanner.nextLine();  // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    // Ajouter un livre
                    biblioApp.afficherLivreListe();
                    System.out.println("--Ajout d'un livre--");
                    System.out.print("Entrez l'auteur: ");
                    String auteur = scanner.nextLine();
                    System.out.print("Entrez le nom du livre: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la date de publication: ");
                    String date = scanner.nextLine();
                    System.out.print("Entrez le nombre de pages: ");
                    int page = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez une description: ");
                    String description = scanner.nextLine();
                    int id = biblioApp.getFreeId();
                    int available = 1; // disponible à la création
                    Livre newLivre = new Livre(id, auteur, nom, date, page, description, available);
                    biblioApp.addLivre(newLivre);
                    break;
                case 2:
                    // Supprimer un livre
                    break;
                case 3:
                    System.out.print("Entrer le nom du livre a chercher: ");
                    String nomRecherche = scanner.nextLine();
                    biblioApp.chercherLivre(nomRecherche);
                    System.out.println("appuiez sur entrée pour continuer");
                    scanner.nextLine();

                    break;
                case 4:
                    // Afficher la liste des livres
                    biblioApp.afficherLivreListe();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Merci d'avoir utilisé votre bibliothèque !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }

        scanner.close();
    }





    //-------------- Fonctions de la classe biblio ----------------//
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
                    lines.add(line); // garder l'en-tête
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
                System.out.println(livre.getDescriptif());
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
                        found = true;
                        freeId++;
                        break;
                    }
                }
            } while (found);
        }
        return freeId;
    }
}



