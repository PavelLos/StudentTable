package com.company.pasha.XML;

import com.company.pasha.Model.MyTableBase;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by pasha on 27.05.2016.
 */
public class ReadFile {
    MyTableBase myTableBase;
    private JFileChooser fileChooser;
    private File file;

    public ReadFile(MyTableBase myTableBase1) {
        myTableBase = myTableBase1;
    }

    public void actionPerformed(ActionEvent e) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            AddToTable addToTable = new AddToTable(myTableBase);
            fileChooser = new JFileChooser();
            MyFileFilter filter = new MyFileFilter();
            fileChooser.setAcceptAllFileFilterUsed(true);
            fileChooser.addChoosableFileFilter(filter);
            int result1 = fileChooser.showOpenDialog(null);
            if (result1 == JFileChooser.APPROVE_OPTION){
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                file = new File(fileName);

                parser.parse(file, addToTable);
                myTableBase = addToTable.getMyTableBase();
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }
}
