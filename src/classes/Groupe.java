package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe {
    private List<Etudiant> etudiants;
    private Formation formation;

    /**
     * Constructeur qui initialise un groupe en fonction de la formation
     * @param f La formation qui définira le groupe
     */
    public Groupe(Formation f){
        this.formation = f;
        this.etudiants = new ArrayList<Etudiant>();
    }

    /**
     * Méthode d'ajout d'un étudiant dans un groupe
     */
    public void ajouterEtudiant(Etudiant e) throws FormationDifferenteException {
        //On verifie si l'étudiant a la bonne formation
        if (!e.getFormation().equals(this.formation)){
            throw new FormationDifferenteException();
        }

        //On vérifie que l'étudiant est déja dans la liste avec une boucle qui parcoure la liste
        //On initialise un boolean qui nous dira si l'étudiant est déja dans la liste
        boolean trouver = false;
        for (int i = 0; i < this.etudiants.size(); i++) {
            //Si l'étudiant n'est pas dans la liste on met le boolean a true
            if (this.etudiants.get(i).getIdentite().getNip() == e.getIdentite().getNip()){
                trouver = true;
                //On arrete de parcourir la liste car l'étudiant a etait trouver
                return;
            }
        }

        //Si l'étudiant n'est pas déja dans la liste alors on l'ajoute
        if (trouver == false){
            etudiants.add(e);
        }
        //Sinon on ne fait rien
    }

    /**
     * Methode permettant de supprimer une etudiant du groupe
     * @param e etudiant que l'on souhaite supprimer
     */
    public void supprimerEtudiant(Etudiant e){
        // On parcourt les Etudiant du groupe
        for (int i=0 ; i<this.etudiants.size(); i++){
            // On verifie si letudiant que l'on souhaite a le meme Nip
            if (this.etudiants.get(i).getIdentite().getNip() == e.getIdentite().getNip()){
                // Si on trouve l'étudiant, on le supprime de la liste
                this.etudiants.remove(i);
                // Puis on met fin a la methode
                return;
            }
        }
    }

    /**
     * Methode permettant de tier les etudiants de manière alphabetique (A-Z)
     */
    public void triAlpha(){
        // Collections.sort permet de trier une liste
        // Ici, on trie la liste des etudiants
        // en utilisant les règles définies dans EtudiantComparator
        Collections.sort(this.etudiants, new EtudiantComparator());
    }

    /**
     * Methode permettant de tier les etudiants de manière alphabetique inverse (Z-A)
     */
    public void triAntiAlpha(){
        // On trie d'abord la liste des etudiants par ordre alphabetique
        // grâce aux règles définies dans EtudiantComparator
        Collections.sort(this.etudiants, new EtudiantComparator());
        // On inverse ensuite l'ordre de la liste
        // pour obtenir un tri de Z vers A
        Collections.reverse(this.etudiants);
    }

    /**
     * Getter permettant d'avoir la liste d'etudiant du group
     * @return la liste d'etudiant
     */
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    /**
     * Getter permettant d'obtenir la formation du groupe
     * @return la formaation du groupe
     */
    public Formation getFormation() {
        return formation;
    }
}
