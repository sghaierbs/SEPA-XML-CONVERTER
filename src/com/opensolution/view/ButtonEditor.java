/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ButtonEditor
extends DefaultCellEditor {
    protected JButton button;
    private ButtonListener bListener = new ButtonListener();

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        this.button = new JButton();
        this.button.setOpaque(true);
        this.button.addActionListener(this.bListener);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.bListener.setRow(row);
        this.bListener.setColumn(column);
        this.bListener.setTable(table);
        this.button.setText(value == null ? "" : value.toString());
        return this.button;
    }

    class ButtonListener
    implements ActionListener {
        private int column;
        private int row;
        private JTable table;
        private int nbre = 0;
        private JButton button;

        ButtonListener() {

        }

        public void setColumn(int col) {
            this.column = col;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setTable(JTable table) {
            this.table = table;
        }

        public JButton getButton() {
            return this.button;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("coucou du bouton : " + ((JButton)event.getSource()).getText());
            this.table.getModel().setValueAt("New Value " + ++this.nbre, this.row, this.column - 1);
            ((AbstractTableModel)this.table.getModel()).fireTableCellUpdated(this.row, this.column - 1);
            this.button = (JButton)event.getSource();
        }
    }

}
