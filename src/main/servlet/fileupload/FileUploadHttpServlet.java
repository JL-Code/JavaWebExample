package main.servlet.fileupload;


import javafx.scene.shape.Path;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

/**
 * https://www.mkyong.com/java/how-to-create-directory-in-java/
 * package com.mkyong.file;
 * <p>
 * import java.io.IOException;
 * import java.nio.file.Files;
 * import java.nio.file.Path;
 * import java.nio.file.Paths;
 * <p>
 * public class CreateDirectoryExample {
 * public static void main(String[] args) {
 * <p>
 * Path path = Paths.get("C:\\Directory2\\Sub2\\Sub-Sub2");
 * //if directory exists?
 * if (!Files.exists(path)) {
 * try {
 * Files.createDirectories(path);
 * } catch (IOException e) {
 * //fail to create directory
 * e.printStackTrace();
 * }
 * }
 * <p>
 * }
 * <p>
 * }
 */

public class FileUploadHttpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String dirRoot = getServletContext().getRealPath("/");
        FileOutputStream outputStream = null;
        try {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            // 设置请求头编码,防止中文名称乱码
            servletFileUpload.setHeaderEncoding("utf-8");
            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
            String fileDir = dirRoot + "uploads";
            File file = new File(fileDir);
            // 当文件夹不存在时，自动创建目录，mkdirs 递归创建目录  mkdir 只创建一个目录。
            if (!file.exists()) {
                file.mkdirs();
            }
            for (FileItem fileItem : fileItemList) {
                System.out.println(fileItem.getFieldName());
                System.out.println(fileItem.getContentType());
                System.out.println(fileItem.getName());
                System.out.println(fileItem.getSize());
                System.out.println(fileItem.isFormField());
                if (!fileItem.isFormField()) {
                    String fullFileName = Paths.get(fileDir, new File(fileItem.getName()).getName()).toString();
                    System.out.println(file.isDirectory());
                    System.out.println(file.exists());
                    InputStream inputStream = fileItem.getInputStream();
                    outputStream = new FileOutputStream(fullFileName);
                    IOUtils.copy(inputStream, outputStream);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

        response.sendRedirect(getServletContext().getContextPath());
    }

}
