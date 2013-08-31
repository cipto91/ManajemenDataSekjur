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
public class JadwalSkripsiDAO {
    private String sid;
    private String hari;
    private Date tanggal;
    private String jam;
    private String nim;
    private String penguji1;
    private String penguji2;
    private String penguji3;
    private String dosen;
    private Integer noRuang;
    private String petugas;
    
    public JadwalSkripsiDAO() {
        
    }
    
    public JadwalSkripsiDAO(String sid, String hari, Date tanggal, String jam, 
            String nim, String dosen, Integer noRuang,
            String petugas, String penguji1, String penguji2, String penguji3) {
        this.sid = sid;
        this.hari = hari;
        this.tanggal = tanggal;
        this.jam = jam;
        this.nim = nim;
        this.dosen = dosen;
        this.noRuang = noRuang;
        this.petugas = petugas;
        this.penguji1 = penguji1;
        this.penguji2 = penguji2;
        this.penguji3 = penguji3;
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
    
    public String getPenguji1() {
        return penguji1;
    }

    public void setPenguji1(String penguji1) {
        this.penguji1 = penguji1;
    }
    
    public String getPenguji2() {
        return penguji2;
    }

    public void setPenguji2(String penguji2) {
        this.penguji2 = penguji2;
    }
    
    public String getPenguji3() {
        return penguji3;
    }

    public void setPenguji3(String penguji3) {
        this.penguji3 = penguji3;
    }
}
