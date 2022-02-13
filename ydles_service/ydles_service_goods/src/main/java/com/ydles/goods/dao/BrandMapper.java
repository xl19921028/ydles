package com.ydles.goods.dao;

import com.ydles.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BrandMapper extends Mapper<Brand> {

    @Select("SELECT name,img FROM tb_brand WHERE id\n" +
            "IN (SELECT brand_id FROM tb_category_brand WHERE\n" +
            "category_id IN (SELECT id FROM tb_category WHERE\n" +
            "NAME=${categoryName}) )order by seq")
    public List<Map> findByCategoryName(@Param("categoryName") String categoryName);

}
