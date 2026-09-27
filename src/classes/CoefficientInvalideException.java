package classes;
// Exception si le coefficient est negatif
public class CoefficientInvalideException extends Exception {
    public CoefficientInvalideException(int coeff) {
        // Message si le coefficient est negatif
        super("Le coefficient " + coeff + " ne peut pas être négatif.");
    }
}