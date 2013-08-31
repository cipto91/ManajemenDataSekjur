/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.table.model;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.cipto.hibernate.Skripsi;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author cipto
 */
public class TableSkripsiModel extends ListDataModel<Skripsi> implements SelectableDataModel<Skripsi>{

    public TableSkripsiModel() {
        
    }
    
    public TableSkripsiModel(List<Skripsi> data) {
        super(data);
    }
    
    @Override
    public Object getRowKey(Skripsi t) {
        return t.getNim();
    }

    @Override
    public Skripsi getRowData(String rowKey) {
        List<Skripsi> skripsi = (List<Skripsi>) getWrappedData();
        
        for (Skripsi skp : skripsi) {
            if (skp.getNim().equals(rowKey)) {
                return skp;
            }
        }

        return null;
    }
    
}
