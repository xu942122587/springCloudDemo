package com.ruixun.sincfin.biz.module.basic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.BankClient;
import com.ruixun.sincfin.biz.module.basic.mapper.BankMapper;
import com.ruixun.sincfin.biz.module.file.service.FileObjectService;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.entity.BankEntity;
import com.ruixun.sincfin.domain.query.BankQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("bank")
public class BankService implements BankClient {

    @Resource
    private BankMapper bankMapper;

    @RequestMapping("insert")
    public BankDto insert(@RequestBody BankDto bankDto) {
        BankEntity bankEntity = new BankEntity();
        BeanHelper.copyProperties(bankEntity, bankDto);
        bankMapper.insertSelective(bankEntity);
        bankDto.setId(bankEntity.getId());
        return bankDto;
    }


    @RequestMapping("update")
    public BankDto update(@RequestBody BankDto bankDto) {
        BankEntity bankEntity = new BankEntity();
        BeanHelper.copyProperties(bankEntity, bankDto);
        bankMapper.updateByPrimaryKeySelective(bankEntity);
        return bankDto;
    }

    @RequestMapping("getById")
    public BankDto getById(@RequestParam("id") Long id) {

        BankDto bankDto = new BankDto();
        BankEntity bankEntity = bankMapper.selectByPrimaryKey(id);
        BeanHelper.copyProperties(bankDto, bankEntity);
        return bankDto;

    }

    @RequestMapping("listPageByCondition")
    public Pagination<BankDto> listPageByCondition(@RequestBody BankQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<BankDto> dtoList = bankMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(dtoList);

        return new Pagination(query, dtoList, (int) pageInfo.getTotal());
    }

}
