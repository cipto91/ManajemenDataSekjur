/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

/**
 *
 * @author cipto
 */
public class NilaiSeminar {
    private String id;
    private String nim;
    private Integer nilai;
    public NilaiSeminar(){
        
    }
    
    public NilaiSeminar(String id, String nim, Integer nilai){
        this.id = id;
        this.nim = nim;
        this.nilai= nilai;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getNim(){
        return nim;
    }
    
    public void setNim(String nim){
        this.nim=nim;
    }
    
    public Integer getNilai(){
        return nilai;
    }
    
    public void setNilai(Integer nilai){
        this.nilai=nilai;
    }
}
