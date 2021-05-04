package com.blog.service;

import com.blog.pojo.TCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MR
 * @since 2021-04-19
 */
public interface TCategoryService extends IService<TCategory> {

    int addCategory(TCategory category);

    int deleteCategory(Long id);

    int updateCategory(TCategory category);

    HashMap<String, Object> categoryList(Long pn, Long size, String name);

    List<TCategory> allCategory();

    List<TCategory> fiveCategory();

    TCategory findById(Long id);
}
