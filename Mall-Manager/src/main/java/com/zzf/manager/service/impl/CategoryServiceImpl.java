package com.zzf.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.zzf.common.exception.CustomException;
import com.zzf.manager.listener.ExcelListener;
import com.zzf.manager.mapper.CategoryMapper;
import com.zzf.manager.service.CategoryService;
import com.zzf.model.entity.product.Category;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.product.CategoryExcelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzf
 * @date 2024-01-10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long parentId) {
        // 根据分类id查询它下面的所有的子分类数据
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)) {

            // 遍历分类的集合，获取每一个分类数据
            categoryList.forEach(item -> {

                // 查询该分类下子分类的数量
                int count = categoryMapper.countByParentId(item.getId());
                //前端需要判断HasChildren值，如果HasChildren为true则有下一级，显示下拉按钮
                if(count > 0) {
                    item.setHasChildren(true);
                } else {
                    item.setHasChildren(false);
                }

            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {

        try {
            // 设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            //防止中文乱码
            String fileName = URLEncoder.encode("分类数据", "UTF-8");

            //设置响应头信息（表示让文件以下载方式打开）
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询分类数据
            List<Category> categoryList = categoryMapper.selectAll();
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();

            //对象转换
            for(Category category : categoryList){
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(categoryExcelVo);
            }
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }

    }

    @Override
    public void importData(MultipartFile file) {
        //创建监听器对象，传递mapper对象
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);
        try {
            //调用read方法读取excel数据
            EasyExcel.read(file.getInputStream(),
                    CategoryExcelVo.class,
                    excelListener).sheet().doRead();
        } catch (IOException e) {
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }

    }
}
