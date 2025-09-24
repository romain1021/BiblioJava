import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Biblio biblioApp = new Biblio();
        biblioApp.updateBiblio();
        biblioApp.afficherLivreListe();
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("--------------------Quel choix voulez vous faire --------------------");
            System.out.println("Bienvenue dans votre bibliothèque !");
            System.out.println("Choissisez ce que vous souhaitez faire:");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Chercher un livre");
            System.out.println("4. Afficher la liste des livres");
            System.out.println("5. Chercher les livres d'un auteur");
            System.out.println("6. Afficher la moyenne du nombre de pages des livres");
            System.out.println("7. Quitter");
            System.out.print("-------Votre choix:------");
            int choix = scanner.nextInt();

            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter un livre
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
                    biblioApp.updateBiblio();
                    break;
                case 2:
                    // Supprimer un livre
                    System.out.print("quel est l'identifiant du liovre a supprimer: ");
                    int idLivreASupr = scanner.nextInt();
                    scanner.nextLine();
                    biblioApp.deleteLivre(idLivreASupr);
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
                    biblioApp.updateBiblio();
                    biblioApp.afficherLivreListe();
                    System.out.println("appuiez sur entrée pour continuer");
                    scanner.nextLine();
                    break;
                case 5:
                // afficher les livre d'un auteur.
                System.out.print("Entrer le nom de l'auteur a chercher: ");
                String auteurRecherche = scanner.nextLine();
                biblioApp.chercherLivreParAuteur(auteurRecherche);
                System.out.println("appuiez sur entrée pour continuer");
                scanner.nextLine();
                break;

                case 6:
                    int totalPages = 0;
                    int totalLivres = biblioApp.countLivre();
                        for (Livre livre : biblioApp.getLivreListe()) {
                            totalPages += livre.getPage();
                        }
                        double moyenne = (double) totalPages / totalLivres;
                        System.out.println("La moyenne du nombre de pages des livres dans la bibliothèque est : " + moyenne);
                        System.out.println("Appuyez sur entrée pour continuer");
                        scanner.nextLine();
                        break;
    
                    case 7:
                        isRunning = false;
                        System.out.println("See you soon 🧌5");
                        break;
    
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            }
    
            scanner.close();
        }
}
