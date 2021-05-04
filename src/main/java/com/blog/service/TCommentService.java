package com.blog.service;

import com.blog.pojo.TComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MR
 * @since 2021-04-24
 */
public interface TCommentService extends IService<TComment> {

    void addComment(TComment comment);

    List<TComment> findListByArticleId(Long articleId);

    List<TComment> findSonCommentListByReplyId(Long ReplyId,Long articleId);

    int commentCount();

}
