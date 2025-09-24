# Bibliothèque en Java

Ce programme propose une interface interactive pour gérer une bibliothèque, entièrement codée en Java avec une base de données au format CSV.

### Fonctionnalités

- **Ajouter un livre**  
    Ajoutez de nouveaux livres à la bibliothèque.

- **Rechercher un livre par nom**  
    Trouvez rapidement un livre grâce à son titre.

- **Consulter les livres d’un auteur**  
    Affichez tous les ouvrages d’un auteur spécifique.

- **Calculer la moyenne des pages**  
    Obtenez la moyenne du nombre de pages des livres présents dans la bibliothèque.


## Arborescence des classes

```
Main
│
├── Biblio
│     └── Livre
```

### Description des classes

- **Main** : Point d'entrée du programme, gère l'interface utilisateur et les choix du menu.
- **Biblio** : Gère la collection de livres, l'accès au fichier CSV, l'ajout, la suppression, la recherche et l'affichage des livres.
- **Livre** : Représente un livre (id, auteur, nom, date, pages, descriptif, disponibilité) avec ses getters/setters.





