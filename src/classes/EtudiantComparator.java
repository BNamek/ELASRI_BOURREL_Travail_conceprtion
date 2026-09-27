package classes;

import java.util.Comparator;

// classe permettant de comparer deux etudiants selon leur nom pour trier d'ordre alphabetique
public class EtudiantComparator implements Comparator<Etudiant> {
    /**
     * Méthode permettant de comparer deux étudiants selon leur nom.
     * @param e1 premier étudiant à comparer
     * @param e2 deuxième étudiant à comparer
     * @return une valeur négative si e1 est avant e2,
     *         0 si les deux noms sont égaux,
     *         une valeur positive si e1 est après e2
     */
    @Override
    public int compare(Etudiant e1, Etudiant e2) {
        // On recupere le premier nom de letudiant 1
        String nom1 = e1.getIdentite().getNom();
        // On recupere le deuxieme nom de letudiant 2
        String nom2 = e2.getIdentite().getNom();
        // On compare le nom du premier étudiant avec celui du deuxième
        return nom1.compareTo(nom2);
    }
}