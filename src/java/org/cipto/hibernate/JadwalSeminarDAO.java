/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

import java.util.Date;

/**
 *
 * @author cipto
 */
public class JadwalSeminarDAO {
    private String sid;
    private String hari;
    private Date tanggal;
    private String jam;
    private String nim;
    private String nidn;
    private String dosen;
    private Integer noRuang;
    private String petugas;
    
    public JadwalSeminarDAO() {
        
    }
    
    public JadwalSeminarDAO(String sid, String hari, Date tanggal, String jam, 
            String nim, String dosen, Integer noRuang,
            String petugas, String nidn) {
        this.sid = sid;
        this.hari = hari;
        this.tanggal = tanggal;
        this.jam = jam;
        this.nim = nim;
        this.dosen = dosen;
        this.noRuang = noRuang;
        this.petugas = petugas;
        this.nidn = nidn;
    }
    
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
    
    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public Integer getNoRuang() {
        return noRuang;
    }

    public void setNoRuang(Integer noRuang) {
        this.noRuang = noRuang;
    }

    public String getPetugas() {
        return petugas;
    }

    public void setPetugas(String petugas) {
        this.petugas = petugas;
    }
    
    public String getNidn(){
        return nidn;
    }
    
    public void setNidn(String nidn){
        this.nidn = nidn;
    }
}
