package com.ruixun.sincfin.biz.module.contract.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.ContractClient;
import com.ruixun.sincfin.biz.module.contract.mapper.ContractMapper;
import com.ruixun.sincfin.biz.module.contract.mapper.ContractTemplateMapper;
import com.ruixun.sincfin.biz.module.file.service.FileObjectService;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserMapper;
import com.ruixun.sincfin.biz.module.product.mapper.ProductMapper;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ContractDto;
import com.ruixun.sincfin.domain.dto.FileObjectDto;
import com.ruixun.sincfin.domain.dto.FinancingUserDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.entity.*;
import com.ruixun.sincfin.domain.enums.ContractStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.ContractQuery;
import com.ruixun.sincfin.domain.query.FinancingUserQuery;
import com.ruixun.sincfin.domain.query.ProductQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/5/24.
 */
@RestController
@RequestMapping("contract")
public class ContractService implements ContractClient {

    @Resource
    private ContractMapper contractMapper;
    @Resource
    private ContractTemplateMapper contractTemplateMapper;
    @Resource
    private FinancingUserMapper financingUserMapper;
    @Resource
    private ProductMapper productMapper;

    @Resource
    private FileObjectService fileObjectService;

    @RequestMapping("insertGenerateProduct")
    @Transactional
    public ProductDto insertGenerateProduct(Long contractId, Long amount) {

        ContractEntity contractEntity = contractMapper.selectByPrimaryKey(contractId);
        if (contractEntity == null) {
            throw new BizException(ApiStatusEnum.CONTRACT_ID_NOT_EXIST);
        }
        if (ContractStatusEnum.WAIT_EDIT.getCode().equals(contractEntity.getStatus())) {
            throw new BizException(ApiStatusEnum.CONTRACT_STATUS_CANNOT_GENERATE_PRODUCT);
        }
        if (contractEntity.getUsableBalance() == null
                || contractEntity.getUsableBalance() < amount ) {
            throw new BizException(ApiStatusEnum.CONTRACT_USABLE_BALANCE_NOT_SUFFICIENT);
        }
        contractMapper.updateUsableBalance(contractId, - amount);

        contractMapper.updateProductCount(contractId, 1);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setContractId(contractId);
        productEntity.setRepaymentType(contractEntity.getRepaymentType());
        productEntity.setAmount(amount);
        productEntity.setUnsoldAmount(amount);
        productEntity.setFinancingUserId(contractEntity.getFinancingUserId());

        productEntity.setTitle(contractEntity.getTitle() + "-" + (contractEntity.getProductCount() + 1));
        productMapper.insertSelective(productEntity);

        ProductDto productDto = new ProductDto();
        BeanHelper.copyProperties(productDto, productEntity);

        return productDto;
    }

    @RequestMapping("update")
    public ContractDto update(@RequestBody ContractDto contractDto) {

        contractDto.setStatus(ContractStatusEnum.EDITED.getCode());
        ContractEntity contractEntity = new ContractEntity();
        BeanHelper.copyProperties(contractEntity, contractDto);
        contractMapper.updateByPrimaryKeySelective(contractEntity);

        return contractDto;
    }

    @RequestMapping("insert")
    @Transactional
    public ContractDto insert(@RequestBody ContractDto contractDto) {
        ContractTemplateEntity contractTemplateEntity
                = contractTemplateMapper.selectByPrimaryKey(contractDto.getContractTemplateId());
        contractDto.setTitle(contractTemplateEntity.getName()
                + DateFormatUtils.format(new Date(), "yyyyMMddHHmm"));

        contractDto.setStatus(ContractStatusEnum.WAIT_EDIT.getCode());
        ContractEntity contractEntity = new ContractEntity();
        BeanHelper.copyProperties(contractEntity, contractDto);
        contractMapper.insertSelective(contractEntity);
        contractDto.setId(contractEntity.getId());
        return contractDto;
    }

    @RequestMapping("getById")
    public ContractDto getById(Long contractId) {
        ContractEntity contractEntity = contractMapper.selectByPrimaryKey(contractId);
        ContractDto contractDto = new ContractDto();
        if (contractEntity == null) {
            return null;
        }
        BeanHelper.copyProperties(contractDto, contractEntity);

        ContractTemplateEntity contractTemplateEntity
                = contractTemplateMapper.selectByPrimaryKey(contractEntity.getContractTemplateId());
        if (contractTemplateEntity == null) {
            return contractDto;
        }
        contractDto.setContractTemplateName(contractTemplateEntity.getName());
        FinancingUserEntity financingUserEntity
                = financingUserMapper.selectByPrimaryKey(contractEntity.getFinancingUserId());
        if (financingUserEntity == null) {
            return contractDto;
        }
        FinancingUserDto financingUserDto = new FinancingUserDto();
        BeanHelper.copyProperties(financingUserDto, financingUserEntity);
        contractDto.setFinancingUser(financingUserDto);

        // file
        String serviceAgreement = contractDto.getServiceAgreement();
        if (StringUtils.isNotEmpty(serviceAgreement)) {
            List<String> fileKeyList = Arrays.asList(serviceAgreement.split(","))
                    .stream().collect(Collectors.toList());
            Map<String, FileObjectDto> fileObjectDtoMap = fileObjectService.getPublicFileList(fileKeyList);
            List<FileObjectDto> fileObjectDtoList = Lists.newArrayListWithExpectedSize(fileKeyList.size());

            fileKeyList.forEach( fileKey -> {
                FileObjectDto fileObjectDto = fileObjectDtoMap.get(fileKey);

                if (fileObjectDto != null) {
                    fileObjectDtoList.add(fileObjectDto);
                }
            });
            if (CollectionUtils.isNotEmpty(fileObjectDtoList)) {
                contractDto.setServiceAgreementFileList(fileObjectDtoList);
            }

        }

        return contractDto;
    }

    @RequestMapping("listPageByCondition")
    public Pagination<ContractDto> listPageByCondition(@RequestBody ContractQuery query) {

        if (StringUtils.isNotEmpty(query.getFinancingUserRealName())) {
            FinancingUserEntity financingUserEntity
                    = financingUserMapper.getByRealName(query.getFinancingUserRealName());
            if (financingUserEntity != null) { query.setFinancingUserId(financingUserEntity.getId()); }
        }

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<ContractDto> contractDtoList = contractMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(contractDtoList);

//        List<ContractDto> contractDtoList = Lists.newArrayList();
//
//        if (CollectionUtils.isEmpty(contractEntityList)) {
//            return new Pagination(query, contractDtoList, 0);
//        }
//
//        // merger financing user
//        List<Long> financingUserIdList = contractEntityList.stream()
//                .map(ContractEntity::getFinancingUserId).collect(Collectors.toList());
//        FinancingUserQuery financingUserQuery = new FinancingUserQuery();
//        financingUserQuery.setIdList(financingUserIdList);
//        List<FinancingUserEntity> financingUserEntityList
//                = financingUserMapper.listByCondition(financingUserQuery);
//        Map<Long, FinancingUserEntity> financingUserEntityMap
//                = financingUserEntityList.stream().collect(
//                        Collectors.toMap(FinancingUserEntity::getId, Function.identity()));
//
//        contractEntityList.forEach( entity -> {
//            ContractDto contractDto = new ContractDto();
//            BeanHelper.copyProperties(contractDto, entity);
//            financingUserIdList.add(contractDto.getFinancingUserId());
//            contractDtoList.add(contractDto);
//            FinancingUserEntity financingUserEntity =
//                    financingUserEntityMap.get(entity.getFinancingUserId());
//            if (financingUserEntity != null) {
//                FinancingUserDto financingUserDto = new FinancingUserDto();
//                BeanHelper.copyProperties(financingUserDto, financingUserEntity);
//                contractDto.setFinancingUser(financingUserDto);
//            }
//        });

        return new Pagination(query, contractDtoList, (int) pageInfo.getTotal());
    }

    @RequestMapping("delete")
    public int delete(Long contractId) {
        ProductQuery productQuery = new ProductQuery();
        productQuery.setContractId(contractId);
        int count = productMapper.countByCondition(productQuery);
        if (count > 0) {
            return 0;
        }
        return contractMapper.deleteById(contractId);
    }


}
