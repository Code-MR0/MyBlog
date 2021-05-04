package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.TBlog;
import com.blog.pojo.TCategory;
import com.blog.mapper.TCategoryMapper;
import com.blog.service.TCategoryService;
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
 * @since 2021-04-19
 */
@Service
public class TCategoryServiceImpl extends ServiceImpl<TCategoryMapper, TCategory> implements TCategoryService {

    @Autowired
    private TCategoryMapper categoryMapper;

    /**
     * 获取分页分类数据
     *
     * @param pn
     * @param size
     * @param queryWrapper
     * @return
     */
    private HashMap<String, Object> getCategoryList(Long pn, Long size, QueryWrapper<TCategory> queryWrapper) {
        HashMap<String, Object> map = new HashMap<>();
        if (pn < 1)
            pn = 1L;
        Page<TCategory> page = new Page<>(pn, size);
        Page<TCategory> selectPage = categoryMapper.selectPage(page, queryWrapper);
        long pages = selectPage.getPages();
        if (pn > pages) {
            page = new Page<>(pages, size);
            selectPage = categoryMapper.selectPage(page, queryWrapper);
        }
        map.put("categoryList", selectPage.getRecords());
        map.put("pages", selectPage.getPages());
        return map;
    }

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Override
    public int addCategory(TCategory category) {
        return categoryMapper.insert(category);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @Override
    public int deleteCategory(Long id) {
        return categoryMapper.deleteById(id);
    }

    /**
     * 更新分类
     *
     * @param category
     * @return
     */
    @Override
    public int updateCategory(TCategory category) {
        TCategory selectById = categoryMapper.selectById(category.getId());
        if (category.getName() != null && !"".equals(category.getName()))
            selectById.setName(category.getName());
        if (category.getImg() != null && !"".equals(category.getImg()))
            selectById.setImg(category.getImg());
        selectById.setUpdateDate(new Date());
        selectById.setRecommend(category.getRecommend());
        return categoryMapper.updateById(selectById);
    }

    /**
     * 获取所有分类
     *
     * @param pn
     * @param size
     * @param name
     * @return
     */
    @Override
    public HashMap<String, Object> categoryList(Long pn, Long size, String name) {
        QueryWrapper<TCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_date");
        if (name != null && !name.equals("")) {
            queryWrapper.like("name", name);
        }
        return getCategoryList(pn, size, queryWrapper);
    }

    @Override
    public List<TCategory> allCategory() {
        return categoryMapper.selectList(null);
    }

    @Override
    public List<TCategory> fiveCategory() {
        return categoryMapper.fiveCategory();
    }

    @Override
    public TCategory findById(Long id) {
        return categoryMapper.selectById(id);
    }
}
