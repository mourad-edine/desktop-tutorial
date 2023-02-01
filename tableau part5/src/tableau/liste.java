/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableau;

/**
 *
 * @author Manouher
 */
public class liste {
    private Integer id;
    private String nomproduit;
    private Integer prixKp;
    private Integer prixsac;
    private Integer prixKilo;
    private Integer TVA;

    public liste(Integer id,String nomproduit, Integer prixKp, Integer prixsac, Integer prixKilo, Integer TVA) {
        this.id =id;
        this.nomproduit = nomproduit;
        this.prixKp = prixKp;
        this.prixsac = prixsac;
        this.prixKilo = prixKilo;
        this.TVA = TVA;
    }

    /**
     * @return the nomproduit
     */
    public String getNomproduit() {
        return nomproduit;
    }

    /**
     * @param nomproduit the nomproduit to set
     */
    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    /**
     * @return the prixKp
     */
    public Integer getPrixKp() {
        return prixKp;
    }

    /**
     * @param prixKp the prixKp to set
     */
    public void setPrixKp(Integer prixKp) {
        this.prixKp = prixKp;
    }

    /**
     * @return the prixsac
     */
    public Integer getPrixsac() {
        return prixsac;
    }

    /**
     * @param prixsac the prixsac to set
     */
    public void setPrixsac(Integer prixsac) {
        this.prixsac = prixsac;
    }

    /**
     * @return the prixKilo
     */
    public Integer getPrixKilo() {
        return prixKilo;
    }

    /**
     * @param prixKilo the prixKilo to set
     */
    public void setPrixKilo(Integer prixKilo) {
        this.prixKilo = prixKilo;
    }

    /**
     * @return the TVA
     */
    public Integer getTVA() {
        return TVA;
    }

    /**
     * @param TVA the TVA to set
     */
    public void setTVA(Integer TVA) {
        this.TVA = TVA;
    }
    
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the nomproduit to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
