import java.io.File;
import java.util.Scanner;

// Main.java
public class Main {
    public static void main(String[] args) {
        System.out.print("Ce programme est une implémentation de la commande tree.");
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine(); // Consomme la ligne précédente

            System.out.print("Veillez entrer le chemin vers le répertoire dont vous voulez afficher l'arborescence: ");
            String chemin = scanner.nextLine();

            System.out.print("Veuillez entrer les options souhaitées (séparées par des espaces) : ");
            String optionsSaisies = scanner.nextLine();
            String[] options = optionsSaisies.split(" ");

            for (String option : options) {
                switch (option) {
                    case "-a":
                        Options.afficherFichiers(chemin);
                        break;

                    case "-s":
                        Options.afficherTailleTotale(chemin);
                        break;

                    case "-f":
                        Options.afficherCheminsComplets(chemin);
                        break;

                    default:
                        System.out.println("Option invalide : " + option);
                        break;
                }
            }

            File repertoire = new File(chemin);
            if (!repertoire.isDirectory()) {
                System.out.println("Chemin invalide ou répertoire introuvable.");
                scanner.close();
                return;
            }
            Repertoire racine = construireArborescence(chemin);

            // Étape 5 : Affichage de l'arborescence
            System.out.println(chemin);
            racine.afficher("");
            System.out.println();
            System.out.println(
                    racine.getNombreRepertoires() + " Repertoire(s), " + racine.getNombreFichiers() + " fichier(s)");
        }
    }

    private static Repertoire construireArborescence(String chemin) {
        Repertoire racine = new Repertoire(new File(chemin).getName());
        ajouterFichiersEtRepertoires(new File(chemin), racine);
        return racine;
    }

    private static void ajouterFichiersEtRepertoires(File dossier, Repertoire parent) {
        File[] fichiers = dossier.listFiles();
        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isFile()) {
                    parent.ajouter(new Fichier(fichier.getName()));
                } else if (fichier.isDirectory()) {
                    Repertoire sousRepertoire = new Repertoire(fichier.getName());
                    parent.ajouter(sousRepertoire);
                    ajouterFichiersEtRepertoires(fichier, sousRepertoire);
                }
            }
        }
    }
}
