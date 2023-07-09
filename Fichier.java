
public class Fichier extends Composant {
    public Fichier(String nom) {
        super(nom);
    }

    public void afficher(String prefix) {
        System.out.println(prefix + "├── " + nom);
    }

    public int getNombreFichiers() {
        return 1;
    }

    public int getNombreRepertoires() {
        return 0;
    }
}
