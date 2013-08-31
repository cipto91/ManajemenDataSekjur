/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.mandata;

import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.faces.context.ExternalContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.cipto.hibernate.HiberUtil;
import org.cipto.hibernate.PembimbingMagang;
import org.cipto.hibernate.PembimbingSeminar;
import org.cipto.hibernate.PembimbingSkripsi;
import org.cipto.hibernate.PesertaMagang;
import org.cipto.hibernate.PesertaSeminar;
import org.cipto.hibernate.PesertaSkripsi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CipoTaa
 */
@ManagedBean
@RequestScoped
public class uploadPeserta {

    /**
     * Creates a new instance of uploadPeserta
     */
    private InputStream locFile = null;
    private POIFSFileSystem fileSys = null;
    private List<String> namal, niml, jdl, pemb = null;
    private String nama, nmPemb;
    private int tmp = 0, i, nim, col = 0;
    private String file, kateg;
    private FacesContext context;
    private static final int BUFF_SIZE = 6124;

    public uploadPeserta() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getKateg() {
        return kateg;
    }

    public void setKateg(String kateg) {
        this.kateg = kateg;
    }

    public void handleReadFile() {
        Session sess = HiberUtil.getSessFact().openSession();
        //read file
        if (file != null) {
            try {
                ExternalContext extren = FacesContext.getCurrentInstance().getExternalContext();
                String nameFile = extren.getRealPath("//upload//" + file);
                locFile = new FileInputStream(nameFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_WARN,"Nama FIle Kosong",""));
        }

        //read content of the file
        try {
            fileSys = new POIFSFileSystem(locFile);
            niml = new ArrayList<String>();
            namal = new ArrayList<String>();
            jdl = new ArrayList<String>();
            pemb = new ArrayList<String>();
            HSSFWorkbook wrk = new HSSFWorkbook(fileSys);
            HSSFSheet sht = wrk.getSheetAt(0);
            Iterator<Row> rws = sht.rowIterator();

            while (rws.hasNext()) {
                HSSFRow rw = (HSSFRow) rws.next();
                Iterator<Cell> cl = rw.cellIterator();

                while (cl.hasNext()) {
                    HSSFCell cel = (HSSFCell) cl.next();
                    //get coloumn
                    col = cel.getCellNum();

                    switch (cel.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: {
                            Double tam = cel.getNumericCellValue();
                            niml.add(String.valueOf(tam.intValue()));
                            break;
                        }
                        case HSSFCell.CELL_TYPE_STRING: {
                            switch (col) {
                                case 1: {
                                    HSSFRichTextString rts = cel.getRichStringCellValue();
                                    namal.add(rts.getString());
                                    //System.out.println(" "+rts.getString());
                                    break;
                                }
                                case 2: {
                                    HSSFRichTextString rts = cel.getRichStringCellValue();
                                    jdl.add(rts.getString());
                                    //System.out.println(" "+rts.getString());
                                    break;
                                }
                                case 3: {
                                    HSSFRichTextString rts = cel.getRichStringCellValue();
                                    nmPemb = rts.getString().split(",")[0].toString();
                                    //System.out.println(nmPemb);
                                    Query q = sess.createQuery("Select d.nidn from DosenPemb as d where d.nama like('%" + nmPemb + "%')");

                                    for (Iterator it = q.iterate(); it.hasNext();) {
                                        //System.out.println(it.next());
                                        pemb.add((String) it.next());
                                    }

                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                tmp++;
            }

            //save to database (batch insert)
            Transaction trx = sess.beginTransaction();

            try {
                if(kateg.equals("kosong")){
                    FacesContext.getCurrentInstance().addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_WARN,"Pilih Salah Satu Kategori",""));
                }else if (kateg.equals("magang")) {

                    for (i = 0; i < tmp; i++) {
                        PesertaMagang psrt = new PesertaMagang();
                        PembimbingMagang bim = new PembimbingMagang();
                        psrt.setNim(niml.get(i));
                        psrt.setNama(namal.get(i));
                        bim.setNim(niml.get(i));
                        bim.setNidn(pemb.get(i));
                        bim.setJudul(jdl.get(i));
                        sess.save(psrt);
                        sess.save(bim);
                        if (i % 20 == 0) {
                            sess.flush();
                            sess.clear();
                        }
                    }
                    trx.commit();
                } else if (kateg.equals("seminar")) {
                    for (i = 0; i < tmp; i++) {
                        PesertaSeminar psrt = new PesertaSeminar();
                        PembimbingSeminar bim = new PembimbingSeminar();
                        psrt.setNim(niml.get(i));
                        psrt.setNama(namal.get(i));
                        bim.setNim(niml.get(i));
                        bim.setNidn(pemb.get(i));
                        bim.setJudul(jdl.get(i));
                        sess.save(psrt);
                        sess.save(bim);
                        if (i % 20 == 0) {
                            sess.flush();
                            sess.clear();
                        }
                    }
                    trx.commit();
                } else if (kateg.equals("skripsi")) {
                    for (i = 0; i < tmp; i++) {
                        PesertaSkripsi psrt = new PesertaSkripsi();
                        PembimbingSkripsi bim = new PembimbingSkripsi();
                        psrt.setNim(niml.get(i));
                        psrt.setNama(namal.get(i));
                        bim.setNim(niml.get(i));
                        bim.setNidn(pemb.get(i));
                        bim.setJudul(jdl.get(i));
                        sess.save(psrt);
                        sess.save(bim);
                        if (i % 20 == 0) {
                            sess.flush();
                            sess.clear();
                        }
                    }
                    trx.commit();
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data berhasil diinput"));
            } catch (Exception e) {
                if (trx != null) {
                    trx.rollback();
                    e.printStackTrace();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
                    sess.close();
                }
            } finally {
                sess.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
        }
    }

    public void handleUpload(FileUploadEvent event) {
        ExternalContext extren = FacesContext.getCurrentInstance().getExternalContext();
        File hasil = new File(extren.getRealPath("//upload//" + event.getFile().getFileName()));

        try {
            FileOutputStream fileOut = new FileOutputStream(hasil);
            byte[] buffer = new byte[BUFF_SIZE];

            int bulk;
            InputStream input = event.getFile().getInputstream();
            while (true) {
                bulk = input.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOut.write(buffer, 0, bulk);
                fileOut.flush();
            }

//            file = event.getFile().getFileName();
            this.setFile(event.getFile().getFileName());
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File ", event.getFile().getFileName() + " telah diupload"));
        } catch (IOException ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " gagal upload", ""));
        }
    }
}
