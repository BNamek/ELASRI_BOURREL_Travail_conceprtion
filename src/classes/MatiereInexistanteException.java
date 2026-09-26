package classes;
// Exception si la matière n'existe pas
public class MatiereInexistanteException extends Exception {
  public MatiereInexistanteException(String mat) {
    //Message si la matiere n'existe pas
    super("La matière " + mat + " n'existe pas.");
  }
}