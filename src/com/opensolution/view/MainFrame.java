/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.view;

import com.opensolution.parser.XMLParser;
import com.opensolution.view.SEPATableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class MainFrame
extends JFrame {
    private JFileChooser fileChooser;
    private JFileChooser folderChooser;
    private JButton selectFileButton;
    private JButton selectDirectoryButton;
    private JButton convertButton;
    private JTable fileTable;
    private DefaultTableModel model;
    private SEPATableModel tableModel;
    private String directoryPath = "";
    private ArrayList<File> selectedFiles;
    private String defaultCountryCode = "MC";
    private String insrtId = "";
    private String paymentReason = "SALA";
    private String financialinstitutionName = "";
    public static boolean showFirstIdTag = false;
    public static boolean showBtchBookgTag = false;
    String[] priority = new String[]{"HIGH", "NORM", ""};
    String[] transactionPayer = new String[]{"SHAR", "CRED", "DEBT", "SLEV", ""};

    public MainFrame(String title) {
        super(title);
        try {
            URL resource = this.getClass().getResource("/icon.png");
            BufferedImage image = ImageIO.read(resource);
            this.setIconImage(image);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        this.fileChooser.setDialogTitle("Choose a file to load ");
        this.fileChooser.setFileSelectionMode(2);
        this.fileChooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers SEPA XML", "xml");
        this.fileChooser.setFileFilter(filter);
        this.folderChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        this.folderChooser.setDialogTitle("Select folder");
        this.folderChooser.setFileSelectionMode(1);
        this.folderChooser.setAcceptAllFileFilterUsed(false);
        JComboBox<String> combo = new JComboBox<String>(this.priority);
        JComboBox<String> transactionPayerCombo = new JComboBox<String>(this.transactionPayer);
        Object[][] data = new Object[][]{};
        String[] titles = new String[]{"Nom du fichier", "Codes de pays compte d\u00e9biteur", "InstrId", "Priorit\u00e9", "Raison de paiement", "Institution financi\u00e8re cr\u00e9anci\u00e8re", "Payeur de frais de transaction", "Chemin absolu"};
        this.tableModel = new SEPATableModel(data, titles){
            boolean[] canEdit;
            {
                this.canEdit = new boolean[]{false, true, true, true, true, true, true, false};
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        };
        this.fileTable = new JTable(this.tableModel);
        this.fileTable.setRowHeight(30);
        this.fileTable.getColumn("Priorit\u00e9").setCellEditor(new DefaultCellEditor(combo));
        DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
        this.fileTable.getColumn("Priorit\u00e9").setCellRenderer(dcr);
        this.fileTable.getColumn("Payeur de frais de transaction").setCellEditor(new DefaultCellEditor(transactionPayerCombo));
        DefaultTableCellRenderer dcrPayer = new DefaultTableCellRenderer();
        this.fileTable.getColumn("Payeur de frais de transaction").setCellRenderer(dcrPayer);
        JScrollPane scrollPane = new JScrollPane(this.fileTable);
        this.fileTable.setFillsViewportHeight(true);
        this.selectFileButton = new JButton("S\u00e9lectionner le(s) fichier(s) source");
        this.convertButton = new JButton("Convertir");
        this.selectDirectoryButton = new JButton("R\u00e9pertoire pour stocker le(s) fichier(s)");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(this.selectFileButton);
        panel.add(this.selectDirectoryButton);
        panel.add(this.convertButton);
        Container container = this.getContentPane();
        container.add((Component)scrollPane, "Center");
        container.add((Component)panel, "South");
        this.selectFileButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MainFrame.this.fileChooser.showOpenDialog(new JFrame());
                if (result == 0) {
                    File[] files = MainFrame.this.fileChooser.getSelectedFiles();
                    while (MainFrame.this.fileTable.getModel().getRowCount() > 0) {
                        ((SEPATableModel)MainFrame.this.fileTable.getModel()).removeRow(0);
                    }
                    for (File file : files) {
                        MainFrame.this.selectedFiles.add(file);
                        Object[] donnee = new Object[]{file.getName(), MainFrame.this.defaultCountryCode, MainFrame.this.insrtId, MainFrame.this.priority[0], MainFrame.this.paymentReason, MainFrame.this.financialinstitutionName, MainFrame.this.transactionPayer[0], file.getAbsolutePath()};
                        ((SEPATableModel)MainFrame.this.fileTable.getModel()).addRow(donnee);
                    }
                }
            }
        });
        this.selectDirectoryButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MainFrame.this.folderChooser.showOpenDialog(new JFrame());
                if (result == 0) {
                    MainFrame.this.directoryPath = MainFrame.this.folderChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("getSelectedFile() : " + MainFrame.this.directoryPath);
                }
            }
        });
        this.convertButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainFrame.this.fileTable.getModel().getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Vous devez s\u00e9lectionner un ou plusieurs fichiers!");
                } else if (MainFrame.this.directoryPath.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vous devez s\u00e9lectionner la distination!");
                } else {
                    for (int i = 0; i < MainFrame.this.fileTable.getModel().getRowCount(); ++i) {
                        String path = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 7);
                        File file = new File(path);
                        String filename = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 0);
                        String defaultCountryCode = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 1);
                        String insrtId = (String)MainFrame.this.fileTable.getModel().getValueAt(i,2);
                        String paymentReason = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 4);
                        String financialinstitutionName = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 5);
                        String transactionPayer = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 6);
                        String priority = (String)MainFrame.this.fileTable.getModel().getValueAt(i, 3);
                        new XMLParser(file, filename, defaultCountryCode, insrtId, priority, paymentReason, financialinstitutionName, transactionPayer, MainFrame.this.directoryPath);
                    }
                }
            }
        });
        this.selectedFiles = new ArrayList();
    }

}
