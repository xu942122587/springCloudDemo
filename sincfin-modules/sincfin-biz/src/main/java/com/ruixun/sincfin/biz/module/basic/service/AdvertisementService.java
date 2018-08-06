package com.ruixun.sincfin.biz.module.basic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.AdvertisementClient;
import com.ruixun.sincfin.biz.module.basic.mapper.AdvertisementMapper;
import com.ruixun.sincfin.biz.module.file.service.FileObjectService;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.dto.ProductTypeDto;
import com.ruixun.sincfin.domain.entity.AdvertisementEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.enums.AdvertisementPackageTypeEnum;
import com.ruixun.sincfin.domain.enums.AdvertisementPositionEnum;
import com.ruixun.sincfin.domain.enums.AdvertisementStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.AdvertisementQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/6/5.
 */
@RestController
@RequestMapping("advertisement")
public class AdvertisementService implements AdvertisementClient {
    
    @Resource
    private AdvertisementMapper advertisementMapper;
    @Resource
    private FileObjectService fileObjectService;

    @RequestMapping("insert")
    public AdvertisementDto insert(@RequestBody AdvertisementDto advertisementDto) {
        AdvertisementEntity advertisementEntity = new AdvertisementEntity();
        BeanHelper.copyProperties(advertisementEntity, advertisementDto);
        advertisementMapper.insertSelective(advertisementEntity);
        advertisementDto.setId(advertisementEntity.getId());
        
        return advertisementDto;
    }


    @RequestMapping("update")
    public AdvertisementDto update(@RequestBody AdvertisementDto advertisementDto) {

//        AdvertisementEntity advertisementEntity
//                = advertisementMapper.selectByPrimaryKey(advertisementDto.getId());
//        String updateStatus = advertisementDto.getStatus();
//        String position = advertisementEntity.getPosition();
//        if (AdvertisementStatusEnum.SHELVE.getCode().equals(updateStatus)) {
//            if (AdvertisementPositionEnum.ADVERTISEMENT.getCode().equals(position)
//                    || AdvertisementPositionEnum.DIALOG.getCode().equals(position)) {
//                advertisementMapper.updateUnshelve(position);
//            } else if (AdvertisementPositionEnum.NAVIGATION.getCode().equals(position)) {
//                int count = advertisementMapper.countByPosition(position);
//                // 导航 最多五个上架
//                if (count > 5) {
//                    throw new BizException(ApiStatusEnum.ADVERTISEMENT_NAVIGATION_MAX);
//                }
//            }
//        }
        AdvertisementEntity updateAdvertisementEntity = new AdvertisementEntity();
        BeanHelper.copyProperties(updateAdvertisementEntity, advertisementDto);
        advertisementMapper.updateByPrimaryKeySelective(updateAdvertisementEntity);
        return advertisementDto;
    }

    @RequestMapping("getById")
    public AdvertisementDto getById(@RequestParam("id") Long id) {

        AdvertisementDto advertisementDto = new AdvertisementDto();
        AdvertisementEntity advertisementEntity = advertisementMapper.selectByPrimaryKey(id);
        BeanHelper.copyProperties(advertisementDto, advertisementEntity);
        advertisementDto.setImageUrl(fileObjectService.getPublicFileUrl(advertisementDto.getImage()));
        return advertisementDto;

    }

    @RequestMapping("listPageByCondition")
    public Pagination<AdvertisementDto> listPageByCondition(@RequestBody AdvertisementQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<AdvertisementDto> dtoList = advertisementMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(dtoList);

        mergeAdvertisementImage(dtoList);
        mergePackageType(dtoList);

        return new Pagination(query, dtoList, (int) pageInfo.getTotal());
    }
    
    
    @RequestMapping("listByPosition")
	public List<AdvertisementDto> listByPosition(@RequestParam("position")String position,@RequestParam("packageType")String packageType){
    	List<AdvertisementDto> dtoList = advertisementMapper.listByPosition(position,packageType);
        mergeAdvertisementImage(dtoList);
    	return dtoList;
    }

    private void mergeAdvertisementImage(List<AdvertisementDto> dtoList) {
        List<String> imageList = dtoList.stream().map(AdvertisementDto::getImage).collect(Collectors.toList());

        Map<String, String> imageUrlMap = fileObjectService.getPublicFileUrlList(imageList);
        dtoList.forEach(dto -> {
            String image = dto.getImage();
            if (StringUtils.isNotEmpty(image)) {
                dto.setImageUrl(imageUrlMap.get(image));
            }
        });
    }

    private void mergePackageType(List<AdvertisementDto> dtoList) {
	    for (AdvertisementDto dto : dtoList) {
	        String packageType = dto.getPackageType();
	        if (StringUtils.isEmpty(packageType)) {
	            continue;
            }
            List<String> packageTypeList =
                    Arrays.asList(packageType.split(",")).stream()
                            .map(s -> s.trim()).collect(Collectors.toList());
            StringBuffer tmpPackageTypeDesc = new StringBuffer("");
            for (String tmpPackageType : packageTypeList) {
                AdvertisementPackageTypeEnum tmpEnum = AdvertisementPackageTypeEnum.fromCode(tmpPackageType);
                if (tmpEnum == null) {
                    continue;
                }
                tmpPackageTypeDesc.append(tmpEnum.getText()).append(",");
            }
            if (StringUtils.isNotEmpty(tmpPackageTypeDesc)) {
                dto.setPackageTypeDesc(StringUtils.substring(tmpPackageTypeDesc.toString(),
                        0, tmpPackageTypeDesc.length() - 1));
            }
        }
    }


}
