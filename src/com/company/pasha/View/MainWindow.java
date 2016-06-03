package com.company.pasha.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.company.pasha.Model.BaseForPage;
import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.MyTableModel;
import com.company.pasha.XML.ReadFile;
import com.company.pasha.XML.SaveTable;

/**
 * Created by pasha on 22.04.2016.
 */
public class MainWindow {
    private JFrame frame;
    private MyTableModel myTableModel;
    private MyTableBase myTableBase;
    private JTable jTable;
    private BaseForPage pageTableBase;
    private JLabel currentPage;

    public MainWindow(JFrame jFrame) {
        frame = jFrame;
        frame.setTitle("StudentTable");
        frame.setLayout(new BorderLayout());

        myTableBase = new MyTableBase();
        pageTableBase = new BaseForPage(myTableBase);
        //tableBase = pageTableBase.getTableBase();
        myTableModel = new MyTableModel(pageTableBase.getTableBase());
        jTable = new JTable(myTableModel);

        Box tableBox = Box.createHorizontalBox();
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setPreferredSize(new Dimension(frame.getWidth(), 600));
        frame.add(scrollPane, BorderLayout.CENTER);
        //frame.add(jTable.getTableHeader(), BorderLayout.CENTER);
        frame.setSize(700, 500);
        myTableModel.fireTableDataChanged();
        JButton addDataToTable = new JButton("Add");
        addDataToTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPanel addPanel = new AddPanel(myTableBase, pageTableBase, myTableModel);
                addPanel.setVisible(true);
                //pageTableBase.refresh();
                myTableModel.fireTableDataChanged();
            }
        });
        JButton findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindPanel findPanel = new FindPanel(myTableBase);
                findPanel.setVisible(true);

            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletePanel deletePanel = new DeletePanel(myTableBase, pageTableBase, myTableModel);
                deletePanel.setVisible(true);
                myTableModel.fireTableDataChanged();
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveTable saveTable=new SaveTable(myTableBase);
                saveTable.actionPerformed(e);
                myTableModel.fireTableDataChanged();
            }
        });

        JButton loadButton =new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFile readFile = new ReadFile(myTableBase);

                readFile.actionPerformed(e);
                pageTableBase.refresh();
                myTableModel.fireTableDataChanged();
            }
        });
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(addDataToTable);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(findButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(loadButton);

        Box pageBox = Box.createHorizontalBox();

        JButton firstPageButton = new JButton("First Page");
        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.firstPage();
                myTableModel.fireTableDataChanged();
            }
        });
        pageBox.add(firstPageButton);
        JButton previousPageButton = new JButton("Previous Page");
        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.previousPage();
                myTableModel.fireTableDataChanged();

            }
        });
        pageBox.add(previousPageButton);
        JButton nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.nextPage();
                myTableModel.fireTableDataChanged();

            }
        });
        pageBox.add(nextPageButton);
        JButton lastPageButton = new JButton("Last Page");
        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.lastPage();
                myTableModel.fireTableDataChanged();
            }
        });
        pageBox.add(lastPageButton);
        String[] numberOfRows = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox numberOfRowsComboBox = new JComboBox(numberOfRows);
        numberOfRowsComboBox.setSelectedIndex(1);
        numberOfRowsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfRows = Integer.parseInt((String) numberOfRowsComboBox.getSelectedItem());
                pageTableBase.setNumberOfRows(numberOfRows);
                myTableModel.fireTableDataChanged();

            }
        });

        pageBox.add(numberOfRowsComboBox);
        frame.add(buttonBox, BorderLayout.NORTH);
        frame.add(pageBox, BorderLayout.SOUTH);
    }
}
