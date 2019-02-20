package com.shop.ssm.service.impl;

import com.shop.ssm.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {


    public File findPathByFileName(String dir,String fileName) {
        File file=new File(dir);
        File[] fileList=file.listFiles();
        for (File tempFile:fileList){
            if(tempFile.getName().equals(fileName)){
                file=tempFile;
            }
        }
        return file;
    }
}
