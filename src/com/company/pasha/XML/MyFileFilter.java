package com.company.pasha.XML;


import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by pasha on 27.05.2016.
 */
public class MyFileFilter extends FileFilter {
    private String ext = ".xml";

    public boolean accept(File file) {
        if (file.isDirectory()) return true;
        return (file.getName().endsWith(ext));
    }

    public String getDescription() {
        return "*" + ext;
    }
}
