package com.blog.controller;


import com.blog.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @version 1.0
 * @Description TODO
 * @Author MR
 * @Date 2021/3/11 21:27
 */

@Controller
public class UpLoadController {

    @Autowired
    private OssUtil ossUtil;

    /**
     * 文件上传
     */
    @ResponseBody
    @PostMapping("/admin/editorToUpLoadFile")
    public HashMap<String,Object> uploadBlog(@RequestParam("editormd-image-file") MultipartFile file) {
        HashMap<String,Object> map = new HashMap<>();
        String filename = file.getOriginalFilename();
        try {
            assert filename != null;
            if (!"".equals(filename.trim())) {
                // 上传到OSS
                String uploadUrl = ossUtil.uploadPic(file);
                map.put("url",uploadUrl);
                map.put("success",1);
                map.put("message","上传成功");
                System.out.println(uploadUrl);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            map.replace("success",1,0);
            map.replace("message","上传成功","上传失败");
            System.out.println("上传失败");
        }
        return map;
    }

}
