package com.blog.mapper;

import com.blog.pojo.TCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MR
 * @since 2021-04-19
 */
@Repository
@Mapper
public interface TCategoryMapper extends BaseMapper<TCategory> {

    @Select("Select * from t_category WHERE deleted=0 ORDER BY num DESC LIMIT 5")
    List<TCategory> fiveCategory();

}
