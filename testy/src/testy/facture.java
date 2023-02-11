/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testy;

/**
 *
 * @author Manouher
 */
public class facture {
    
    private int quantite;
    private String designation;
    private int prixunit;
    private int prixtotal;

    public facture(int quantite, String designation, int prixunit, int prixtotal) {
        this.quantite = quantite;
        this.designation = designation;
        this.prixunit = prixunit;
        this.prixtotal = prixtotal;
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
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the prixunit
     */
    public int getPrixunit() {
        return prixunit;
    }

    /**
     * @param prixunit the prixunit to set
     */
    public void setPrixunit(int prixunit) {
        this.prixunit = prixunit;
    }

    /**
     * @return the prixtotal
     */
    public int getPrixtotal() {
        return prixtotal;
    }

    /**
     * @param prixtotal the prixtotal to set
     */
    public void setPrixtotal(int prixtotal) {
        this.prixtotal = prixtotal;
    }

    
    
}
