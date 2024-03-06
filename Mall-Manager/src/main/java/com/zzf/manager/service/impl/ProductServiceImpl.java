package com.zzf.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.manager.mapper.ProductDetailsMapper;
import com.zzf.manager.mapper.ProductMapper;
import com.zzf.manager.mapper.ProductSkuMapper;
import com.zzf.manager.service.ProductService;
import com.zzf.model.dto.product.ProductDto;
import com.zzf.model.entity.product.Product;
import com.zzf.model.entity.product.ProductDetails;
import com.zzf.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page , limit);
        List<Product> productList =  productMapper.findByPage(productDto);
        return new PageInfo<>(productList);
    }

    @Transactional
    @Override
    public void save(Product product) {
        //设置两个状态
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);

        //获取商品sku列表集合，保存sku信息，product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i = 0; i < productSkuList.size(); i++){
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);
            productSku.setStatus(0);
            productSkuMapper.save(productSku);
        }

        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    @Override
    public Product getById(Long id) {
        //根据id查询商品基本数据
        Product product = productMapper.selectById(id);

        //根据商品id查询sku数据
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(productSkuList);

        //根据商品id查询商品详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        product.setDetailsImageUrls(productDetails.getImageUrls());

        return product;
    }

    @Override
    public void updateById(Product product) {
        // 修改商品基本数据
        productMapper.updateById(product);

        // 修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> productSkuMapper.updateById(productSku));

        // 修改商品的详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        //根据id删除商品的基本数据
        productMapper.deleteById(id);
        //根据id删除商品的sku数据
        productSkuMapper.deleteByProductId(id);
        //根据id删除商品的详情数据
        productDetailsMapper.deleteByProductId(id);
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1){
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        }else{
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }

}
