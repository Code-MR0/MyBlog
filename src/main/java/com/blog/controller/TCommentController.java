package com.blog.controller;


import com.blog.pojo.TComment;
import com.blog.service.TCommentService;
import com.blog.utils.CommentDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MR
 * @since 2021-04-24
 */
@Controller
public class TCommentController {

    @Autowired
    private TCommentService commentService;

    /**
     * 根据文章id获取评论列表
     *
     * @param articleId
     * @return
     */
    @GetMapping("/comment/{articleId}")
    public String comment(@PathVariable Long articleId, ModelMap map) {
        List<TComment> listByArticleId = commentService.findListByArticleId(articleId);
        for (TComment comment : listByArticleId) {
            List<TComment> sonCommentList = commentService.findSonCommentListByReplyId(comment.getId(), articleId);
            for (TComment son : sonCommentList){
                son.setReplyDate(CommentDateUtil.getCommentDate(son.getPublishDate()));
            }
            comment.setSonComment(sonCommentList);
            comment.setReplyDate(CommentDateUtil.getCommentDate(comment.getPublishDate()));
        }
        map.put("commentDataList", listByArticleId);
        return "blog :: commentList";
    }

    @PostMapping("/addComment")
    public String addComment(TComment comment) {
        commentService.addComment(comment);
        return "redirect:/comment/" + comment.getArticleId();
    }

}

