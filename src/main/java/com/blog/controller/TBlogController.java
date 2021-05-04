package com.blog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.mapper.TBlogMapper;
import com.blog.pojo.TBlog;
import com.blog.pojo.TComment;
import com.blog.service.TBlogService;
import com.blog.service.TCategoryService;
import com.blog.service.TCommentService;
import com.blog.utils.CommentDateUtil;
import com.blog.utils.MarkDownUtil;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MR
 * @since 2021-04-16
 */
@Controller
public class TBlogController {

    @Autowired
    private TBlogService blogService;

    @Autowired
    private TCategoryService categoryService;

    @Autowired
    private TCommentService commentService;

    /**
     * 博客新增编辑公用页
     *
     * @param map
     * @return
     */
    @GetMapping("/admin/editor")
    public String editor(ModelMap map, @RequestParam(value = "id", required = false) Long id) {
        TBlog blog;
        if (id != null)
            blog = blogService.findBlogById(id);
        else
            blog = new TBlog();
        map.put("blog", blog);
        map.put("allCategory", categoryService.allCategory());
        return "admin/editor";
    }

    /**
     * 新增（编辑）博客
     *
     * @param blog
     * @return
     */
    @PostMapping("/admin/addBlog")
    @ResponseBody
    public HashMap<String, Object> addBlog(TBlog blog) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int re;
        blog.setAuthor("马洪伟");
        if (blog.getId() == null)
            re = blogService.addBlog(blog);
        else
            re = blogService.updateBlog(blog);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @PostMapping("/admin/deleteBlog")
    @ResponseBody
    public HashMap<String, Object> deleteBlog(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int re = blogService.deleteBlog(id);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    /**
     * 博客列表
     *
     * @param map
     * @param pn   当前页
     * @param size 页大小
     * @return
     */
    @GetMapping("/admin/blogList")
    public String blogList(ModelMap map,
                           @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                           @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "title", required = false) String title) {
        getData(map, pn, size, category, title);
        map.put("allCategory", categoryService.allCategory());
        return "admin/blogList";
    }

    /**
     * 归档页
     *
     * @return
     */
    @GetMapping("/archives")
    public String archives(ModelMap map,
                           @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                           @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "title", required = false) String title) {
        getData(map, pn, size, null, null);
        return "archives";
    }

    private void getData(ModelMap map, @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn, @RequestParam(value = "size", required = false, defaultValue = "3") Long size, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "title", required = false) String title) {
        HashMap<String, Object> hashMap = blogService.blogList(pn, size, category, title);
        List<TBlog> blogList = (List<TBlog>) hashMap.get("blogList");
        for (TBlog blog : blogList) {
            try {
                blog.setCategory(categoryService.findById(Long.valueOf(blog.getCategory())).getName());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                blogList.remove(blog);
            }
        }
        map.put("category", category);
        map.put("title", title);
        map.put("pn", pn);
        map.put("size", size);
        map.put("blogList", blogList);
        map.put("pages", hashMap.get("pages"));
    }

    /**
     * 博客详情
     *
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(ModelMap map, @PathVariable String id) {
        TBlog blog = blogService.findBlogById(Long.valueOf(id));
        blogService.addView(Long.valueOf(id));
        blog.setContent(MarkDownUtil.mdToHtml(blog.getContent()));
        blog.setCategory(categoryService.findById(Long.valueOf(blog.getCategory())).getName());
        map.put("blog", blog);
        List<TComment> listByArticleId = commentService.findListByArticleId(Long.valueOf(id));
        for (TComment comment : listByArticleId) {
            List<TComment> sonCommentList = commentService.findSonCommentListByReplyId(comment.getId(), Long.valueOf(id));
            for (TComment son : sonCommentList){
                son.setReplyDate(CommentDateUtil.getCommentDate(son.getPublishDate()));
            }
            comment.setSonComment(sonCommentList);
            comment.setReplyDate(CommentDateUtil.getCommentDate(comment.getPublishDate()));
        }
        map.put("commentDataList", listByArticleId);
        return "blog";
    }


    @GetMapping(path = {"/index","/"})
    public String index(ModelMap map,
                        @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                        @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                        @RequestParam(value = "category", required = false, defaultValue = "") String category,
                        @RequestParam(value = "title", required = false, defaultValue = "") String title) {
        getData(map, pn, size, category, title);
        map.put("allCategory", categoryService.fiveCategory());
        map.put("sixBlog", blogService.sixBlog());
        map.put("blogCount", blogService.blogCount());
        map.put("commentCount", commentService.commentCount());
        return "index";
    }

    @GetMapping("/findByText")
    @ResponseBody
    public HashMap<String, Object> findByText(String text) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<TBlog> list = blogService.findByText(text);
        for (TBlog blog : list) {
            try {
                blog.setCategory(categoryService.findById(Long.valueOf(blog.getCategory())).getName());
                blog.setViews(Integer.valueOf(blog.getId().toString().substring(16, 19)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                list.remove(blog);
            }
        }
        hashMap.put("items", list);
        return hashMap;
    }
}

