/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.view;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer
extends JButton
implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
        this.setText(value != null ? value.toString() : "");
        return this;
    }
}
