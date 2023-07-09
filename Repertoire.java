import java.util.ArrayList;
import java.util.List;

// Repertoire.java
public class Repertoire extends Composant {
    private List<Composant> enfants;

    public Repertoire(String nom) {
        super(nom);
        enfants = new ArrayList<>();
    }

    public void ajouter(Composant composant) {
        enfants.add(composant);
    }

    public void supprimer(Composant composant) {
        enfants.remove(composant);
    }

    public Composant getEnfant(int index) {
        return enfants.get(index);
    }

    public void afficher(String prefix) {
        System.out.println(prefix + "├── " + nom);
        for (int i = 0; i < enfants.size(); i++) {
            Composant enfant = enfants.get(i);
            if (i == enfants.size() - 1) {
                enfant.afficher(prefix + "    ");
            } else {
                enfant.afficher(prefix + "│   ");
            }
        }
    }
    

    public int getNombreFichiers() {
        int total = 0;
        for (Composant enfant : enfants) {
            total += enfant.getNombreFichiers();
        }
        return total;
    }

    public int getNombreRepertoires() {
        int total = 0;
        for (Composant enfant : enfants) {
            if (enfant instanceof Repertoire) {
                total += 1 + enfant.getNombreRepertoires();
            }
        }
        return total;
    }
    
}
