/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.util.ArrayList;
import obfuscator.FileAndDirectory;
import obfuscator.FileParser;

/**
 *
 * @author MANHKHUC
 */
public class Obfuscator {

    private static ArrayList<String> listFile;
    public static ArrayList listExistedName;

    public String Obfuscator(String file) {
        String ok = "n";
        listExistedName = new ArrayList();
        listFile = new ArrayList();

//        listFile.add(file);
        getListFile(file);
        if (listFile.isEmpty()) {
            return "j";
        } else {
            ArrayList listClasses = new ArrayList();
            try {
                for (int i = 0; i < listFile.size(); i++) {
                    FileParser fp = new FileParser();

                    ArrayList classes = fp.RenameMethod((String) listFile.get(i), listFile);
                    if ((classes != null) && (classes.size() > 0)) {
                        for (int j = 0; j < classes.size(); j++) {
                            listClasses.add(classes.get(j));
                        }
                    }
                }

                if ((listClasses != null) && (listClasses.size() > 0)) {
                    for (int i = 0; i < listClasses.size(); i++) {
                        ArrayList element = (ArrayList) listClasses.get(i);
                        String oldName = (String) element.get(0);
                        String newName = (String) element.get(1);
                        File oldFile = new File(oldName);
                        File newFile = new File(newName);
                        oldFile.renameTo(newFile);
                    }
                }
                ok = "y";
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return ok;
    }

    private void getListFile(String DirPath) {
        File directory = new File(DirPath);
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                String ext = FileAndDirectory.getExtentionFileName(file);
                if (ext.equals("java")) {
                    listFile.add(file.getAbsolutePath());
                }
            } else {
                getListFile(file.getAbsolutePath());
            }
        }
    }
}
