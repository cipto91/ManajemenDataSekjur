/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.mandata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.cipto.dao.SeminarIface;
import org.cipto.dao.SkripsiIface;
import org.cipto.dao.impl.SeminarImpl;
import org.cipto.dao.impl.SkripsiImpl;
import org.cipto.hibernate.JadwalSeminar;
import org.cipto.hibernate.JadwalSeminarDAO;
import org.cipto.hibernate.JadwalSkripsi;
import org.cipto.hibernate.JadwalSkripsiDAO;
import org.cipto.hibernate.NilaiSeminar;
import org.cipto.hibernate.NilaiSkripsi;
import org.cipto.hibernate.PembimbingSeminar;
import org.cipto.hibernate.PembimbingSkripsi;
import org.cipto.hibernate.PesertaSeminar;
import org.cipto.hibernate.PesertaSkripsi;
import org.cipto.hibernate.Seminar;
import org.cipto.hibernate.Skripsi;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.cipto.report.ReportGenController;
/**
 *
 * @author cipto
 */
@ManagedBean
@RequestScoped
public class DataController {

    /**
     * Creates a new instance of DataController
     */
    private PesertaSkripsi psrtSkp;
    private PembimbingSkripsi pemSkp;
    private PesertaSeminar psrtSem;
    private PembimbingSeminar pemSem;
    private NilaiSeminar nilaiSeminar;
    private NilaiSkripsi nilaiSkripsi;
    private Skripsi skp;
    private JadwalSeminarDAO jadwalSeminarDAO;
    private JadwalSkripsiDAO jadwalSkripsiDAO;
    private List<JadwalSeminar> jadwalSeminar;
    private List<JadwalSkripsi> jadwalSkripsi;
    private SeminarIface seminar;
    private SkripsiIface skripsi;

    public DataController() {
        seminar = new SeminarImpl();
        skripsi = new SkripsiImpl();
        jadwalSeminar = new ArrayList<JadwalSeminar>();
        jadwalSkripsi = new ArrayList<JadwalSkripsi>();
        psrtSkp = new PesertaSkripsi();
        pemSkp = new PembimbingSkripsi();
        psrtSem = new PesertaSeminar();
        pemSem = new PembimbingSeminar();
        nilaiSeminar = new NilaiSeminar();
        nilaiSkripsi = new NilaiSkripsi();
        jadwalSeminarDAO = new JadwalSeminarDAO();
        getDataTabelJadwalSeminar();
        getDataTabelJadwalSkripsi();
    }

    //getter and setter seminar
    public PesertaSeminar getPsrtSem() {
        return psrtSem;
    }

    public void setPsrtSem(PesertaSeminar psrtSem) {
        this.psrtSem = psrtSem;
    }

    public PembimbingSeminar getPemSem() {
        return pemSem;
    }

    public void setPemSem(PembimbingSeminar pemSem) {
        this.pemSem = pemSem;
    }

    //getter and setter skripsi
    public PesertaSkripsi getPsrtSkp() {
        return psrtSkp;
    }

    public void setPsrtSkp(PesertaSkripsi psrtSkp) {
        this.psrtSkp = psrtSkp;
    }

    public PembimbingSkripsi getPemSkp() {
        return pemSkp;
    }

    public void setPemSkp(PembimbingSkripsi pemSkp) {
        this.pemSkp = pemSkp;
    }

    public List<JadwalSeminar> getJadwalSeminar() {
        return jadwalSeminar;
    }

    public void setJadwalSeminar(List<JadwalSeminar> jadwalSeminar) {
        this.jadwalSeminar = jadwalSeminar;
    }

    public NilaiSeminar getNilaiSeminar() {
        return nilaiSeminar;
    }
    
    public JadwalSeminarDAO getJadwalSeminarDAO() {
        return jadwalSeminarDAO;
    }
    
    public void setJadwalSeminarDAO(JadwalSeminarDAO jadwalSeminarDAO) {
        this.jadwalSeminarDAO = jadwalSeminarDAO;
    }
    
    public void setNilaiSeminar(NilaiSeminar nilaiSeminar) {
        this.nilaiSeminar = nilaiSeminar;
    }
    
    public List<JadwalSkripsi> getJadwalSkripsi() {
        return jadwalSkripsi;
    }
    
    public JadwalSkripsiDAO getJadwalSkripsiDAO() {
        return jadwalSkripsiDAO;
    }
    
    public void setJadwalSkripsiDAO(JadwalSkripsiDAO jadwalSkripsiDAO) {
        this.jadwalSkripsiDAO = jadwalSkripsiDAO;
    }
    
    public NilaiSkripsi getNilaiSkripsi() {
        return nilaiSkripsi;
    }
    
    public void setNilaiSkripsi(NilaiSkripsi nilaiSkripsi) {
        this.nilaiSkripsi = nilaiSkripsi;
    }
    
    //action skripsi
    public void clearInsertPsrtSkp(ActionEvent actionEvent) {
        psrtSkp = new PesertaSkripsi();
        pemSkp = new PembimbingSkripsi();
    }

    public void insertPsrtSkp(ActionEvent actionEvent) {
        try {
            SkripsiImpl si = new SkripsiImpl();
            pemSkp.setNim(psrtSkp.getNim());
            si.insertPeserta(psrtSkp, pemSkp);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
            psrtSkp = new PesertaSkripsi();
            pemSkp = new PembimbingSkripsi();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while inserting", e.getMessage()));
        }

    }

    public void getValPsertaSkp(String id) {
        SkripsiImpl si = new SkripsiImpl();
        psrtSkp = si.getnim(id);
        pemSkp = si.getnimtoo(id);
    }

    public void editPsertaSkp() {
        try {
            SkripsiImpl si = new SkripsiImpl();
            pemSkp.setNim(psrtSkp.getNim());
            si.updatePeserta(psrtSkp, pemSkp);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diedit"));
            psrtSkp = new PesertaSkripsi();
            pemSkp = new PembimbingSkripsi();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while updating", e.getMessage()));
        }
    }

    public void deletePsertaSkp() {
        try {
            SkripsiImpl si = new SkripsiImpl();
            pemSkp.setNim(psrtSkp.getNim());
            si.deletePeserta(psrtSkp, pemSkp);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil dihapus"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while updating", e.getMessage()));
        }
    }

    //action seminar
    public void clearInsertPsrtSem(ActionEvent actionEvent) {
        psrtSem = new PesertaSeminar();
        pemSem = new PembimbingSeminar();
    }

    public void insertPsrtSem(ActionEvent actionEvent) {
        try {
            SeminarImpl si = new SeminarImpl();
            pemSem.setNim(psrtSem.getNim());
            si.insertPeserta(psrtSem, pemSem);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
            psrtSkp = new PesertaSkripsi();
            pemSkp = new PembimbingSkripsi();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while inserting", e.getMessage()));
        }

    }

    public void getValPsertaSem(String id) {
        SeminarImpl si = new SeminarImpl();
        psrtSem = si.getnim(id);
        pemSem = si.getnimtoo(id);
    }

    public void editPsertaSem() {
        try {
            SeminarImpl si = new SeminarImpl();
            pemSem.setNim(psrtSem.getNim());
            si.updatePeserta(psrtSem, pemSem);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diedit"));
            psrtSkp = new PesertaSkripsi();
            pemSkp = new PembimbingSkripsi();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while updating", e.getMessage()));
        }
    }

    public void deletePsertaSem() {
        try {
            SeminarImpl si = new SeminarImpl();
            pemSem.setNim(psrtSem.getNim());
            si.deletePeserta(psrtSem, pemSem);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil dihapus"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while updating", e.getMessage()));
        }
    }

    public void getDataTabelJadwalSeminar() {
        jadwalSeminar = seminar.getJadwal();
    }

    public void saveNilai(CellEditEvent evt) {
        int index = evt.getRowIndex();
        Object oldVal = evt.getOldValue();
        Object newVal = evt.getNewValue();
        if (newVal != null && !newVal.equals(oldVal)) {
            nilaiSeminar.setId(jadwalSeminar.get(index).getSid());
            nilaiSeminar.setNim(jadwalSeminar.get(index).getNim());
            nilaiSeminar.setNilai1(Integer.valueOf(newVal.toString()));
            seminar.insertNilai(nilaiSeminar);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
            nilaiSeminar = new NilaiSeminar();
        }
    }

    public void saveJadwal(CellEditEvent evt) {
        int index = evt.getRowIndex();
        Object oldVal = evt.getOldValue();
        Object newVal = evt.getNewValue();
        if (newVal != null && !newVal.equals(oldVal)) {
            jadwalSeminarDAO.setSid(jadwalSeminar.get(index).getSid());
            jadwalSeminarDAO.setNim(jadwalSeminar.get(index).getNim());
            jadwalSeminarDAO.setHari(jadwalSeminar.get(index).getHari());
            jadwalSeminarDAO.setTanggal(jadwalSeminar.get(index).getTanggal());
            jadwalSeminarDAO.setJam(jadwalSeminar.get(index).getJam());
            jadwalSeminarDAO.setNidn(jadwalSeminar.get(index).getPenguji());
            jadwalSeminarDAO.setNoRuang(jadwalSeminar.get(index).getNoRuang());
            jadwalSeminarDAO.setPetugas(jadwalSeminar.get(index).getPetugas());
            seminar.insertJadwal(jadwalSeminarDAO);
            System.out.println("sid "+jadwalSeminar.get(index).getSid());
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
            jadwalSeminarDAO = new JadwalSeminarDAO();
        }
    }
    
    public void getDataTabelJadwalSkripsi() {
        jadwalSkripsi = skripsi.getJadwal();
    }
    
    public void saveJadwalSkripsi() {
        jadwalSkripsiDAO.setSid(jadwalSkripsi.get(1).getSid());
        jadwalSkripsiDAO.setNim(jadwalSkripsi.get(1).getNim());
//        jadwalSeminarDAO.setHari(jadwalSeminar.get(2).getHari());
//        jadwalSeminarDAO.setTanggal(jadwalSeminar.get(3).getTanggal());
//        jadwalSeminarDAO.setJam(jadwalSeminar.get(4).getJam());
//        jadwalSeminarDAO.setNidn(jadwalSeminar.get(5).getPenguji());
//        jadwalSeminarDAO.setNoRuang(jadwalSeminar.get(6).getNoRuang());
//        jadwalSeminarDAO.setPetugas(jadwalSeminar.get(7).getPetugas());
        skripsi.insertJadwal(jadwalSkripsiDAO);
        FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
    }
    
    public void saveNilaiSkripsi(CellEditEvent evt) {
        int index = evt.getRowIndex();
        Object oldVal = evt.getOldValue();
        Object newVal = evt.getNewValue();
        if (newVal != null && !newVal.equals(oldVal)) {
            nilaiSkripsi.setId(jadwalSkripsi.get(index).getSid());
            nilaiSkripsi.setNim(jadwalSkripsi.get(index).getNim());
            nilaiSkripsi.setNilai1(Integer.valueOf(newVal.toString()));
            nilaiSkripsi.setNilai2(Integer.valueOf(newVal.toString()));
            nilaiSkripsi.setNilai3(Integer.valueOf(newVal.toString()));
            skripsi.insertNilai(nilaiSkripsi);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
            nilaiSkripsi = new NilaiSkripsi();
        }
    }
    
    public void cetakSKDosen() {
        
        String tmpalte = "SK Dosen";
         Map map = new HashMap();
         
         ReportGenController gen = new ReportGenController();
         if(gen.printReport(map, new StringBuffer("/report/").append(tmpalte)
                 .append(".jrxml").toString(), tmpalte)){
             
         }
    }
}
