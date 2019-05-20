package com.erp.controller;

import com.erp.bean.UploadInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 13:10
 */

@Controller
public class MainController {

    @RequestMapping("home")
    public String toHome() {
        System.out.println(111);
        return "home";
    }

    @RequestMapping("pic/upload")
    public @ResponseBody
    UploadInfo uploadImg(String dir, @RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpServletRequest request) {
        System.out.println(dir);
        String pathdir = null;
        if ("image".equals(dir)){
            pathdir="pic/";
        }else if ("flash".equals(dir)){
            pathdir="flash/";
        }else if ("media".equals(dir)){
            pathdir="media/";
        }else if ("file".equals(dir)){
            pathdir="file/";
        }else{
            pathdir = "others/";
        }
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (contentType.contains("text") || contentType.contains("msword")|| contentType.contains("pdf")
                || contentType.contains("audio") || contentType.contains("image") || contentType.contains("video") ||contentType.contains("application/octet-stream")) {
            String path = request.getSession().getServletContext().getRealPath("/WEB-INF/"+pathdir);
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            long time = new Date().getTime();
            String imgname = prefix + time + suffix;
            File imgfile = new File(path + imgname);
            if (!imgfile.getParentFile().exists()) {
                imgfile.getParentFile().mkdir();
            }
            try {
                file.transferTo(imgfile);
                return new UploadInfo(0, pathdir + imgname);
            } catch (IOException e) {
                e.printStackTrace();
                return new UploadInfo(1, null,"网络异常，请重新上传");
            }

        }else{
            //未知格式
            return new UploadInfo(1, null,"不支持此类文件上传");
        }

    }

    @RequestMapping("pic/delete")
    @ResponseBody
    public Map<String, String> deletePic(String picName,HttpServletRequest request){
        List<String> images=new ArrayList<>();
        images.add(picName);
        request.getSession().setAttribute("images",images);
        Map<String ,String > map =new HashMap<>();
        map.put("data","success");
        return map;
    }


    @RequestMapping("file/download")
    public void downloadFile(String fileName,HttpServletRequest req, HttpServletResponse resp){
        String filepath=req.getSession().getServletContext().getRealPath("/WEB-INF/")+fileName;
        InputStream bis=null;
        BufferedOutputStream out=null;
        try {
            bis=new BufferedInputStream(new FileInputStream(new File(filepath)));
            String name=fileName.substring(fileName.lastIndexOf("/"));
            name= URLEncoder.encode(name,"utf-8");
            resp.addHeader("Content-Disposition","attachment;filename="+name);
            resp.setContentType("multipart/form-data");
            out= new BufferedOutputStream(resp.getOutputStream());
            byte[] b=new byte[2048];
            int len=0;
            while((len = bis.read(b))!=-1){
                out.write(b,0,len);
                out.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @RequestMapping("file/upload")
    public @ResponseBody
    UploadInfo uploadfile( @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/file/");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
        long time = new Date().getTime();
        String newFileName = prefix + time + suffix;
        File newFile = new File(path + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        try {
            file.transferTo(newFile);
            return new UploadInfo(0, "/file/" + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new UploadInfo(1, null, "网络异常，请重新上传");
        }
    }



    @RequestMapping("file/delete")
    @ResponseBody
    public Map<String, String> deletefile(String fileName,HttpServletRequest request){
        Map<String ,String > map =new HashMap<>();

        String newFilePath=fileName.substring(fileName.lastIndexOf("=")+1);
        String path=request.getSession().getServletContext().getRealPath(newFilePath);
        File file =new File(path);
        boolean res=file.delete();
        System.out.println(res);
        map.put("data","success");
        return map;
    }

}
