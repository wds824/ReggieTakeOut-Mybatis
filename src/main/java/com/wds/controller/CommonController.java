package com.wds.controller;

import cn.hutool.core.util.IdUtil;
import com.wds.common.JsonResult;
import com.wds.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-20 23:12
 */
@RestController
public class CommonController {
    /**
     * 绑定主页
     */
    @GetMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/static/index.html");
    }

    @Value("${reggie.basePath}")
    private String basePath;

    @GetMapping("/common/download")
    public void downloadImg(String name,HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream inputStream = new FileInputStream(basePath + name);

        byte[] data = new byte[1024];
        int len ;
        while ((len = inputStream.read(data)) > 0){
            outputStream.write(data, 0, len);
        }

        inputStream.close();
        outputStream.close();
    }

    @PostMapping("/common/upload")
    public JsonResult upload(@RequestBody MultipartFile file) throws IOException {
        //重命名
        String oldName = file.getOriginalFilename();
        if (oldName == null) {
            throw new CustomException("未知错误: fileName is null");
        }
        String type = oldName.substring(oldName.lastIndexOf('.'));
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + type;


        File finalFile = new File(basePath + newName);
        file.transferTo(finalFile);

        return JsonResult.ok(newName);
    }
}
