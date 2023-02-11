/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableau;

/**
 *
 * @author Manouher
 */
public class facture {
    
    private int quantiteP;
    private String designation;
    private int Prix_unit,prixT;

    public facture(int quantiteP, String designation, int Prix_unit, int prixT) {
        this.quantiteP = quantiteP;
        this.designation = designation;
        this.Prix_unit = Prix_unit;
        this.prixT = prixT;
    }

    /**
     * @return the quantiteP
     */
    public int getQuantiteP() {
        return quantiteP;
    }

    /**
     * @param quantiteP the quantiteP to set
     */
    public void setQuantiteP(int quantiteP) {
        this.quantiteP = quantiteP;
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
     * @return the Prix_unit
     */
    public int getPrix_unit() {
        return Prix_unit;
    }

    /**
     * @param Prix_unit the Prix_unit to set
     */
    public void setPrix_unit(int Prix_unit) {
        this.Prix_unit = Prix_unit;
    }

    /**
     * @return the prixT
     */
    public int getPrixT() {
        return prixT;
    }

    /**
     * @param prixT the prixT to set
     */
    public void setPrixT(int prixT) {
        this.prixT = prixT;
    }
    
    
}
