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
    private String nom;
    private String email;
    private String passe;

    public tableau(String nom, String email, String passe) {
        this.nom = nom;
        this.email = email;
        this.passe = passe;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
}
