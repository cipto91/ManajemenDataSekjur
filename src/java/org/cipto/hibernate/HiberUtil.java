/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author CipoTaa
 */
public class HiberUtil {
    private static SessionFactory sessFact=null;
    
    public static SessionFactory getSessFact(){
        if(sessFact==null){
            sessFact= new Configuration().configure().buildSessionFactory();
        }
        return sessFact;
    }
    
    public static void setSessFact(SessionFactory sessFact){
        HiberUtil.sessFact=sessFact;
    }
}
