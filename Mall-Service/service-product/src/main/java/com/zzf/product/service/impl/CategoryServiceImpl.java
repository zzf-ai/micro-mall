package com.zzf.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzf.common.constant.Constants;
import com.zzf.model.entity.product.Category;
import com.zzf.product.mapper.CategoryMapper;
import com.zzf.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zzf
 * @date 2024-01-16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Category> findFirstCategory() {

        //从redis缓存中查询所有的一级分类数据
        String categoryListJSON = redisTemplate.opsForValue().get("category:one");
        if(StringUtils.hasText(categoryListJSON)){
            List<Category> categoryList = JSON.parseArray(categoryListJSON, Category.class);
            return categoryList;
        }
        List<Category> categoryList = categoryMapper.findOneCategory();
        redisTemplate.opsForValue().set(Constants.CATEGORYONE_KEY, JSON.toJSONString(categoryList), 7, TimeUnit.DAYS);
        return categoryList;
    }

    @Cacheable(value = "category" , key = "'all'")
    @Override
    public List<Category> findCategoryTree() {

        //查询所有分类
        List<Category> categoryList = categoryMapper.findAll();
        //通过条件parentid=0得到所有的一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0).collect(Collectors.toList());
        //遍历一级分类的集合，通过id=parentId，得到二级分类
        if(!CollectionUtils.isEmpty(oneCategoryList)){
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);

                //遍历二级分类的集合，通过id=parentId，得到三级分类
                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
