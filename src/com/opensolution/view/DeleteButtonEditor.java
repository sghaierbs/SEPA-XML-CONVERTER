/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.view;

import com.opensolution.view.SEPATableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class DeleteButtonEditor
extends DefaultCellEditor {
    protected JButton button;
    private DeleteButtonListener bListener = new DeleteButtonListener();

    public DeleteButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        this.button = new JButton();
        this.button.setOpaque(true);
        this.button.addActionListener(this.bListener);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.bListener.setRow(row);
        this.bListener.setTable(table);
        this.button.setText(value == null ? "" : value.toString());
        return this.button;
    }

    class DeleteButtonListener
    implements ActionListener {
        private int row;
        private JTable table;

        DeleteButtonListener() {
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setTable(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (this.table.getRowCount() > 0) {
                System.out.println("coucou du bouton : " + ((JButton)event.getSource()).getText());
                ((SEPATableModel)this.table.getModel()).removeRow(this.row);
            }
        }
    }

}
