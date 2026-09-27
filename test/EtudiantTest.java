import classes.CoefficientInvalideException;
import classes.Etudiant;
import classes.Formation;
import classes.Identite;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class EtudiantTest {
    @Test
    public void Test_Constructeur(){
        Identite identite = new Identite(1, "Bourrel", "Robin");
        Formation formation = new Formation(1);

        Etudiant etudiant = new Etudiant(identite, formation);

        assertEquals(identite, etudiant.getIdentite(), "L'identité devrait être 1, Bourrel, Robin");
        assertEquals(formation, etudiant.getFormation(), "La formation devrait etre 1");
    }
    @Test
    public void test_Ajouter_Note_Valide() throws Exception{
        Identite identite = new Identite(1, "Bourrel", "Robin");
        Formation formation = new Formation(1);
        formation.ajoutMatiere("Maths", 3);

        Etudiant etudiant = new Etudiant(identite, formation);
        etudiant.ajouterNote("Maths", 15);

        assertEquals(15, etudiant.getNotes("Maths").get(0), "La note devrait être égale a 15");
    }

    @Test
    public void test_Ajouter_Plusieurs_Notes_Meme_Matiere() throws Exception{
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        f.ajoutMatiere("Maths", 3);

        Etudiant et = new Etudiant(id, f);
        et.ajouterNote("Maths", 15);
        et.ajouterNote("Maths", 13);
        et.ajouterNote("Maths", 10);

        ArrayList<Integer> notesAttendus = new ArrayList<>();
        notesAttendus.add(15);
        notesAttendus.add(13);
        notesAttendus.add(10);

        assertEquals(notesAttendus, et.getNotes("Maths"), "Les notes ne correspondent pas");
    }

    @Test
    public void test_Note_Inferieure_A_Zero() throws CoefficientInvalideException {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        f.ajoutMatiere("Maths", 3);

        Etudiant et = new Etudiant(id, f);
        // la méthode fail qui permet de rendre un test faux si le resultat attendue n'est pas le bon
        try {
            et.ajouterNote("Maths", -1);
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("La note doit etre entre 0 et 20", e.getMessage());
        }
    }

    @Test
    public void test_Note_Superieure_A_Vingt() throws CoefficientInvalideException {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        f.ajoutMatiere("Maths", 3);

        Etudiant et = new Etudiant(id, f);
        try {
            et.ajouterNote("Maths", 30);
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("La note doit etre entre 0 et 20", e.getMessage());
        }
    }

    @Test
    public void test_Note_Egale_A_Vingt() throws Exception {
        Identite identite = new Identite(1, "Bourrel", "Robin");
        Formation formation = new Formation(1);
        formation.ajoutMatiere("Maths", 3);

        Etudiant etudiant = new Etudiant(identite, formation);
        etudiant.ajouterNote("Maths", 20);

        assertEquals(20, etudiant.getNotes("Maths").get(0), "La note devrait être égale a 20");
    }

    @Test
    public void test_Note_Egale_A_Zero() throws Exception {
        Identite identite = new Identite(1, "Bourrel", "Robin");
        Formation formation = new Formation(1);
        formation.ajoutMatiere("Maths", 3);

        Etudiant etudiant = new Etudiant(identite, formation);
        etudiant.ajouterNote("Maths", 0);

        assertEquals(0, etudiant.getNotes("Maths").get(0), "La note devrait être égale a 0");
    }

    @Test
    public void test_Matiere_Inexistante() {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);

        Etudiant et = new Etudiant(id, f);
        try {
            et.ajouterNote("Maths", 15);
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("La matière Maths n'existe pas.", e.getMessage());
        }
    }

    @Test
    public void test_GetNotes_Matiere_Inexistante() {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        Etudiant et = new Etudiant(id, f);
        try {
            et.getNotes("Maths");
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("La matière Maths n'existe pas.", e.getMessage());
        }
    }

    @Test
    public void test_calculerMoyenne()throws Exception {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        f.ajoutMatiere("Maths", 3);

        Etudiant et = new Etudiant(id, f);

        et.ajouterNote("Maths", 10);
        et.ajouterNote("Maths", 15);
        et.ajouterNote("Maths", 20);

        assertEquals(15.0, et.calculerMoyenne("Maths"), "La moyenne devrait être égale à 15");
    }

    @Test
    public void test_calculerMoyenne_Matiere_Inexistante() throws Exception {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);

        Etudiant et = new Etudiant(id, f);

        try {
            et.calculerMoyenne("Maths");
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("La matière Maths n'existe pas.", e.getMessage());
        }
    }

    @Test
    public void test_calculerMoyenne_Sans_Notes() throws Exception {
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        f.ajoutMatiere("Maths", 3);

        Etudiant et = new Etudiant(id, f);

        try {
            et.calculerMoyenne("Maths");
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("L'étudiant n'a aucune note dans cette matière", e.getMessage());
        }
    }

    @Test
    public void test_calculerMoyenneGenerale_Une_Matiere() throws Exception{
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);
        f.ajoutMatiere("Maths", 3);

        Etudiant et = new Etudiant(id, f);

        et.ajouterNote("Maths", 10);
        et.ajouterNote("Maths", 15);
        et.ajouterNote("Maths", 20);

        assertEquals(15.0, et.calculerMoyenneGenerale(), "La moyenne générale devrait être égale à 15");
    }

    @Test
    public void test_calculerMoyenneGenerale_Plusieurs_Matieres() throws Exception{
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);

        f.ajoutMatiere("Maths", 3);
        f.ajoutMatiere("Francais", 2);

        Etudiant et = new Etudiant(id, f);

        // Moyenne Maths
        et.ajouterNote("Maths", 10);
        et.ajouterNote("Maths", 10);

        // Moyenne Francais
        et.ajouterNote("Francais", 15);
        et.ajouterNote("Francais", 15);

        // Moyenne generale = 12
        assertEquals(12, et.calculerMoyenneGenerale(), "La moyenne générale devrait etre 12");
    }

    @Test
    public void test_calculerMoyenneGenerale_Sans_Notes() throws Exception{
        Identite id = new Identite(1, "Bourrel", "Robin");
        Formation f = new Formation(1);

        f.ajoutMatiere("Maths", 3);
        f.ajoutMatiere("Francais", 2);

        Etudiant et = new Etudiant(id, f);

        try {
            et.calculerMoyenneGenerale();
            fail("Une exception devait être levée");
        } catch (Exception e) {
            assertEquals("L'étudiant n'a aucune note", e.getMessage());
        }
    }
}
