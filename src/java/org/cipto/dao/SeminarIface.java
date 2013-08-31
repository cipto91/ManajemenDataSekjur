/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.dao;

import java.util.List;
import org.cipto.hibernate.JadwalSeminar;
import org.cipto.hibernate.JadwalSeminarDAO;
import org.cipto.hibernate.NilaiSeminar;
import org.cipto.hibernate.PembimbingSeminar;
import org.cipto.hibernate.PesertaSeminar;
import org.cipto.hibernate.Seminar;

/**
 *
 * @author CipoTaa
 */
public interface SeminarIface {
    public void insertPeserta(PesertaSeminar ps,PembimbingSeminar seminar);
    public void updatePeserta(PesertaSeminar ps,PembimbingSeminar seminar);
    public void deletePeserta(PesertaSeminar ps,PembimbingSeminar seminar);
    public PesertaSeminar getnim(String nim);
    public PembimbingSeminar getnimtoo(String nim);
    public List<PesertaSeminar> getall();
    public List<JadwalSeminar> getJadwal();
    public void saveSidang(PembimbingSeminar sem, JadwalSeminarDAO js);
    public void insertNilai(NilaiSeminar ns);
    public List<NilaiSeminar> findAllNilai();
    public void insertJadwal(JadwalSeminarDAO jsd);
}
