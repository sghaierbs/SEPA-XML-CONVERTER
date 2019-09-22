/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution;

import com.opensolution.view.MainFrame;
import javax.swing.SwingUtilities;

public class Entry {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                MainFrame frame = new MainFrame("XML SEPA Converter");
                frame.setSize(1200, 400);
                frame.setDefaultCloseOperation(3);
                frame.setVisible(true);
            }
        });
    }

}
