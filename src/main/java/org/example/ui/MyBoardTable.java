package org.example.ui;

import org.example.domain.model.Repository;

import javax.swing.table.AbstractTableModel;

public class MyBoardTable extends AbstractTableModel {

    private final Repository repository;

    public MyBoardTable(Repository repository) {
        this.repository = repository;
    }

    @Override
    public int getRowCount() {
        return repository.getBoard().length;
    }

    @Override
    public int getColumnCount() {
        return repository.getBoard()[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        fireTableCellUpdated(rowIndex, columnIndex);
        return repository.getCellElements(rowIndex, columnIndex);
    }
}
