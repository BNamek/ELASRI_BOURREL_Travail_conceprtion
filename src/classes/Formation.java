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
    public void ajoutMatiere(String mat, int coeff) throws CoefficientInvalideException{
        if (coeff < 0){
            throw new CoefficientInvalideException(coeff);
        }
        // Verifications de si matiere existe deja dans la collections
        if (matiereExiste(mat)){
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
    public void suppMatiere(String mat) throws MatiereInexistanteException{
        // Verif si la matiere existe pas
        if (!matiereExiste(mat)){
            throw new MatiereInexistanteException(mat);
            // Si elle existe pas renvoie un message
        }
        collections.remove(mat);
        // Suppression de la matiere
    }

    /**
     * Méthode permettant d'avoir le coefficient pour une matière
     * @param mat matière que l'on souhaite avoir son coefficient
     * @return le coefficient de la matière, si elle existe pas retourne d'une exception
     */
    public int getCoeff(String mat) throws MatiereInexistanteException {
        if (!matiereExiste(mat)){
            // Exception si la matiere n'existe pas
            throw new MatiereInexistanteException(mat);
        }
        return collections.get(mat);
    }

    /**
     * Méthode permettant de vérifier si une matière existe dans la formation
     * @param mat matière que l'on souhaite vérifier
     * @return true si la matière existe, false sinon
     */
    public boolean matiereExiste(String mat) {
        return collections.containsKey(mat);
    }

    /**
     * Methode permettant de verifier si la formation est egale a la formation passé en parametre
     * @param obj reference de l'objet que l'on souhaite compare
     * @return true si les deux formations sont égales, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        // On verifie si l'objet passé en parametre a la meme reference que la formation
        if (this == obj) {
            return true;
        }
        // On verifie si l'objet en parametre n'est pas null ou net pas de la meme nature quel l'autre objet
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // On transforme l'objet en Formation
        Formation formation = (Formation) obj;
        // On compare l'identifiant
        if (this.id != formation.id) {
            return false;
        }
        // On compare les matieres
        if (!this.collections.equals(formation.collections)) {
            return false;
        }
        // Enfin on est sur que c'est le même donc on return true
        return true;
    }

    /**
     * Méthode permettant de générer le hashCode d'une formation
     * à partir de son identifiant et de sa collection de matières
     * @return le hashCode de la formation
     */
     @Override
     public int hashCode() {
         // On initialise avec un nombre premier
         int resultat = 13;

         // On ajoute l'identifiant de la formation
         resultat = 13 * resultat + id;

         // On ajoute le hashCode de la collection de matières
         resultat = 13 * resultat + collections.hashCode();

         return resultat;
     }
}
