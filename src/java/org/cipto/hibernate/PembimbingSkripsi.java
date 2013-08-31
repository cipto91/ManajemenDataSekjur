/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

/**
 *
 * @author CipoTaa
 */
public class PembimbingSkripsi {
    private String nim,nidn,judul;
    private Integer kdTahun;
    private Boolean isSidang;
    
    public PembimbingSkripsi(){
        
    }
    
    public PembimbingSkripsi(String nim,String nidn,String judul){
        this.nim=nim;
        this.nidn=nidn;
        this.judul=judul;
    }
    
    public String getNim(){
        return nim;
    }
    
    public void setNim(String nim){
        this.nim=nim;
    }
    
    public String getNidn(){
        return nidn;
    }
    
    public void setNidn(String nidn){
        this.nidn=nidn;
    }
    
    public String getJudul(){
        return judul;
    }
    
    public void setJudul(String judul){
        this.judul=judul;
    }
    
     public Integer getKdTahun(){
        return kdTahun;
    }
    
    public void setKdTahun(Integer kdTahun){
        this.kdTahun = kdTahun;
    }
    
    public Boolean getIsSidang(){
        return isSidang;
    }
    
    public void setIsSidang(Boolean isSidang){
        this.isSidang = isSidang;
    }
}
