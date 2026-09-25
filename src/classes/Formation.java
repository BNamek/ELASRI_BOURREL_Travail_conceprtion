package classes;

import java.util.HashMap;

/**
 * Classe répresentant Une formation
 */
public class Formation {
    // Attributs
    private int id; // Identifiant de la formation
    private HashMap<String, Integer> collections; // Collection de matières assignées a un coefficient


    // Constructeur de Formation par son identifiant
    // En creent la collections sans rien dedans
    public Formation(int id) {
        this.id = id;
        this.collections = new HashMap<>();
    }

    /**
     * méthode ajoutMatiere permettant d'ajouter une matiere a notre collections
     * @param mat nom de la matiere que l'on souhaite ajoute
     * @param coeff coefficient de la matiere qu'on veut ajouter
     */
    public void ajoutMatiere(String mat, int coeff){
        // Verifications de si matiere existe deja dans la collections
        if (collections.containsKey(mat)){
            System.out.println("La matière existe déjà !");
            // Si oui renvoie un message
        } else{
            // Ajout de la matière dans la collections
            collections.put(mat, coeff);
        }
    }

    /**
     * Méthode suppMatiere permettant de supprimer une matiere de la collections
     * @param mat matière que l'on souhaite supprimer
     */
    public void suppMatiere(String mat){
        // Verif si la matiere existe pas
        if (!collections.containsKey(mat)){
            System.out.println("La matière n'existe pas");
            // Si elle existe pas renvoie un message
        } else{
            collections.remove(mat);
            // Suppression de la matiere
        }
    }

    /**
     * Méthode permettant d'avoir le coefficient pour une matière
     * @param mat matière que l'on souhaite avoir son coefficient
     * @return le coefficient de la matière, si elle existe pas retourner -1
     */
    public int getCoeff(String mat){
        if (!collections.containsKey(mat)){
            return -1;
        }
        return collections.get(mat);
    }
}
