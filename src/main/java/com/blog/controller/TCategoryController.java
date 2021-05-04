package com.blog.controller;


import com.blog.pojo.TCategory;
import com.blog.service.TCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MR
 * @since 2021-04-19
 */
@Controller
public class TCategoryController {

    @Autowired
    private TCategoryService categoryService;

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    @PostMapping("/admin/addCategory")
    @ResponseBody
    public HashMap<String, Object> addCategory(TCategory category) {
        HashMap<String, Object> hashMap = new HashMap<>();
        System.out.println(category);
        int re = categoryService.addCategory(category);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @PostMapping("/admin/deleteCategory")
    @ResponseBody
    public HashMap<String, Object> deleteCategory(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int re = categoryService.deleteCategory(id);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    /**
     * 更新分类
     *
     * @param category
     * @return
     */
    @PostMapping("/admin/updateCategory")
    @ResponseBody
    public HashMap<String, Object> updateCategory(TCategory category) {
        System.out.println(category);
        HashMap<String, Object> hashMap = new HashMap<>();
        int re = categoryService.updateCategory(category);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    /**
     * 后台分类列表
     *
     * @param map
     * @param pn   当前页
     * @param size 页大小
     * @param name
     * @return
     */
    @GetMapping("/admin/categoryList")
    public String categoryList(ModelMap map,
                           @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                           @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                           @RequestParam(value = "name", required = false) String name) {
        getData(map, pn, size, name);
        return "admin/categoryList";
    }

    /**
     * 前台分类列表
     * @param map
     * @param pn
     * @param size
     * @param name
     * @return
     */
    @GetMapping("/categories")
    public String categories(ModelMap map,
                             @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                             @RequestParam(value = "size", required = false, defaultValue = "9") Long size,
                             @RequestParam(value = "name", required = false) String name) {
        getData(map, pn, size, name);
        return "categories";
    }

    private void getData(ModelMap map,
                         @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                         @RequestParam(value = "size", required = false, defaultValue = "9") Long size,
                         @RequestParam(value = "name", required = false) String name) {
        HashMap<String, Object> hashMap = categoryService.categoryList(pn, size, name);
        map.put("name", name);
        map.put("pn", pn);
        map.put("size", size);
        map.put("categoryList", hashMap.get("categoryList"));
        map.put("pages", hashMap.get("pages"));
    }
}

