package com.zzf.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;
import com.zzf.common.constant.Constants;
import com.zzf.manager.mapper.CategoryMapper;
import com.zzf.model.entity.product.Category;
import com.zzf.model.vo.product.CategoryExcelVo;

import java.util.List;
import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-11
 */
public class ExcelListener<T> implements ReadListener<T> {

    private List cachedDataList = Lists.newArrayListWithExpectedSize(Constants.BATCH_COUNT);

    //获取mapper对象
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    //每解析一行数据就会调用一次该方法
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        cachedDataList.add(t);
        //达到BATCH_COUNT就写入数据库一次
        if(cachedDataList.size() >= Constants.BATCH_COUNT){
            saveData();
            //存储完清理缓存
            cachedDataList = Lists.newArrayListWithExpectedSize(Constants.BATCH_COUNT);
        }
    }

    private void saveData() {
        categoryMapper.batchInsert((List<CategoryExcelVo>)cachedDataList);
    }

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //excel解析完后要确保最后遗留的数据也存储到数据库
        saveData();
    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return false;
    }
}
