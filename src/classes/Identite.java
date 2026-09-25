package classes;

public class Identite {
    private int nip;
    private String nom,prenom;

    public Identite(int num,String n,String p){
        this.nip = num;
        this.nom = n;
        this.prenom = p;
    }

    public int getNip(){
        return this.nip;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }
}
