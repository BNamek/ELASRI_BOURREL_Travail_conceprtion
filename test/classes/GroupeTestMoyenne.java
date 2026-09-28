package classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupeTestMoyenne {

    //Test qui permet de voir si il y a bien une moyenne selon une matiere dans un groupe
    @Test
    void moyenneGroupeMat() throws Exception {
        //Creation de la formation
        Formation f = new Formation(1);
        //Ajout d'une matiere avec un coeff
        f.ajoutMatiere("Test",2);
        //Creation des étudiants
        Identite id1 = new Identite(1,"Nom1","Prenom1");
        Etudiant e1 = new Etudiant(id1,f);

        Identite id2 = new Identite(2,"Nom2","Prenom2");
        Etudiant e2 = new Etudiant(id2,f);

        Identite id3 = new Identite(3,"Nom2","Prenom2");
        Etudiant e3 = new Etudiant(id3,f);

        //Ajout de note differente aux etudiants
        e1.ajouterNote("Test",17);
        e2.ajouterNote("Test",20);
        e3.ajouterNote("Test",18);

        //Creation d'un groupe
        Groupe g = new Groupe(f);
        //Ajout des étudiant dans le groupe
        g.ajouterEtudiant(e1);
        g.ajouterEtudiant(e2);
        g.ajouterEtudiant(e3);

        //On test bien que la moyenne est bonne attention il y'a un coeff ici donc il faut bien faire toute les notes
        //fois le coeff diviser par le nombre de note fois le coeff
        assertEquals(18,g.moyenneGroupeMat("Test"));
    }

    /**
     * Je réalise un test qui permet de calculer la moyenne generale d'un groupe d'étudiant
     * Pour réaliser ce test j'ai choisi de mettre les coeffs de toutes les matières a 1
     * car mettre un coeff different pour chaque matiere allez etre trop long pour moi
     * a calculer j'ai donc fais un coeff de 1 pour allez plus vite
     */
    @Test
    void moyenneGroupeGenerale()throws Exception {
        //Creation de la formation
        Formation f = new Formation(1);
        //Ajout de plusieurs matiere avec un coeff
        f.ajoutMatiere("Test",1);
        f.ajoutMatiere("Test2",1);
        f.ajoutMatiere("Test3",1);
        //Creation des étudiants
        Identite id1 = new Identite(1,"Nom1","Prenom1");
        Etudiant e1 = new Etudiant(id1,f);

        Identite id2 = new Identite(2,"Nom2","Prenom2");
        Etudiant e2 = new Etudiant(id2,f);

        Identite id3 = new Identite(3,"Nom2","Prenom2");
        Etudiant e3 = new Etudiant(id3,f);

        //Ajout de note differente aux etudiants
        e1.ajouterNote("Test",17);
        e2.ajouterNote("Test",20);
        e3.ajouterNote("Test",18);
        //Premiere moyenne 18

        e1.ajouterNote("Test2",15);
        e2.ajouterNote("Test2",18);
        e3.ajouterNote("Test2",13);
        //Deuxieme moyenne 15

        e1.ajouterNote("Test3",10);
        e2.ajouterNote("Test3",12);
        e3.ajouterNote("Test3",15);
        //Troiseme moyenne 12
        //Moyenne generale 15

        //Creation d'un groupe
        Groupe g = new Groupe(f);
        //Ajout des étudiant dans le groupe
        g.ajouterEtudiant(e1);
        g.ajouterEtudiant(e2);
        g.ajouterEtudiant(e3);

        assertEquals(15,g.moyenneGroupeGenerale());
    }
}