/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableau;

/**
 *
 * @author Manouher
 */
public class listproduit {
    private int id;
    private String typeachat;
    private  String nom_produit,client;
    private  int quantite;
    private String civilite;
    private int punitaire;
    private int ptotal;

    public listproduit(int id, String typeachat, String nom_produit, String client, int quantite, String civilite, int punitaire, int ptotal) {
        this.id = id;
        this.typeachat = typeachat;
        this.nom_produit = nom_produit;
        this.client = client;
        this.quantite = quantite;
        this.civilite = civilite;
        this.punitaire = punitaire;
        this.ptotal = ptotal;
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
     * @return the typeachat
     */
    public String getTypeachat() {
        return typeachat;
    }

    /**
     * @param typeachat the typeachat to set
     */
    public void setTypeachat(String typeachat) {
        this.typeachat = typeachat;
    }

    /**
     * @return the nom_produit
     */
    public String getNom_produit() {
        return nom_produit;
    }

    /**
     * @param nom_produit the nom_produit to set
     */
    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    /**
     * @return the client
     */
    public String getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the civilite
     */
    public String getCivilite() {
        return civilite;
    }

    /**
     * @param civilite the civilite to set
     */
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    /**
     * @return the punitaire
     */
    public int getPunitaire() {
        return punitaire;
    }

    /**
     * @param punitaire the punitaire to set
     */
    public void setPunitaire(int punitaire) {
        this.punitaire = punitaire;
    }

    /**
     * @return the ptotal
     */
    public int getPtotal() {
        return ptotal;
    }

    /**
     * @param ptotal the ptotal to set
     */
    public void setPtotal(int ptotal) {
        this.ptotal = ptotal;
    }
    
   

   
        
    

}
