/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.view;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableComponent
extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            return (JButton)value;
        }
        if (value instanceof JComboBox) {
            return (JComboBox)value;
        }
        return this;
    }
}
