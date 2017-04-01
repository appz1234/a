package servlet;

import DirDel.DirDel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Obfuscator;
import obfuscator.FileAndDirectory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import zip.UnzipDir;
import zip.ZipDir;

/**
 * A Java servlet that handles file upload from client.
 *
 * @author www.codejava.net
 */
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    public String fileName;
    public String filePath;
    public String zipFile = null;

    /**
     * handles file upload via HTTP POST method
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        String uploadPath = "D:\\demo\\upload";
        File folder = new File(uploadPath);
        DirDel.Del(folder);
        folder.mkdir();

        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();

            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    fileName = new File(item.getName()).getName();
                    filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);

                    // saves the file on disk
                    item.write(storeFile);
                }
            }
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "Error when upload file!");
            return;
        }

        if (!isZipFile(filePath)) {
            request.setAttribute("zip", "not zip file!");
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        boolean unzip = UnzipDir.extract(new File("D:\\demo\\upload\\"+fileName), new File("D:\\demo\\Obfuscated"));
        if (!unzip) {
//            request.setAttribute("zip", "can not unzip file!");
//            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);           
        }
        
//        UnZip u = new UnZip();
//        if (!u.unZipIt(filePath)) {
//            request.setAttribute("zip", "can not unzip file!");
//            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
//        }

        Obfuscator o = new Obfuscator();
        String ok = o.Obfuscator("D:\\demo\\Obfuscated");
        if (ok.equals("y")) {
            request.setAttribute("ok", "File obfuscated!");
        } else if (ok.equals("n")) {
            request.setAttribute("ok", "Obfuscate failure!");
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        } else if (ok.equals("j")) {
            request.setAttribute("ok", "Directory is not contain any java file!");
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        ZipDir z = new ZipDir();
        if (!z.ZipDir()) {
            request.setAttribute("zip", "zip failure!");
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        }

//        ZipFile z = new ZipFile();
//        boolean ZipFile = z.ZipFile("D:\\demo\\unzip");
//        if (ZipFile) {
//            request.setAttribute("zip", "File have been zipped!");
//        } else {
//            request.setAttribute("zip", "zip failure!");
//            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
//        }

        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private boolean isZipFile(String filePath) {

        File file = new File(filePath);
        boolean ok = false;
        if (file.isFile()) {
            String ext = FileAndDirectory.getExtentionFileName(file);
            if (ext.equals("zip")) {
                ok = true;
            }
        }
        return ok;
    }
}