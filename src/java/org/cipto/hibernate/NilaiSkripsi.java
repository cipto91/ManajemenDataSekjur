/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

/**
 *
 * @author cipto
 */
public class NilaiSkripsi {
    private String id;
    private String nim;
    private Integer nilai1,nilai2,nilai3,presentasi;
    public NilaiSkripsi(){
        
    }
    
    public NilaiSkripsi(String id, String nim, Integer nilai1, 
            Integer nilai2, Integer nilai3,Integer presentasi){
        this.id = id;
        this.nim = nim;
        this.nilai1= nilai1;
        this.nilai2= nilai2;
        this.nilai3= nilai3;
        this.presentasi = presentasi;
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
    
    public Integer getNilai1(){
        return nilai1;
    }
    
    public void setNilai1(Integer nilai1){
        this.nilai1=nilai1;
    }
    
    public Integer getNilai2(){
        return nilai2;
    }
    
    public void setNilai2(Integer nilai2){
        this.nilai2=nilai2;
    }
    
    public Integer getNilai3(){
        return nilai2;
    }
    
    public void setNilai3(Integer nilai3){
        this.nilai3=nilai3;
    }

    public Integer getPresentasi() {
        return presentasi;
    }

    public void setPresentasi(Integer presentasi) {
        this.presentasi = presentasi;
    }
}
