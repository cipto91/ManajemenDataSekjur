/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.mandata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.cipto.dao.SeminarIface;
import org.cipto.dao.impl.SeminarImpl;
import org.cipto.hibernate.HiberUtil;
import org.cipto.hibernate.JadwalSeminar;
import org.cipto.hibernate.JadwalSeminarDAO;
import org.cipto.hibernate.NilaiSeminar;
import org.cipto.hibernate.PembimbingSeminar;
import org.cipto.hibernate.PesertaSeminar;
import org.cipto.hibernate.Seminar;
import org.cipto.table.model.TableSeminarModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CipoTaa
 */
@ManagedBean
@RequestScoped
public class tablePeserta {

    /**
     * Creates a new instance of tablePeserta
     */
    private List<String> psrtmg;
    private List<Seminar> psrt;
    private List<PesertaSeminar> filteredSeminar;
    private Seminar [] arrayPsrtSem;
    private TableSeminarModel modelTblSeminar;
    private final static String[] hari;
    private final static String[] jam;
    private final static String[] ruang;
    
    static{
        hari = new String[5];
        hari[0]="Senin";
        hari[1]="Selasa";
        hari[2]="Rabu";
        hari[3]="Kamis";
        hari[4]="Jumat";
        
        jam = new String[7];
        jam[0]="08.00";
        jam[1]="08.30";
        jam[2]="09.00";
        jam[3]="09.30";
        jam[4]="10.00";
        jam[5]="10.30";
        jam[6]="11.00";
        
        ruang = new String[6];
        ruang[0]="501";
        ruang[1]="502";
        ruang[2]="504";
        ruang[3]="505";
        ruang[4]="507";
        ruang[5]="508";
    }

    public String[] getHari() {
        return hari;
    }

    public String[] getJam() {
        return jam;
    }

    public String[] getRuang() {
        return ruang;
    }
    
    public tablePeserta() {
        psrt= new ArrayList<Seminar>();
        psrtmg= new ArrayList<String>();
        selectSeminar();
        selectMagang();
        modelTblSeminar = new TableSeminarModel(psrt);
    }
    
    public List<Seminar> getPsrt(){
        return psrt;
        
    }
    
    public List<String> getPsrtmg(){
        return psrtmg;
    }
    
    public List<PesertaSeminar> getFilteredSeminar(){
        return filteredSeminar;
    }
    
    public void setFilteredSeminar(List<PesertaSeminar> filteredSeminar){
        this.filteredSeminar=filteredSeminar;
    }
    
    public void selectSeminar(){
        Session sess= HiberUtil.getSessFact().openSession();
        Query q=sess.createQuery("from Seminar sem order by sem.nim");
        psrt=q.list();
        sess.close();
    }
    
    public void selectMagang(){
        Session sess= HiberUtil.getSessFact().openSession();
        Query q=sess.createQuery("from PesertaMagang magang order by magang.nim");
        psrtmg=q.list();
        sess.close();
    }
    
    public Seminar[] getArrayPsrtSem(){
        return arrayPsrtSem;
    }
    
    public void setArrayPsrtSem(Seminar[] arrayPsrtSem) {
        this.arrayPsrtSem = arrayPsrtSem;
    }
    
    public TableSeminarModel getModelTblSeminar(){
        return modelTblSeminar;
    }
    
    public void setModelTblSeminar(TableSeminarModel tableSeminarModel){
        this.modelTblSeminar = tableSeminarModel;
    }
    
    public void saveSidang(){
        Seminar[] psrt = getArrayPsrtSem();
        PembimbingSeminar pembimbingSeminar = new PembimbingSeminar();
        JadwalSeminarDAO js = new JadwalSeminarDAO();
        NilaiSeminar ns = new NilaiSeminar();
        String eS = null;
        Date tgl = new Date();
        int y=0;
        for (Seminar ps : psrt) {
            System.out.println(ps.getNim());
            pembimbingSeminar.setNim(ps.getNim());
            pembimbingSeminar.setNidn(ps.getNidn());
            pembimbingSeminar.setJudul(ps.getJudul());
            pembimbingSeminar.setKdTahun(20122);
            pembimbingSeminar.setIsSidang(Boolean.TRUE);
            js.setSid(UUID.randomUUID().toString());
            js.setHari("hari");
            js.setTanggal(tgl);
            js.setJam("00:00");
            js.setNoRuang(000);
            js.setNidn(ps.getNidn());
            js.setPetugas("-");
            js.setNim(ps.getNim());
            try {
                SeminarIface seminar = new SeminarImpl();
                seminar.saveSidang(pembimbingSeminar, js);
                y=1;
            } catch (Exception e) {
                eS = e.getMessage();
                y=0;
            }

        }
        if (y == 1) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Data berhasil diinput"));
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, "Error while inserting", eS));
        }
    }
}
