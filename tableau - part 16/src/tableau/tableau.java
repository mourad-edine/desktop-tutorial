/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableau;

/**
 *
 * @author Manouher
 */
public class tableau {
    private int id;
    private String nom;
    private String prenom;
    private String adresse,email;
    private String passe,poste;
    
   // private String id;

    public tableau(int id, String nom, String prenom, String adresse, String passe, String poste,String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.passe = passe;
        this.poste = poste;
        this.email = email;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the passe
     */
    public String getPasse() {
        return passe;
    }

    /**
     * @param passe the passe to set
     */
    public void setPasse(String passe) {
        this.passe = passe;
    }

    /**
     * @return the poste
     */
    public String getPoste() {
        return poste;
    }

    /**
     * @param poste the poste to set
     */
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the nom to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
        
    

    
    
    
}
