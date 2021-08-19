package com.kuang.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class FileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入doPost方法");
        //判断上传的文件是普通表单还是带文件的表单
        if(!ServletFileUpload.isMultipartContent(req)){
            System.out.println("普通表单");
            return;//禁止方法运行，说明这是一个普通表单，直接返回
        }

        //创建上传文件的保存路径，建议在WEB-INF路径下面，安全，用户无法直接访问上传的文件
        String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdir(); //创建这个目录
        }
        //临时文件
        String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdir(); //创建这个目录
        }


        DiskFileItemFactory factory = getDiskFileItemFactory(file);
        ServletFileUpload upload = getServletFileUpload(factory);
        String msg = uploadParseRequest(upload,req,uploadPath);

        //servlet请求转发消息
        req.setAttribute("msg",msg);
        //跳转到info.jsp
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public static DiskFileItemFactory getDiskFileItemFactory(File file){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);
        factory.setRepository(file);
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        ServletFileUpload upload = new ServletFileUpload(factory);

        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            //l:已经读取到文件大小
            //l1:文件大小
            public void update(long l, long l1, int i) {
                System.out.println("总大小" + l1 + "已上传：" + l);
            }
        });

        //处理乱码
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024*1024*10);
        //设置总共能够上传文件的大小:10MB
        upload.setSizeMax(1024*1024*10);

        return upload;
    }

    public static String uploadParseRequest(ServletFileUpload upload,HttpServletRequest req,String uploadPath){
        String msg="error";
        //处理上传文件
        try {
            //把前端请求解析，封装成一个FileItem类，需要从ServletFileUpload对象中获取
            List<FileItem> fileItems = upload.parseRequest(req);
            //fileItem表示每一个表单对象
            for (FileItem fileItem : fileItems) {
                //判断上传的文件是普通表单还是带文件的表单
                if (fileItem.isFormField()){
                    String name = fileItem.getFieldName();//getFieldName指前端表单控件的name
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name+":"+ value);
                }else {
                    //文件
                    //处理文件
                    String uploadFileName = fileItem.getName();
                    if (uploadFileName.trim().equals("")||uploadFileName==null){
                        continue;
                    }
                    //获取上传的文件名：/images/girl/paojie.png
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                    //获取文件的后缀名
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    String uuidPath = UUID.randomUUID().toString();
                    //存放地址
                    //文件真实存在的路径realPath
                    String realPath = uploadPath+"/"+uuidPath;
                    File realPathFile = new File(realPath);
                    if (!realPathFile.exists()){
                        realPathFile.mkdir();
                    }
                    //文件传输完毕
                    InputStream inputStream = fileItem.getInputStream();
                    FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);
                    byte[] buffer = new byte[1024 * 1024];
                    int len = 0;
                    while ((len=inputStream.read(buffer))>0){
                        fos.write(buffer,0,len);
                    }
                    fos.close();
                    inputStream.close();
                    fileItem.delete();  //上传成功，清楚临时文件
                    msg = "上传成功";
                }
            }

        } catch (FileUploadException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  msg;
    }
}
