package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.pojo.TComment;
import com.blog.mapper.TCommentMapper;
import com.blog.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.utils.CommentDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MR
 * @since 2021-04-24
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {
    @Autowired
    private TCommentMapper commentMapper;

    @Override
    public void addComment(TComment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<TComment> findListByArticleId(Long articleId) {
        QueryWrapper<TComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id",articleId);
        queryWrapper.eq("reply_id",-1);
        queryWrapper.orderByDesc("publish_date");
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public List<TComment> findSonCommentListByReplyId(Long ReplyId,Long articleId) {
        QueryWrapper<TComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id",articleId);
        queryWrapper.eq("reply_ancestor_id",ReplyId);
        queryWrapper.orderByDesc("publish_date");
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public int commentCount() {
        return commentMapper.selectCount(null);
    }
}
