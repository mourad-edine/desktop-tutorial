/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Books {
    private int id;
    private int quantite;
    private String designation;
    private int Pu;
    private int Pt;

    public Books(int id,int quantite, String designation, int Pu, int Pt) {
        this.id = id;
        this.quantite = quantite;
        this.designation = designation;
        this.Pu = Pu;
        this.Pt = Pt;
    }

    public int getId() {
        return id;
    }
    
    public int getQuantite() {
        return quantite;
    }

    public String getDesignation() {
        return designation;
    }

    public int getPu() {
        return Pu;
    }

    public int getPt() {
        return Pt;
    }
    
}
