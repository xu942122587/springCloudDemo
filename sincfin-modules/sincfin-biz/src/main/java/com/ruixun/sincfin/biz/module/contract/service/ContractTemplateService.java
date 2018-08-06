package com.ruixun.sincfin.biz.module.contract.service;

import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.ContractTemplateClient;
import com.ruixun.sincfin.biz.module.contract.mapper.ContractTemplateMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.dto.ContractTemplateDto;
import com.ruixun.sincfin.domain.entity.ContractTemplateEntity;
import com.ruixun.sincfin.domain.query.ContractTemplateQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiantiea on 2018/5/25.
 */
@RestController
@RequestMapping("contractTemplate")
public class ContractTemplateService implements ContractTemplateClient {

    @Resource
    private ContractTemplateMapper contractTemplateMapper;

    @RequestMapping("listByCondition")
    public List<ContractTemplateDto> listByCondition(ContractTemplateQuery query) {
        List<ContractTemplateEntity> entityList = contractTemplateMapper.listByCondition(query);
        if (CollectionUtils.isEmpty(entityList)) {
            return Lists.newArrayList();
        }
        List<ContractTemplateDto> dtoList
                = Lists.newArrayListWithCapacity(entityList.size());
        entityList.forEach( entity -> {
            ContractTemplateDto dto = new ContractTemplateDto();
            BeanHelper.copyProperties(dto, entity);
            dtoList.add(dto);
        });
        return dtoList;
    }


}
