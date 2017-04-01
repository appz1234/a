package zip;

import DirDel.DirDel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author MANHKHUC
 */
public class ZipDir {

    public boolean ZipDir() {
        FileOutputStream fos = null;
        File folder = new File("D:\\demo\\zip");
        DirDel.Del(folder);
        folder.mkdir();
        try {
            fos = new FileOutputStream("D:\\demo\\zip\\Obfuscated.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            addDirToZipArchive(zos, new File("D:\\demo\\Obfuscated\\"), null);
            zos.flush();
            fos.flush();
            zos.close();
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(ZipDir.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ZipDir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
        if (fileToZip == null || !fileToZip.exists()) {
            return;
        }

        String zipEntryName = fileToZip.getName();
        if (parrentDirectoryName != null && !parrentDirectoryName.isEmpty()) {
            zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
        }

        if (fileToZip.isDirectory()) {
            System.out.println("+" + zipEntryName);
            for (File file : fileToZip.listFiles()) {
                addDirToZipArchive(zos, file, zipEntryName);
            }
        } else {
            System.out.println("   " + zipEntryName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(fileToZip);
            zos.putNextEntry(new ZipEntry(zipEntryName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }
    }
}