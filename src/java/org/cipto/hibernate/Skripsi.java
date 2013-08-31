/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

/**
 *
 * @author CipoTaa
 */
public class Skripsi {
    private String nim,nama,judul,dosen,nidn;
    
    
    public Skripsi(){
        
    }
    
    public Skripsi(String nim, String nama, String judul, String dosen, String nidn){
        this.nim=nim;
        this.nama=nama;
        this.judul=judul;
        this.dosen=dosen;
        this.nidn=nidn;
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
    
    public String getDosen(){
        return dosen;
    }
    
    public void setDosen(String dosen){
        this.dosen=dosen;
    }
    
    public String getNidn() {
        return nidn;
    }
    
    public void setNidn(String nidn) {
        this.nidn = nidn;
    }
}
