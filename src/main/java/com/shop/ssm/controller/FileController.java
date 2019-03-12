package com.shop.ssm.controller;

import com.shop.ssm.service.FileService;
import com.shop.ssm.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Controller
@RequestMapping("/file")
public class FileController {
//    @Autowired
//    private FileService fileService;
//    /**
//     * 实现上传文件
//     * @param myfile
//     * @param request
//     * @return
//     */
//    @RequestMapping("/upload")
//    @ResponseBody
//    public String upload(@RequestParam("myfile")MultipartFile myfile,HttpServletRequest request){
//        String filename=myfile.getOriginalFilename();
//        String path=request.getSession().getServletContext().getRealPath("upload");
//        File dir=new File(path,filename);
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
//        try {
//            //MultipartFile自带的解析方法
//            myfile.transferTo(dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }
//
//    /**
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/download")
//    @ResponseBody
//    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String download =request.getSession().getServletContext().getRealPath("/upload");
//        FileUtil.downFile("test.docx", ".docx", download, response);
//    }
}
