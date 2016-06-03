package com.company.pasha.XML;

import com.company.pasha.Model.BaseForPage;
import com.company.pasha.Model.MyTableBase;
import com.company.pasha.Model.MyTableModel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 27.05.2016.
 */
public class AddToTable extends DefaultHandler {
    private MyTableBase myTableBase;
    private String element;

    public AddToTable(MyTableBase myTableBase1) {
        myTableBase = myTableBase1;
        myTableBase.removeAll();
        element = "";
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        element = qName;
        if (element.equals("StudentElem")){

        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = qName;
        if (element.equals("StudentElem")){
            myTableBase.addToBase();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element.equals("fio")) {
            String fio = new String(ch, start, length);
            myTableBase.setFirstName(fio.split(" ")[0]);
            myTableBase.setSecondName(fio.split(" ")[1]);
            myTableBase.setMiddleName(fio.split(" ")[2]);
        }
        if (element.equals("numberOfCourse")) {
            myTableBase.setNumberOfCourse(Integer.parseInt((String)new String(ch, start, length)));
        }
        if (element.equals("numberOfGroup")) {
            myTableBase.setNumberOfGroup(Integer.parseInt((String)new String(ch, start, length)));
        }
        if (element.equals("numberOfProjects")) {
            myTableBase.setNumberOfProjects(Integer.parseInt((String)new String(ch, start, length)));
        }
        if (element.equals("numberOfSolvedProjects")) {
            myTableBase.setNumberOfSolvedProjects(Integer.parseInt((String)new String(ch, start, length)));
        }
        if (element.equals("programLanguage")) {
            myTableBase.setProgramLanguage(new String(ch, start, length));
        }


    }

    @Override
    public void endDocument() {
    }

    public MyTableBase getMyTableBase() {
        return myTableBase;
    }
}
