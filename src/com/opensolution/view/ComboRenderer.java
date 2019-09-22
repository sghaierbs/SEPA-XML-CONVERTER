/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboRenderer
extends JComboBox
implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
        this.addItem("Tr\u00e8s bien");
        this.addItem("Bien");
        this.addItem("Mal");
        return this;
    }
}
