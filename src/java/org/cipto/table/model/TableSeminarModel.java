/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.table.model;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.cipto.hibernate.Seminar;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author cipto
 */
public class TableSeminarModel extends ListDataModel<Seminar> implements SelectableDataModel<Seminar> {

    public TableSeminarModel() {
    }

    public TableSeminarModel(List<Seminar> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Seminar t) {
        return t.getNim();
    }

    @Override
    public Seminar getRowData(String rowKey) {
        List<Seminar> seminar = (List<Seminar>) getWrappedData();

        for (Seminar sem : seminar) {
            if (sem.getNim().equals(rowKey)) {
                return sem;
            }
        }

        return null;
    }
}
