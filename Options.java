import java.io.File;
import java.text.DecimalFormat;

public class Options {
    public static void afficherAide() {
        System.out.println("Options disponibles :");
        System.out.println("-a : Affiche tous les fichiers, y compris les fichiers cachés.");
        System.out.println("-s : Affiche la taille totale du répertoire.");
        System.out.println("-f : Affiche le chemin complet des fichiers.");
    }

    public static void afficherFichiers(String chemin) {
        afficherFichiersEtRepertoires(chemin, "", true, false, false);
    }

    public static void afficherTailleTotale(String chemin) {
        long taille = calculerTailleTotale(chemin);
        String tailleFormatee = formaterTaille(taille);
        System.out.println("Taille totale du répertoire : " + tailleFormatee);
    }

    public static void afficherCheminsComplets(String chemin) {
        afficherFichiersEtRepertoires(chemin, "", false, false, true);
    }

    private static void afficherFichiersEtRepertoires(String chemin, String prefix, boolean afficherFichiers,
            boolean afficherRepertoires, boolean afficherCheminComplet) {
        File repertoire = new File(chemin);
        if (!repertoire.isDirectory()) {
            System.out.println("Chemin invalide ou répertoire introuvable.");
            return;
        }

        if (afficherCheminComplet) {
            System.out.println(prefix + "├── " + repertoire.getAbsolutePath());
        } else {
            System.out.println(prefix + "├── " + repertoire.getName());
        }

        File[] fichiers = repertoire.listFiles();
        if (fichiers != null) {
            for (int i = 0; i < fichiers.length; i++) {
                File fichier = fichiers[i];
                if (fichier.isFile()) {
                    if (afficherFichiers || (afficherFichiers && fichier.isHidden())) {
                        if (afficherCheminComplet) {
                            System.out.println(
                                    prefix + (i == fichiers.length - 1 ? "└── " : "├── ") + fichier.getAbsolutePath());
                        } else {
                            System.out
                                    .println(prefix + (i == fichiers.length - 1 ? "└── " : "├── ") + fichier.getName());
                        }
                    }
                } else if (fichier.isDirectory()) {
                    if (afficherRepertoires) {
                        if (afficherCheminComplet) {
                            System.out.println(
                                    prefix + (i == fichiers.length - 1 ? "└── " : "├── ") + fichier.getAbsolutePath());
                        } else {
                            System.out
                                    .println(prefix + (i == fichiers.length - 1 ? "└── " : "├── ") + fichier.getName());
                        }
                        afficherFichiersEtRepertoires(fichier.getAbsolutePath(),
                                prefix + (i == fichiers.length - 1 ? "    " : "│   "), afficherFichiers,
                                afficherRepertoires, afficherCheminComplet);
                    }
                }
            }
        }
    }

    private static long calculerTailleTotale(String chemin) {
        File repertoire = new File(chemin);
        if (!repertoire.isDirectory()) {
            return 0;
        }

        long tailleTotale = 0;

        File[] fichiers = repertoire.listFiles();
        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isFile()) {
                    tailleTotale += fichier.length();
                } else if (fichier.isDirectory()) {
                    tailleTotale += calculerTailleTotale(fichier.getAbsolutePath());
                }
            }
        }

        return tailleTotale;
    }

    private static String formaterTaille(long taille) {
        if (taille <= 0) {
            return "0 octets";
        }

        final String[] unite = { "octets", "Ko", "Mo", "Go", "To" };
        int index = (int) (Math.log10(taille) / Math.log10(1024));
        double tailleFormatee = taille / Math.pow(1024, index);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        return decimalFormat.format(tailleFormatee) + " " + unite[index];
    }
}
