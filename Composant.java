public abstract class Composant {
    protected String nom;

    public Composant(String nom) {
        this.nom = nom;
    }

    public abstract void afficher(String prefix);
    public abstract int getNombreFichiers();
    public abstract int getNombreRepertoires();
}
