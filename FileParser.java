/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obfuscator;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.Comment;
import japa.parser.ast.CompilationUnit;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import main.Obfuscator;

/**
 *
 * @author cuonglb
 */
public class FileParser {

    private ArrayList<String> impls = new ArrayList<String>();
    private ArrayList<String> extendsClass = new ArrayList<String>();

    public ArrayList<ArrayList<String>> RenameMethod(String fileName, ArrayList<String> referenceFile) throws FileNotFoundException, ParseException, IOException, Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(fileName);
        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);


        } finally {
            in.close();
        }
        MethodChangerVisitor mcv = new MethodChangerVisitor();
        ArrayList<String> listMethod = mcv.getAllMethod(cu);
        ArrayList<String> listVars = mcv.getAllVariable(cu);
        ArrayList<String> listClassNames = mcv.getClassName(cu);

        boolean hasMain = mcv.isHasMain();
        impls = mcv.getImpls();
        extendsClass = mcv.getExtendsClass();
        ArrayList<String> listFormVars = mcv.getFormVars();

//        ArrayList<String> allString = mcv.getAllString(cu.toString());
//        for (int i = 0; i < allString.size(); i++) {
//            if (referenceFile != null && referenceFile.size() > 0) {
//                for (int j = 0; j < referenceFile.size(); j++) {
//                    RenameInRef(referenceFile.get(j), allString.get(i).toString(), "\""+i+"\"");
//                }
//            }
//        }

        if (listMethod != null || listMethod.size() > 0) {
            for (int i = 0; i < listMethod.size(); i++) {
                //sinh ngau nhien ten moi cho method
                String newName = RandomStringGenerator.generateRandomName(3, 10, RandomStringGenerator.Mode.SMETHOD, Obfuscator.listExistedName);
                //String newName = RandomStringGenerator.generateRandomString(10, RandomStringGenerator.Mode.SMETHOD);
                String methodName = listMethod.get(i);

                boolean re = ofFormVar(methodName, listFormVars);
                if (re == false) {
                    if (!methodName.equals("initComponents") && !methodName.equals("run") && !methodName.equals("main") && (!methodName.equals("add")) && (!methodName.equals("actionPerformed"))) {
                        Rename(fileName, methodName, newName);
                        //add newName to list Existed Name IN ORDER TO check when generate new name
                        Obfuscator.listExistedName.add(newName);

                        if (referenceFile != null && referenceFile.size() > 0) {
                            for (int j = 0; j < referenceFile.size(); j++) {
                                RenameInRef(referenceFile.get(j), methodName, newName);
                            }
                        }
                    }
                }
            }
        }
        if (listVars != null || listVars.size() > 0) {
            for (int i = 0; i < listVars.size(); i++) {
                //String newName = RandomStringGenerator.generateRandomString(10, RandomStringGenerator.Mode.SPROPERTY);
                String newName = RandomStringGenerator.generateRandomName(3, 10, RandomStringGenerator.Mode.SPROPERTY, Obfuscator.listExistedName);
                String varName = listVars.get(i);
                Rename(fileName, varName, newName);
                Obfuscator.listExistedName.add(newName);
                if (referenceFile != null && referenceFile.size() > 0) {
                    for (int j = 0; j < referenceFile.size(); j++) {
                        RenameInRef(referenceFile.get(j), varName, newName);
                    }
                }
            }
        }

        ArrayList<ArrayList<String>> listClasses = new ArrayList<ArrayList<String>>();
        if (listClassNames != null || listClassNames.size() > 0) {

            for (int i = 0; i < listClassNames.size(); i++) {
                //String newName = RandomStringGenerator.generateRandomString(10, RandomStringGenerator.Mode.SCLASS);
                String newName = RandomStringGenerator.generateRandomName(3, 10, RandomStringGenerator.Mode.SCLASS, Obfuscator.listExistedName);
                String varName = listClassNames.get(i);
                if (hasMain == false) {
                    Rename(fileName, varName, newName);
                    Obfuscator.listExistedName.add(newName);

                    ArrayList<String> arr = new ArrayList<String>();
                    arr.add(fileName);
                    arr.add(fileName.replace(varName, newName));
                    listClasses.add(arr);
                    if (referenceFile != null && referenceFile.size() > 0) {
                        for (int j = 0; j < referenceFile.size(); j++) {
                            RenameInRef(referenceFile.get(j), varName, newName);
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < allString.size(); i++) {
//            if (referenceFile != null && referenceFile.size() > 0) {
//                for (int j = 0; j < referenceFile.size(); j++) {
//                    RenameInRef(referenceFile.get(j),"\""+i+"\"" , allString.get(i));
//                }
//            }
//        }

//        FileInputStream in1 = new FileInputStream(fileName);
//        CompilationUnit cu1;
//        try {
//            cu1 = JavaParser.parse(in1);
//        } finally {
//            in.close();
//        }
//
//        List comment = cu1.getComments();
//        String nf = cu1.toString();
//
//        for (int i = 0; i < comment.size(); i++) {
//            String[] st = ((Comment) comment.get(i)).toString().split("\n");
//            for (int j = 0; j < st.length; j++) {
//                nf = nf.replace(st[j], "");
//            }
//        }
//
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//            bw.write(nf);
//            bw.flush();
//            bw.close();
//        } catch (Exception e) {
//        }
        return listClasses;
    }

    public boolean ofFormVar(String methodName, ArrayList<String> listFormVars) {
        if (listFormVars != null && listFormVars.size() > 0) {
            for (int i = 0; i < listFormVars.size(); i++) {
                boolean re = methodName.contains(listFormVars.get(i));
                if (re == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public void RenameInRef(String fileName, String Name, String newName) throws FileNotFoundException, IOException, ParseException {
        FileInputStream in = new FileInputStream(fileName);
        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        MethodChangerVisitor mcv = new MethodChangerVisitor();
        ArrayList<String> listMethod = mcv.getAllMethod(cu);
        ArrayList<String> listVars = mcv.getAllVariable(cu);
        ArrayList<String> listClassNames = mcv.getClassName(cu);
        File f = new File(fileName);

        String nameClass = f.getName();
        int pos = nameClass.lastIndexOf(".");
        if (pos > 0) {
            nameClass = nameClass.substring(0, pos);
        }

        boolean isExtendorImpl = false;
        if (impls != null && impls.size() > 0) {
            for (int i = 0; i < impls.size(); i++) {
                if (nameClass.equals(impls.get(i))) {
                    isExtendorImpl = true;
                    break;
                }
            }
        }
        if (extendsClass != null && extendsClass.size() > 0) {
            for (int i = 0; i < extendsClass.size(); i++) {
                if (nameClass.equals(extendsClass.get(i))) {
                    isExtendorImpl = true;
                    break;
                }
            }
        }

        if (isExtendorImpl == true) {
            if (listVars != null || listVars.size() > 0) {
                for (int i = 0; i < listVars.size(); i++) {
                    if (Name.equals(listVars.get(i))) {
                        return;
                    }
                }
            }
            if (listClassNames != null || listClassNames.size() > 0) {
                for (int i = 0; i < listClassNames.size(); i++) {
                    if (Name.equals(listClassNames.get(i))) {
                        return;
                    }
                }
            }
        } else {
            if (listMethod != null || listMethod.size() > 0) {
                for (int i = 0; i < listMethod.size(); i++) {
                    if (Name.equals(listMethod.get(i))) {
                        return;
                    }
                }
            }
            if (listVars != null || listVars.size() > 0) {
                for (int i = 0; i < listVars.size(); i++) {
                    if (Name.equals(listVars.get(i))) {
                        return;
                    }
                }
            }
            if (listClassNames != null || listClassNames.size() > 0) {
                for (int i = 0; i < listClassNames.size(); i++) {
                    if (Name.equals(listClassNames.get(i))) {
                        return;
                    }
                }
            }
        }

        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line;
        String newContent = "";
        while ((line = bf.readLine()) != null) {
            if ((line.contains("package")) && (line.contains(Name))) {
                newContent += line + "\n";
            } else {
                //line = line.replace(Name, newName);
                line = line.replaceAll(Name, newName);
                newContent += line + "\n";
            }
        }
        bf.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
        out.write(newContent);
        out.close();
    }

    public void Rename(String fileName, String Name, String newName) throws FileNotFoundException, IOException, ParseException {
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line;
        String newContent = "";
        while ((line = bf.readLine()) != null) {
            if ((line.contains("package")) && (line.contains(Name))) {
                newContent += line + "\n";
            } else {
                //line = line.replace(Name, newName);
                line = line.replaceAll(Name, newName);
                newContent += line + "\n";
            }
        }
        bf.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
        out.write(newContent);
        out.close();
    }
}
