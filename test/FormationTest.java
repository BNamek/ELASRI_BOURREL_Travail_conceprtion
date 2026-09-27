import classes.Formation;
import classes.MatiereInexistanteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FormationTest {

    //Ajout d'une matière dans la collection, avec tout les attributs requis
    @Test
    public void test_Ajout_Matiere() {
        //Création d'une formaiton
        Formation f = new Formation(1);
        //Ajouter la matiere et sont coefficient
        f.ajoutMatiere("Français", 2);
        //Tester si la matiere est bien dans la formation
        assertEquals(true, f.matiereExiste("Français"), "La matière français doit etre dans la liste");
    }

    //Ajout d'une matière en mettant un coeff négatif
    @Test
    public void test_Ajout_Matiere_CoeffNegatif() {
        //Création d'une formaiton
        Formation f = new Formation(1);
        //On fait un try catch pour pouvoir s'assurer que la méthode throw bien une erreur
        //J'ai cherché sur internet comment faire pour tester les erreurs dans les classes de tests
        //J'ai trouvé qu'on pouvait utiliser les try catch avec un asserEquals dans le catch
        try {
            //Ajout de la matiere doit retourner une erreur car le coeff est négatif
            f.ajoutMatiere("test", -1);

            //Sur la documentation de JUnit5 il y'a la méthode fail qui permet de rendre un test faux si le resultat attendue
            //n'est pas le bon ici c'est que l'exception n'a pas etait lever
            fail("L'exception n'a pas etait lever");
        } catch (Exception e) {
            assertEquals("Le coeff doit etre positif", e.getMessage());
        }

    }

    //Ajout d'une matiere qui est déja dans la collection
    @Test
    public void test_Ajout_Matiere_Existante() throws MatiereInexistanteException {
        //Création d'une formaiton
        Formation f = new Formation(1);

        //Ajout de la matiere
        f.ajoutMatiere("test", 1);

        //Ajout d'une matiere avec le meme nom, mais un coeff different
        f.ajoutMatiere("test", 2);
        //On vas verifier que le coeff n'a pas etait changer
        assertEquals(1,f.getCoeff("test"));
    }

    //Tester la suppression d'une matiere qui existe donc le fonctionnement normal de la méthode
    @Test
    public void test_Suppression_Matiere_Existante(){
        //Création d'une formaiton
        Formation f = new Formation(1);

        //ajout d'une matiere
        f.ajoutMatiere("test",2);

        //Supprimer la matiere
        f.suppMatiere("test");

        //Je vais utiliser la méthode getCoeff qui vas retourner une exception car on ne peut pas avoir le coeff
        // d'une matiere qui n'éxiste pas
        try {
             f.getCoeff("test");

             //J'utilise la méthode fail si l'éxception n'est pas lever
            fail("L'éxception doit etre lever");
         } catch (MatiereInexistanteException m) {
            //On verifie que la matiere n'existe plus dans la collection
            assertEquals("La matière test n'existe pas.",m.getMessage());
        }
    }

    //Tester la suppression d'une matiere inexistante
    @Test
    public void test_Suppression_Matiere_Inexistante(){
        //Création d'une formaiton
        Formation f = new Formation(1);


    }
}
