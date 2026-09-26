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
        double res;
        //Verification que la matière existe
        if (!this.formation.matiereExiste(mat)) {
            //Si la matière n'existe pas on throw une exception
            throw new MatiereInexistanteException(mat);
        }

        //On crée une liste qui va contenire les notes de la matière
        ArrayList<Integer> listNote = this.resultat.get(mat);
        if (listNote.isEmpty()) {
            //Si la liste est vide alors on throw une exception
            throw new Exception("L'étudiant n'a aucune note dans cette matière");
        }
        int somme = 0;
        //Une boucle for pour parcourir la liste est l'ajouter dans la somme de toute les notes
        for (int i = 0; i < listNote.size(); i++) {
            somme += listNote.get(i);
        }

        //On réalise le calcule de la moyenne
        res = (double) somme / listNote.size();
        return res;
    }

    /**
     * La méthode calculerMoyenneGenerale permet de calculer la moyenne
     * générale d'un étudiant en prenant en compte les coefficients
     * de chaque matière
     *
     * @return La moyenne générale de l'étudiant
     */
    public double calculerMoyenneGenerale() throws Exception {
        double res;
        double sommeMoyennes = 0;
        int sommeCoefficients = 0;

        // On parcourt toutes les matières dans lesquelles l'étudiant a des notes
        for (String mat : this.resultat.keySet()) {
            // On calcule la moyenne de l'étudiant dans cette matière
            double moyenne = calculerMoyenne(mat);
            // On récupère le coefficient de la matière
            int coefficient = formation.getCoeff(mat);

            // On multiplie la moyenne par son coefficient et on ajoute le résultat
            sommeMoyennes += moyenne * coefficient;
            // On ajoute le coefficient à la somme des coefficients
            sommeCoefficients += coefficient;
        }

        // On vérifie que l'étudiant possède au moins une note
        if (sommeCoefficients == 0) {
            throw new Exception("L'étudiant n'a aucune note");
        }
        res = sommeMoyennes / sommeCoefficients;
        return res;
    }

    public Formation getFormation() {
        return this.formation;
    }

    public Identite getIdentite() {
        return this.identite;
    }

    public ArrayList<Integer> getNotes(String mat) throws MatiereInexistanteException {

        if (!formation.matiereExiste(mat)) {
            throw new MatiereInexistanteException(mat);
        }

        return this.resultat.get(mat);
    }
}
