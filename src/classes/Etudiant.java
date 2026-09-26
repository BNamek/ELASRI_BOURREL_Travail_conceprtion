package classes;

import java.util.*;

public class Etudiant {
    private Identite identite;
    private Formation formation;
    //Pour avoir plusieurs notes pour une seule matière, je mets une liste d'integer dans la partie valeur
    private Map<String, ArrayList<Integer>> resultat;

    /**
     * Creation d'un constructeur qui intialise un étudiant
     *
     * @param id Initialisation de l'identiter d'un étudiant donc crée son identité avant de crée l'étudiant
     * @param f  Initialisation de la formation auquel l'étudiant apartient
     */
    public Etudiant(Identite id, Formation f) {
        this.identite = id;
        this.formation = f;
        this.resultat = new HashMap<String, ArrayList<Integer>>();
    }

    public void ajouterNote(String mat, int note) throws MatiereInexistanteException, Exception {
        //Je vérifie que la matière éxiste
        //Changement de return, car la méthode vas throw une exception
        if (formation.matiereExiste(mat) == false) {
            //Si elle n'existe pas on throw une erreur
            throw new MatiereInexistanteException(mat);
            //On arrete la méthode
        }

        //Je vérifie que la note se situe bien entre 0 et 20
        if (note < 0 || note > 20) {
            //Si la note n'est pas dans l'intervalle 0 et 20 on throw une erreur
            throw new Exception("La note doit etre entre 0 et 20");
            //On arrete la méthode
        }

        //On récupere les notes qui sont déja présente dans cette matière
        ArrayList<Integer> notesTot = this.resultat.get(mat);
        //Si la matière n'a pas de note on lui rajoute une liste de note avec la note
        if (notesTot == null) {
            //On crée un nouvelle liste vide
            ArrayList<Integer> notes = new ArrayList<>();
            //On ajoute la note dans cette liste
            notes.add(note);
            //Puis on l'ajoute dans la hashmap avec la matière et la nouvelle liste
            this.resultat.put(mat, notes);
            //On quitte ensuite la méthode
            return;
        }
        //Sinon on ajoute juste la note dans la liste note de la matière
        notesTot.add(note);
    }

    /**
     * La méthode calculerMoyenne permet de calculer la moyenne d'un etudiant dans une matière donnée
     *
     * @param mat La matière dans laquelle on veux la moyenne de l'etudiant
     * @return Un double qui est la moyenne de l'étudiant dans la matière donné
     */
    public double calculerMoyenne(String mat) throws MatiereInexistanteException, Exception {
        if (!this.formation.matiereExiste(mat)) {
            throw new MatiereInexistanteException(mat);
        }

        ArrayList<Integer> listNote = this.resultat.get(mat);
        if (listNote.isEmpty()) {
            throw new Exception("L'étudiant n'a aucune note dans cette matière");
        }
        int somme = 0;

        for (int i = 0; i < listNote.size(); i++) {
            somme += listNote.get(i);
        }
        return (double) somme / listNote.size();
    }

    public double calculerMoyenneGenerale()throws Exception {

        double sommeMoyennes = 0;
        int sommeCoefficients = 0;
        for (String mat : resultat.keySet()) {
            double moyenne = calculerMoyenne(mat);
            int coefficient = formation.getCoeff(mat);

            sommeMoyennes += moyenne * coefficient;
            sommeCoefficients += coefficient;
        }

        if (sommeCoefficients == 0) {
            throw new Exception("L'étudiant n'a aucune note");
        }
        return sommeMoyennes / sommeCoefficients;
    }

    public Formation getFormation() {
        return formation;
    }

    public Identite getIdentite() {
        return identite;
    }

    public ArrayList<Integer> getNotes(String mat) throws MatiereInexistanteException {

        if (!formation.matiereExiste(mat)) {
            throw new MatiereInexistanteException(mat);
        }

        return resultat.get(mat);
    }
}
