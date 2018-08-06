package com.ruixun.sincfin.biz.module.product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.ProductLabelClient;
import com.ruixun.sincfin.biz.module.product.mapper.ProductLabelMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ProductLabelDto;
import com.ruixun.sincfin.domain.entity.ProductLabelEntity;
import com.ruixun.sincfin.domain.query.ProductLabelQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/productLabel")
public class ProductLabelService implements ProductLabelClient {
	
	@Resource
    private ProductLabelMapper productLabelMapper;

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getById")
	public ProductLabelDto getById(@RequestParam Long id) {

		ProductLabelEntity entity  = productLabelMapper.selectByPrimaryKey(id);
		if (entity == null) {
			return null;
		}
		ProductLabelDto dto = new ProductLabelDto();
		BeanHelper.copyProperties(dto, entity);

		return dto;
	}

    @RequestMapping("/listByCondition")
    public List<ProductLabelDto> listByCondition(@RequestBody ProductLabelQuery query) {

        List<ProductLabelEntity> list = productLabelMapper.listByCondition(query);

        List<ProductLabelDto> productLabelDtoList = Lists.newArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return productLabelDtoList;
        }

        list.forEach(entity -> {
            ProductLabelDto productLabelDto = new ProductLabelDto();
            BeanHelper.copyProperties(productLabelDto, entity);
            productLabelDtoList.add(productLabelDto);
        });

        return productLabelDtoList;

    }

    @RequestMapping("/listPageByCondition")
    public Pagination<ProductLabelDto> listPageByCondition(@RequestBody ProductLabelQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<ProductLabelEntity> list = productLabelMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(list);

        List<ProductLabelDto> productLabelDtoList = Lists.newArrayList();

        if (CollectionUtils.isEmpty(list)) {
            return new Pagination(query, productLabelDtoList, 0);
        }

        list.forEach(entity -> {
            ProductLabelDto productLabelDto = new ProductLabelDto();
            BeanHelper.copyProperties(productLabelDto, entity);
            productLabelDtoList.add(productLabelDto);
        });

        return new Pagination(query, productLabelDtoList, (int) pageInfo.getTotal());

    }

    @RequestMapping("/insert")
    public ProductLabelDto insert(@RequestBody ProductLabelDto productLabelDto) {

        ProductLabelEntity entity = new ProductLabelEntity();
        entity.setName(productLabelDto.getName());
        productLabelMapper.insertSelective(entity);
        productLabelDto.setId(entity.getId());
        return productLabelDto;
    }

    @RequestMapping("/update")
    public ProductLabelDto update(@RequestBody ProductLabelDto productLabelDto) {

        ProductLabelEntity entity = new ProductLabelEntity();
        entity.setId(productLabelDto.getId());
        entity.setName(productLabelDto.getName());
        productLabelMapper.updateByPrimaryKeySelective(entity);

        return productLabelDto;
    }

    @RequestMapping("/delete")
    public int delete(@RequestParam("id") Long id) {
        return productLabelMapper.deleteById(id);
    }

}
