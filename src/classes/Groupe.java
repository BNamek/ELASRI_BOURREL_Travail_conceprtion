package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Groupe {
    private List<Etudiant> etudiants;
    private Formation formation;

    /**
     * Constructeur qui initialise un groupe en fonction de la formation
     * @param f La formation qui définira le groupe
     */
    public Groupe(Formation f) {
        this.formation = f;
        this.etudiants = new ArrayList<Etudiant>();
    }

    /**
     * Méthode d'ajout d'un étudiant dans un groupe
     */
    public void ajouterEtudiant(Etudiant e) throws FormationDifferenteException {
        //On verifie si l'étudiant a la bonne formation
        if (!e.getFormation().equals(this.formation)) {
            throw new FormationDifferenteException();
        }

        //On vérifie que l'étudiant est déja dans la liste avec une boucle qui parcoure la liste
        //On initialise un boolean qui nous dira si l'étudiant est déja dans la liste
        boolean trouver = false;
        for (int i = 0; i < this.etudiants.size(); i++) {
            //Si l'étudiant n'est pas dans la liste on met le boolean a true
            if (this.etudiants.get(i).getIdentite().getNip() == e.getIdentite().getNip()) {
                trouver = true;
                //On arrete de parcourir la liste car l'étudiant a etait trouver
                return;
            }
        }

        //Si l'étudiant n'est pas déja dans la liste alors on l'ajoute
        if (trouver == false) {
            etudiants.add(e);
        }
        //Sinon on ne fait rien
    }

    /**
     * Methode permettant de supprimer une etudiant du groupe
     * @param e etudiant que l'on souhaite supprimer
     */
    public void supprimerEtudiant(Etudiant e) {
        // On parcourt les Etudiant du groupe
        for (int i = 0; i < this.etudiants.size(); i++) {
            // On verifie si letudiant que l'on souhaite a le meme Nip
            if (this.etudiants.get(i).getIdentite().getNip() == e.getIdentite().getNip()) {
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
    public void triAlpha() {
        // Collections.sort permet de trier une liste
        // Ici, on trie la liste des etudiants
        // en utilisant les règles définies dans EtudiantComparator
        Collections.sort(this.etudiants, new EtudiantComparator());
    }

    /**
     * Methode permettant de tier les etudiants de manière alphabetique inverse (Z-A)
     */
    public void triAntiAlpha() {
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

    /**
     * Methode qui calcule la moyenne du groupe selon une matière
     * @param mat La matière dont on veut la moyenne selon le groupe
     * @return Un double qui est la moyenne du groupe selon la matiere
     */
    public double moyenneGroupeMat(String mat) throws MatiereInexistanteException, Exception {
        //Si la matierea n'existe pas alors on throw une exception
        if (this.formation.matiereExiste(mat) == false) {
            throw new MatiereInexistanteException(mat);
        }
        //Initialiser la somme de toutes les moyennes en double
        double somme = 0;
        //Initialiser le nombre d'étudiant totale dans le groupe
        int taille = this.etudiants.size();

        //Je vais parcourir la liste d'étudiant
        for (int i = 0; i < this.etudiants.size(); i++) {
            //Je met chaque resultat de la moyenne d'un etudiant dans la somme
            somme += this.etudiants.get(i).calculerMoyenne(mat);
        }

        //Je crée une variable moyenne pour mettre le resultat dedans
        double moy;
        moy = somme / taille;

        //On utilise la méthode round pour qu'il n'y est pas un grand nombre apres la virgule donc il retournera
        //juste le nombre avant la virgule
        return Math.round(moy);
    }

    /**
     * Méthode qui permet de calculer la moyenne generale d'un groupe donc de toute les matiere que prend en compte le groupe
     * @return un double qui est la moyenne generale de tout le groupe
     */
    public double moyenneGroupeGenerale() throws Exception {
        //Je crée la variable somme qui prendra toute les moyenne de chaque matiere
        double somme = 0;
        //Je récupere toute les clef donc les matiere
        Set<String> clef = this.formation.getCollections().keySet();

        //Boucle qui parcours toute les matiere
        for (String mat : clef) {
            //Je met la méthode coder ci dessus pour calculer la moyenne de tout les étudiants selon la matière
            //vue que dans la méthode on fais une boucle pour trouver la moyenne nous n'avons pas besoin de la calculer
            //dans cette méthode
            somme += this.moyenneGroupeMat(mat);
        }

        //Variable qui va avoir la moyenne generale
        double moy;

        moy = somme/this.etudiants.size();
        //J'utilise round pour retourner le nombre avant la virgule seulment
        return Math.round(moy);
    }

}
