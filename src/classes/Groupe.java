package classes;

import java.util.ArrayList;
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

}
