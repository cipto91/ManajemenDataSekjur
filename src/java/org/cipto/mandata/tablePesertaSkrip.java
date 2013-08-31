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
import org.cipto.dao.SkripsiIface;
import org.cipto.dao.impl.SkripsiImpl;
import org.cipto.hibernate.HiberUtil;
import org.cipto.hibernate.JadwalSkripsiDAO;
import org.cipto.hibernate.NilaiSkripsi;
import org.cipto.hibernate.PembimbingSeminar;
import org.cipto.hibernate.PembimbingSkripsi;
import org.cipto.hibernate.Skripsi;
import org.cipto.table.model.TableSkripsiModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CipoTaa
 */
@ManagedBean
@RequestScoped
public class tablePesertaSkrip {

    /**
     * Creates a new instance of tablePesertaSkrip
     */
    private List<String> skrip;
    private List<Skripsi> psrttmpl;
    private Skripsi [] arraySkp;
    private TableSkripsiModel modelPesertaSkrip;
    private String msg;
    private FacesContext context;
    public tablePesertaSkrip() {
        psrttmpl= new ArrayList<Skripsi>();
        skrip= new ArrayList<String>();
        selectSkripsi();
        notSkp();
        modelPesertaSkrip = new TableSkripsiModel(psrttmpl);
    }
    
    public List<Skripsi> getPsrttmpl(){
        return psrttmpl;
    }
    
    public void setPsrttmpl(List<Skripsi> psrttmpl) {
        this.psrttmpl = psrttmpl; 
    }
    
    public void selectSkripsi(){
        Session sess= HiberUtil.getSessFact().openSession();
        Query q=sess.createQuery("from Skripsi skrip order by skrip.nim");
        psrttmpl=q.list();
        /*Query qw=sess.createQuery("select s.nim,s.nama from PesertaSeminar as s where (s.nim,s.nama) not in (select k.nim,k.nama from PesertaSkripsi as k)");
        for(Iterator it=qw.iterate(); it.hasNext();){
            Object[] obj= (Object[]) it.next();
            msg += obj[0].toString() + obj[1].toString();
        }
        context=FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage("Data yang belum ada",msg));*/
        sess.close();
    }
    
    public List<String> getnotSkrip(){
        return skrip;
    }
    
    public void notSkp(){
        Session sess= HiberUtil.getSessFact().openSession();
        skrip=sess.createQuery("from NotSkrip notSkrip order by notSkrip.nim").list();
    }
    
    public Skripsi[] getArraySkrip() {
        return arraySkp;
    }
    
    public void setArraySkrip(Skripsi[] arraySkrip) {
        this.arraySkp = arraySkrip;
    }
    
    public TableSkripsiModel getModelPesertaSkrip(){
        return modelPesertaSkrip;
    }
    
    public void setModelPesertaSkrip(TableSkripsiModel modelPesertaSkrip) {
        this.modelPesertaSkrip = modelPesertaSkrip;
    }
    
    public void saveSidang(){
        Skripsi[] psrt = getArraySkrip();
        PembimbingSkripsi pembimbingSkripsi = new PembimbingSkripsi();
        JadwalSkripsiDAO js = new JadwalSkripsiDAO();
        NilaiSkripsi ns = new NilaiSkripsi();
        String eS = null;
        Date tgl = new Date();
        int y=0;
        for (Skripsi ps : psrt) {
            System.out.println(ps.getNim());
            pembimbingSkripsi.setNim(ps.getNim());
            pembimbingSkripsi.setNidn(ps.getNidn());
            pembimbingSkripsi.setJudul(ps.getJudul());
            pembimbingSkripsi.setKdTahun(20122);
            pembimbingSkripsi.setIsSidang(Boolean.TRUE);
            js.setSid(UUID.randomUUID().toString());
            js.setHari("hari");
            js.setTanggal(tgl);
            js.setJam("00:00");
            js.setNoRuang(0);
            js.setPenguji1("");
            js.setPenguji2("");
            js.setPenguji3("");
            js.setPetugas("-");
            js.setNim(ps.getNim());
            try {
                SkripsiIface skripsi = new SkripsiImpl();
                skripsi.saveSidang(pembimbingSkripsi, js);
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
