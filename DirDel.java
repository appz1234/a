package DirDel;

import java.io.File;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author MANHKHUC
 */
public class DirDel {

    public static void Del(File folder) {
        if (folder.exists()) {
            if (folder.isDirectory()) {
                if (folder.list().length == 0) {
                    folder.delete();
                } else {
                    String files[] = folder.list();
                    for (String temp : files) {
                        File fileDelete = new File(folder, temp);
                        Del(fileDelete);
                    }
                    if (folder.list().length == 0) {
                        folder.delete();
                    }
                }

            } else {
                folder.delete();
            }
        }
    }

    public static void main(String[] args) {
        DirDel.Del(new File("D:\\demo\\upload"));
    }
}