package classes;

public class Identite {
    private int nip;
    private String nom,prenom;

    /**
     * Creation d'un constructeur
     * @param num Initialise le numero (NIP) d'un étudiant
     * @param n Initialise le nom d'un étudiant
     * @param p Initialise le prenom d'un étudiant
     */
    public Identite(int num,String n,String p){
        this.nip = num;
        this.nom = n;
        this.prenom = p;
    }

    /**
     * Creation d'un get
     * @return Retourne le numero(NIP) d'un étudiant
     */
    public int getNip(){
        return this.nip;
    }

    /**
     * Creation d'un get
     * @return Retourne le nom d'un étudiant
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Creation d'un get
     * @return Retourne le prenom d'un étudiant
     */
    public String getPrenom(){
        return this.prenom;
    }
}
