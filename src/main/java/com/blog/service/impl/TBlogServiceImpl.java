package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.mapper.TCategoryMapper;
import com.blog.pojo.TBlog;
import com.blog.mapper.TBlogMapper;
import com.blog.pojo.TCategory;
import com.blog.service.TBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MR
 * @since 2021-04-16
 */
@Service
public class TBlogServiceImpl extends ServiceImpl<TBlogMapper, TBlog> implements TBlogService {

    @Autowired
    private TBlogMapper blogMapper;

    @Autowired
    private TCategoryMapper categoryMapper;

    /**
     * 获取分页博客数据
     *
     * @param pn
     * @param size
     * @param queryWrapper
     * @return
     */
    private HashMap<String, Object> getBlogList(Long pn, Long size, QueryWrapper<TBlog> queryWrapper) {
        HashMap<String, Object> map = new HashMap<>();
        if (pn < 1)
            pn = 1L;
        Page<TBlog> page = new Page<>(pn, size);
        Page<TBlog> selectPage = blogMapper.selectPage(page, queryWrapper);
        long pages = selectPage.getPages();
        if (pn > pages) {
            page = new Page<>(pages, size);
            selectPage = blogMapper.selectPage(page, queryWrapper);
        }
        map.put("blogList", selectPage.getRecords());
        map.put("pages", selectPage.getPages());
        return map;
    }

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    @Override
    public int addBlog(TBlog blog) {
        TCategory category = categoryMapper.selectById(blog.getCategory());
        category.setNum(category.getNum()+1);
        categoryMapper.updateById(category);
        return blogMapper.insert(blog);
    }

    /**
     * 更新博客
     *
     * @param blog
     * @return
     */
    @Override
    public int updateBlog(TBlog blog) {
        TBlog selectById = blogMapper.selectById(blog.getId());
        if (!selectById.getCategory().equals(blog.getCategory())) {
            TCategory category = categoryMapper.selectById(blog.getCategory());
            category.setNum(category.getNum()+1);
            categoryMapper.updateById(category);
            TCategory category1 = categoryMapper.selectById(selectById.getCategory());
            category1.setNum(category1.getNum()-1);
            categoryMapper.updateById(category1);
            selectById.setCategory(blog.getCategory());
        }
        selectById.setTags(blog.getTags());
        selectById.setContent(blog.getContent());
        selectById.setDescription(blog.getDescription());
        selectById.setFirstImg(blog.getFirstImg());
        selectById.setComment(blog.getComment());
        selectById.setRecommend(blog.getRecommend());
        selectById.setTitle(blog.getTitle());
        selectById.setStatus(blog.getStatus());
        selectById.setUpdateDate(new Date());
        return blogMapper.updateById(selectById);
    }

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteById(id);
    }

    /**
     * 根据id查询博客
     *
     * @param id
     * @return
     */
    @Override
    public TBlog findBlogById(Long id) {
        return blogMapper.selectById(id);
    }

    /**
     * 获取所有博客
     *
     * @param pn
     * @param size
     * @param category
     * @param title
     * @return
     */
    @Override
    public HashMap<String, Object> blogList(Long pn, Long size, String category, String title) {
        QueryWrapper<TBlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_date");
        if (category != null && !category.equals("")) {
            queryWrapper.eq("category", category);
        }
        if (title != null && !title.equals("")) {
            queryWrapper.like("title", title);
        }
        return getBlogList(pn, size, queryWrapper);
    }

    @Override
    public List<TBlog> allBlog() {
        QueryWrapper<TBlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_date");
        return blogMapper.selectList(queryWrapper);
    }

    @Override
    public List<TBlog> sixBlog() {
        return blogMapper.sixBlog();
    }

    @Override
    public List<TBlog> findByText(String text) {
        QueryWrapper<TBlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",text);
        queryWrapper.or();
        queryWrapper.like("content",text);
        return blogMapper.selectList(queryWrapper);
    }

    @Override
    public int blogCount() {
        return blogMapper.selectCount(null);
    }

    @Override
    public int addView(Long id) {
        TBlog selectById = blogMapper.selectById(id);
        selectById.setViews(selectById.getViews()+1);
        return blogMapper.updateById(selectById);
    }
}
