package com.company.pasha.XML;

import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.MyTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by pasha on 27.05.2016.
 */
public class SaveTable extends AbstractAction{
    private MyTableModel myTableModel;
    public MyTableBase myTableBase;
    private JFileChooser fileChooser;
    private File file;

    public SaveTable(MyTableBase myTableBase1) {
        myTableBase = myTableBase1;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            myTableModel = new MyTableModel(myTableBase);
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            fileChooser = new JFileChooser();
            MyFileFilter filter = new MyFileFilter();
            fileChooser.setAcceptAllFileFilterUsed(true);
            fileChooser.addChoosableFileFilter(filter);
            int result1 = fileChooser.showSaveDialog(null);
            if (result1 == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if (fileChooser.getFileFilter() == filter) {
                    file = new File(fileName + ".xml");
                } else {
                    file = new File(fileName);
                }
                if (!file.exists()) {
                    file.createNewFile();
                }

                Document doc = builder.newDocument();
                Element tableEl = doc.createElement("Student");
                doc.appendChild(tableEl);

                String[] columns = {"fio", "numberOfCourse", "numberOfGroup", "numberOfProjects", "numberOfSolvedProjects", "programLanguage"};

                for (int numberOfRow = 0; numberOfRow < myTableModel.getRowCount(); numberOfRow++) {
                    Element rowEl = doc.createElement("StudentElem");
                    tableEl.appendChild(rowEl);
                    for (int numberOfColimn = 0; numberOfColimn < myTableModel.getColumnCount(); numberOfColimn++) {
                        String header = columns[numberOfColimn].toString();
                        String value = myTableModel.getValueAt(numberOfRow, numberOfColimn).toString();
                        Element cellEl = doc.createElement(header);
                        rowEl.appendChild(cellEl);
                        cellEl.appendChild(doc.createTextNode(value));
                    }
                }

                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer transformer = tFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }


}
