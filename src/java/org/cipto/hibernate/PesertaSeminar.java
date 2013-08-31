/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

/**
 *
 * @author CipoTaa
 */
public class PesertaSeminar {
    private String nama,nim;
    
    public PesertaSeminar(){
    }
    
    public PesertaSeminar(String nim, String nama){
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
}
