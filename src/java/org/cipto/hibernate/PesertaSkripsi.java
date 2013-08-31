/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

/**
 *
 * @author CipoTaa
 */
public class PesertaSkripsi {
    private String nama,nim,judul;
    private Integer kdTahun;
    private Boolean isSidang;
    
    public PesertaSkripsi(){
    }
    
    public PesertaSkripsi(String nim,String nama){
        this.nim=nim;
        this.nama=nama;
    }
    
    public String getNim(){
        return nim;
    }
    
    public void setNim(String nim){
        this.nim=nim;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama=nama;
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
