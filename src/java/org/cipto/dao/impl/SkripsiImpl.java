/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.dao.impl;

import java.util.List;
import org.cipto.dao.SkripsiIface;
import org.cipto.hibernate.DosenPemb;
import org.cipto.hibernate.HiberUtil;
import org.cipto.hibernate.JadwalSkripsi;
import org.cipto.hibernate.JadwalSkripsiDAO;
import org.cipto.hibernate.NilaiSkripsi;
import org.cipto.hibernate.PembimbingSkripsi;
import org.cipto.hibernate.PesertaSkripsi;
import org.cipto.hibernate.Skripsi;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author cipto
 */
public class SkripsiImpl implements SkripsiIface{

    @Override
    public void insertPeserta(PesertaSkripsi skripsi, PembimbingSkripsi ps) {
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.save(skripsi);
            sess.save(ps);
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
    public void updatePeserta(PesertaSkripsi skripsi, PembimbingSkripsi ps) {
       Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.update(ps);
            sess.update(skripsi);
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
    public void deletePeserta(PesertaSkripsi skripsi, PembimbingSkripsi ps) {
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.delete(ps);
            trx.commit();
        } catch (Exception e) {
            if(trx!=null){
                trx.rollback();
                System.out.println("Error while deleting data: "+e.getMessage());
                e.printStackTrace();
            }
        }
        Session sess2 = HiberUtil.getSessFact().openSession();
        Transaction trx2= sess.beginTransaction();
        try {
            sess2.beginTransaction();
            sess2.delete(skripsi);
            trx2.commit();
        } catch (Exception e) {
            if(trx2!=null){
                trx2.rollback();
                System.out.println("Error while deleting data: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public PesertaSkripsi getnim(String nim) {
        Session sess = HiberUtil.getSessFact().openSession();
        return (PesertaSkripsi) sess.load(PesertaSkripsi.class, nim);
    }

    @Override
    public PembimbingSkripsi getnimtoo(String nim) {
        Session sess = HiberUtil.getSessFact().openSession();
        return (PembimbingSkripsi) sess.load(PembimbingSkripsi.class, nim);
    }
    
    @Override
    public List<Skripsi> getall() {
        Session sess = HiberUtil.getSessFact().openSession();
        return sess.createQuery("from Skripsi").list();
    }

    @Override
    public List<JadwalSkripsi> getJadwal(){
        Session sess = HiberUtil.getSessFact().openSession();
        String query = "from JadwalSkripsi js order by js.nim";
        System.out.println(sess.createQuery(query).list());
        return sess.createQuery(query).list();
    }
    
    @Override
    public void saveSidang(PembimbingSkripsi skp, JadwalSkripsiDAO js){
        Session sess = HiberUtil.getSessFact().openSession();
        Transaction trx= sess.beginTransaction();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(skp);
            sess.saveOrUpdate(js);
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
    public void insertNilai(NilaiSkripsi ns) {
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
    };
    
    @Override
    public void insertJadwal(JadwalSkripsiDAO jsd) {
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
