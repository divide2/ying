package com.ying.product.service.impl;

import com.ying.auth.model.UserCompany;
import com.ying.auth.repo.UserCompanyRepository;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.product.dto.ProductDTO;
import com.ying.product.dto.ProductUpdateDTO;
import com.ying.product.model.Product;
import com.ying.product.model.ProductSpec;
import com.ying.product.repo.ProductRepository;
import com.ying.product.repo.ProductSpecRepository;
import com.ying.product.service.ProductService;
import com.ying.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Service
public class ProductServiceImpl extends SimpleBasicServiceImpl<Product, Integer, ProductRepository> implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSpecRepository productSpecRepository;
    private final UserCompanyRepository userCompanyRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductSpecRepository productSpecRepository,
                              UserCompanyRepository userCompanyRepository) {
        this.productRepository = productRepository;
        this.productSpecRepository = productSpecRepository;

        this.userCompanyRepository = userCompanyRepository;
    }

    private ProductVO mergeProductSpecs(Product product) {
        ProductVO vo = ProductVO.of(product);
        List<ProductSpec> productSpecs = productSpecRepository.findByProductId(product.getId());
        vo.setSpecs(productSpecs);
        return vo;
    }
    @Override
    public Page<ProductVO> findByUserCompany(Integer userId, Pageable pageable) {
        UserCompany userCompany = userCompanyRepository.getByUserId(userId);
        return this.findByCompany(userCompany.getCompanyId(), pageable);
    }

    @Override
    public Page<ProductVO> findByUser(Integer userId, Pageable pageable) {
        Page<Product> products = productRepository.findByFromId(userId, pageable);
        return products.map(this::mergeProductSpecs);
    }

    @Override
    public Page<ProductVO> findByCompany(Integer companyId, Pageable pageable) {
        Page<Product> products = productRepository.findByCompanyId(companyId, pageable);
        return products.map(this::mergeProductSpecs);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ProductDTO dto) {
        Product product = new Product();
        // fixme
        product.setCompanyId(Loginer.companyId());
        product.setFromId(Loginer.userId());
        product.setFromName(Loginer.username());
        product.setCreateTime(LocalDateTime.now());
        product.setEnabled(true);
        product.setImage(dto.getImage());
        product.setName(dto.getName());
        product.setRemarks(dto.getRemarks());
        this.add(product);
        // todo 消耗规则 暂时不用 下一版本再说
        this.saveSpec(product.getId(), dto.getSpecs());
    }


    @Override
    @Transactional
    public void update(ProductUpdateDTO dto) {
        Product product = this.get(dto.getId());
        product.setUpdateTime(LocalDateTime.now());
        product.setImage(dto.getImage());
        product.setName(dto.getName());
        product.setRemarks(dto.getRemarks());
        productSpecRepository.deleteByProductId(product.getId());
        this.saveSpec(product.getId(), dto.getSpecs());
    }

    private void saveSpec(Integer productId, List<ProductSpec> specs) {
        specs.forEach(spec -> {
            ProductSpec productSpec = new ProductSpec();
            productSpec.setProductId(productId);
            productSpec.setPrice(spec.getPrice());
            productSpec.setName(spec.getName());
            productSpec.setImage(spec.getImage());
            productSpecRepository.save(productSpec);
        });
    }

    @Override
    public Page<ProductDTO> findInfo(Pageable pageable) {
        return null;
    }

    @Override
    public ProductVO getVO(Integer productId) {
        Product product = this.get(productId);
        return this.mergeProductSpecs(product);
    }

    @Override
    public List<ProductVO> findByIds(List<Integer> ids) {

        List<Product> products = productRepository.findByIdIn(ids);
        return products.stream().map(this::mergeProductSpecs).collect(Collectors.toList());
    }
}
