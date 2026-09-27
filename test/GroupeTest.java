import classes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GroupeTest {

    // Test d'ajouter un etudiant au groupe
    @Test
    public void test_Ajouter_Etudiant() throws Exception {
        Formation f = new Formation(1);
        Identite id = new Identite(1, "Bourrel", "Robin");
        Etudiant e = new Etudiant(id, f);
        Groupe g = new Groupe(f);
        g.ajouterEtudiant(e);
        assertEquals(1, g.getEtudiants().size(), "Le groupe devrait contenir un étudiant");
        assertEquals(1, g.getEtudiants().get(0).getIdentite().getNip(), "Le NIP de l'étudiant devrait être 1");
    }

    // Test d'ajout un etudiant deja dans le groupe
    @Test
    public void test_Ajouter_Etudiant_Deja_Present() throws Exception {
        Formation f = new Formation(1);
        Identite id = new Identite(1, "Bourrel", "Robin");
        Etudiant e = new Etudiant(id, f);
        Groupe g = new Groupe(f);
        g.ajouterEtudiant(e);
        g.ajouterEtudiant(e);
        assertEquals(1, g.getEtudiants().size(), "L'étudiant ne doit pas être ajouté deux fois");
    }

    // Test d'ajout d'un etudiant dans une formation differente
    @Test
    public void test_Ajouter_Etudiant_Formation_Differente() {
        Formation f1 = new Formation(1);
        Formation f2 = new Formation(2);
        Identite id = new Identite(1, "Bourrel", "Robin");
        Etudiant e = new Etudiant(id, f2);
        Groupe g = new Groupe(f1);
        try {
            g.ajouterEtudiant(e);
            fail("Une exception devait être levée");
        } catch (FormationDifferenteException ex) {
            assertEquals("La formation est differente", ex.getMessage());
        }
    }

    // test de supprimer un etudiant
    @Test
    public void test_Supprimer_Etudiant() throws Exception {
        Formation f = new Formation(1);
        Identite id = new Identite(1, "Bourrel", "Robin");
        Etudiant e = new Etudiant(id, f);
        Groupe g = new Groupe(f);
        g.ajouterEtudiant(e);
        g.supprimerEtudiant(e);
        assertEquals(0, g.getEtudiants().size(), "Le groupe devrait être vide");
    }

    // Test de la methode triAlpha
    @Test
    public void test_triAlpha() throws Exception {
        // creation d'une formation
        Formation f = new Formation(1);
        // Creation de trois Identite
        Identite id1 = new Identite(1, "Za", "martin");
        Identite id2 = new Identite(2, "Aa", "bernard");
        Identite id3 = new Identite(3, "Ba", "toto");

        // Creation de trois etudiant
        Etudiant e1 = new Etudiant(id1, f);
        Etudiant e2 = new Etudiant(id2, f);
        Etudiant e3 = new Etudiant(id3, f);

        // Creation du groupe des 3 etudiants
        Groupe g = new Groupe(f);

        // Ajout des trois etudiants
        g.ajouterEtudiant(e1);
        g.ajouterEtudiant(e2);
        g.ajouterEtudiant(e3);

        // Lancement de la methode de tri
        g.triAlpha();

        assertEquals("Aa", g.getEtudiants().get(0).getIdentite().getNom(), "Le nom du premier etudiant devrait etre Aa");
        assertEquals("Ba", g.getEtudiants().get(1).getIdentite().getNom(), "Le nom du deuxieme etudiant devrait etre Ba");
        assertEquals("Za", g.getEtudiants().get(2).getIdentite().getNom(), "Le nom du troisieme etudiant devrait etre Za");
    }

    // Test de la methode triAntiAlpha
    @Test
    public void test_TriAntiAlpha() throws Exception {
        // creation d'une formation
        Formation f = new Formation(1);
        // Creation de trois Identite
        Identite id1 = new Identite(1, "Aa", "martin");
        Identite id2 = new Identite(2, "Za", "bernard");
        Identite id3 = new Identite(3, "Ba", "toto");

        // Creation de trois etudiant
        Etudiant e1 = new Etudiant(id1, f);
        Etudiant e2 = new Etudiant(id2, f);
        Etudiant e3 = new Etudiant(id3, f);

        // Creation du groupe des 3 etudiants
        Groupe g = new Groupe(f);

        // Ajout des trois etudiants
        g.ajouterEtudiant(e1);
        g.ajouterEtudiant(e2);
        g.ajouterEtudiant(e3);

        // Lancement de la methode de tri
        g.triAntiAlpha();

        assertEquals("Za", g.getEtudiants().get(0).getIdentite().getNom(), "Le nom du premier etudiant devrait etre Za");
        assertEquals("Ba", g.getEtudiants().get(1).getIdentite().getNom(), "Le nom du deuxieme etudiant devrait etre Ba");
        assertEquals("Aa", g.getEtudiants().get(2).getIdentite().getNom(), "Le nom du troisieme etudiant devrait etre Aa");
    }
}
