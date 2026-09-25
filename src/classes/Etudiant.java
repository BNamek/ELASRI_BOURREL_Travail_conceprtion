package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Etudiant {
    private Identite identite;
    private Formation formation;
    //Pour avoir plusieurs notes pour une seule matière, je mets une liste d'integer dans la partie valeur
    private Map<String, ArrayList<Integer>> resultat;

    /**
     * Creation d'un constructeur qui intialise un étudiant
     * @param id Initialisation de l'identiter d'un étudiant donc crée son identité avant de crée l'étudiant
     * @param f Initialisation de la formation auquel l'étudiant apartient
     */
    public Etudiant(Identite id,Formation f){
        this.identite = id;
        this.formation = f;
        this.resultat = new HashMap<String, ArrayList<Integer>>();
    }

    public void ajouterNote(String mat,int note) throws Exception {
        //Je vérifie que la matière éxiste
        //Changement de return, car la méthode vas throw une exception
        if (formation.getCoeff(mat) == -1){
            //Si elle n'existe pas on throw une erreur
            throw new Exception("La matière n'éxiste pas");
            //On arrete la méthode
            return;
        }

        //Je vérifie que la note se situe bien entre 0 et 20
        if (note<0 || note>20) {
            //Si la note n'est pas dans l'intervalle 0 et 20 on throw une erreur
            throw new Exception("La note doit etre entre 0 et 20");
            //On arrete la méthode
            return;
        }

        for(this.resultat : r){

        }

    }
}
