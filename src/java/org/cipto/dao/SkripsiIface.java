/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.dao;

import java.util.List;
import org.cipto.hibernate.DosenPemb;
import org.cipto.hibernate.JadwalSkripsi;
import org.cipto.hibernate.JadwalSkripsiDAO;
import org.cipto.hibernate.NilaiSkripsi;
import org.cipto.hibernate.PembimbingSkripsi;
import org.cipto.hibernate.PesertaSkripsi;
import org.cipto.hibernate.Skripsi;

/**
 *
 * @author cipto
 */
public interface SkripsiIface {
    public void insertPeserta(PesertaSkripsi skripsi, PembimbingSkripsi ps);
    public void updatePeserta(PesertaSkripsi skripsi, PembimbingSkripsi ps);
    public void deletePeserta(PesertaSkripsi skripsi, PembimbingSkripsi ps);
    public PesertaSkripsi getnim(String nim);
    public PembimbingSkripsi getnimtoo(String nim);
    public List<Skripsi> getall();
    public List<JadwalSkripsi> getJadwal();
    public void saveSidang(PembimbingSkripsi skp, JadwalSkripsiDAO js);
    public void insertNilai(NilaiSkripsi ns);
//    public NilaiSeminar findAllNilai();
    public void insertJadwal(JadwalSkripsiDAO jsd);
}
