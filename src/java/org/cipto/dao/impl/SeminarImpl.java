/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.dao.impl;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.cipto.dao.SeminarIface;
import org.cipto.hibernate.HiberUtil;
import org.cipto.hibernate.JadwalSeminar;
import org.cipto.hibernate.JadwalSeminarDAO;
import org.cipto.hibernate.NilaiSeminar;
import org.cipto.hibernate.PembimbingSeminar;
import org.cipto.hibernate.PesertaSeminar;
import org.cipto.hibernate.Seminar;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author CipoTaa
 */
public class SeminarImpl implements SeminarIface{

    @Override
    public void insertPeserta(PesertaSeminar ps,PembimbingSeminar seminar) {
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.save(ps);
            sess.save(seminar);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while inserting data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updatePeserta(PesertaSeminar ps,PembimbingSeminar seminar) {
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.update(ps);
            sess.update(seminar);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while updating data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deletePeserta(PesertaSeminar ps,PembimbingSeminar seminar) {
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.delete(ps);
            sess.save(seminar);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while deleting data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public PesertaSeminar getnim(String nim) {
        Session sess = HiberUtil.getSessFact().openSession();
        return (PesertaSeminar) sess.load(PesertaSeminar.class, nim);
    }

    @Override
    public List<PesertaSeminar> getall() {
        Session sess = HiberUtil.getSessFact().openSession();
        return sess.createQuery("from PesertaSeminar").list();
    }

    @Override
    public PembimbingSeminar getnimtoo(String nim) {
        Session sess = HiberUtil.getSessFact().openSession();
        return (PembimbingSeminar) sess.load(PembimbingSeminar.class, nim);
    }
    
    @Override
    public List<JadwalSeminar> getJadwal() {
        Session sess = HiberUtil.getSessFact().openSession();
        String query = "from JadwalSeminar js order by js.nim";
        System.out.println(sess.createQuery(query).list());
        return sess.createQuery(query).list();
    }
    
    @Override
    public void saveSidang(PembimbingSeminar sem, JadwalSeminarDAO js){
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.update(sem);
            sess.save(js);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while updating data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void insertNilai(NilaiSeminar ns){
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(ns);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while updating data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public List<NilaiSeminar> findAllNilai() {
        Session sess = HiberUtil.getSessFact().openSession();
        return sess.createQuery("from NilaiSeminar").list();
    }
    
    @Override
    public void insertJadwal(JadwalSeminarDAO jsd){
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(jsd);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while updating data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
