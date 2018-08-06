package com.ruixun.sincfin.biz.module.finuser.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.FinancingUserClient;
import com.ruixun.sincfin.biz.module.file.service.FileObjectService;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserMapper;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserRemittanceMapper;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserRepaymentMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.entity.FinancingUserEntity;
import com.ruixun.sincfin.domain.entity.FinancingUserRemittanceEntity;
import com.ruixun.sincfin.domain.entity.FinancingUserRepaymentEntity;
import com.ruixun.sincfin.domain.query.FinancingUserQuery;

/**
 * @author t
 * @date 2018/5/22 14:25
 */
@RestController
@RequestMapping("financingUser")
public class FinancingUserService implements FinancingUserClient {

	@Resource
	private FinancingUserMapper financingUserMapper;
	@Resource
	private FinancingUserRemittanceMapper financingUserRemittanceMapper;
	@Resource
	private FinancingUserRepaymentMapper financingUserRepaymentMapper;
	@Resource
	private FileObjectService fileObjectService;

	@RequestMapping("listPageByCondition")
	public Pagination<FinancingUserDto> listPageByCondition(@RequestBody FinancingUserQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<FinancingUserEntity> financingUserList = financingUserMapper.listByCondition(query);
		PageInfo pageInfo = new PageInfo(financingUserList);

		List<FinancingUserDto> financingUserDtoList = Lists.newArrayList();

		if (CollectionUtils.isEmpty(financingUserList)) {
			return new Pagination(query, financingUserDtoList, 0);
		}

		financingUserList.forEach(entity -> {
			FinancingUserDto financingUserDto = new FinancingUserDto();
			BeanHelper.copyProperties(financingUserDto, entity);
			financingUserDtoList.add(financingUserDto);
		});

		return new Pagination(query, financingUserDtoList, (int) pageInfo.getTotal());
	}

	@RequestMapping("listByCondition")
	public List<FinancingUserDto> listByCondition(@RequestBody FinancingUserQuery query) {

		List<FinancingUserEntity> entityList = financingUserMapper.listByCondition(query);

		if (CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}

		List<FinancingUserDto> financingUserDtoList = Lists.newArrayList();

		entityList.forEach(entity -> {
			FinancingUserDto dto = new FinancingUserDto();
			BeanHelper.copyProperties(dto, entity);
			financingUserDtoList.add(dto);
		});

		return financingUserDtoList;
	}

	@RequestMapping("getById")
	public FinancingUserDto getById(@RequestParam Long id) {

		FinancingUserEntity financingUserEntity = financingUserMapper.selectByPrimaryKey(id);
		if (financingUserEntity == null) {
			return null;
		}
		FinancingUserDto financingUserDto = new FinancingUserDto();
		BeanHelper.copyProperties(financingUserDto, financingUserEntity);

		financingUserDto.setBusinessLicenseImageUrl(
				fileObjectService.getPublicFileUrl(financingUserDto.getBusinessLicenseImage()));
		financingUserDto
				.setIdCardFaceImageUrl(fileObjectService.getPublicFileUrl(financingUserDto.getIdCardFaceImage()));
		financingUserDto
				.setIdCardBackImageUrl(fileObjectService.getPublicFileUrl(financingUserDto.getIdCardBackImage()));

		return financingUserDto;
	}

	@RequestMapping("insert")
	public FinancingUserDto insert(@RequestBody FinancingUserDto financingUserDto) {

		FinancingUserEntity financingUserEntity = new FinancingUserEntity();

		BeanHelper.copyProperties(financingUserEntity, financingUserDto);

		financingUserMapper.insertSelective(financingUserEntity);
		financingUserDto.setId(financingUserEntity.getId());
		return financingUserDto;

	}

	@RequestMapping("update")
	public FinancingUserDto update(@RequestBody FinancingUserDto financingUserDto) {

		FinancingUserEntity financingUserEntity = new FinancingUserEntity();
		BeanHelper.copyProperties(financingUserEntity, financingUserDto);

		financingUserMapper.updateByPrimaryKeySelective(financingUserEntity);
		return financingUserDto;

	}

	/**
	 * 产品售罄（满标）操作
	 * 
	 * @return
	 */
	@Override
	@RequestMapping("updateProductSellOut")
	public int updateProductSellOut(@RequestBody FinancingUserRemittanceDto financingUserRemittanceDto) {

		FinancingUserRemittanceEntity remittanceEntity = new FinancingUserRemittanceEntity();
		BeanHelper.copyProperties(remittanceEntity, financingUserRemittanceDto);

		financingUserRemittanceMapper.insertSelective(remittanceEntity);
		long financingUserId = financingUserRemittanceDto.getFinancingUserId();
		long amount = financingUserRemittanceDto.getExpectedAmount();

		financingUserMapper.updateTotalFinancingAmount(financingUserId, amount);
		return financingUserMapper.updateTotalUnpaidPrincipal(financingUserId, amount);
	}

	/**
	 * 产品还款操作
	 * 
	 * @return
	 */
	@Override
	@RequestMapping("updateProductRepayment")
	public int updateProductRepayment(@RequestBody FinancingUserRepaymentDto financingUserRepaymentDto) {

		FinancingUserRepaymentEntity repaymentEntity = new FinancingUserRepaymentEntity();
		BeanHelper.copyProperties(repaymentEntity, financingUserRepaymentDto);

		financingUserRepaymentMapper.insertSelective(repaymentEntity);
		long financingUserId = financingUserRepaymentDto.getFinancingUserId();
		long principal = financingUserRepaymentDto.getPrincipal();

		return financingUserMapper.updateTotalUnpaidPrincipal(financingUserId, -principal);
	}

	/**
	 * 融资客户还款操作
	 * 
	 * @return
	 */
	@Override
	@RequestMapping("updateFinancingRepaymentStatus")
	public int updateFinancingRepayment(@RequestBody FinancingUserRepaymentDto financingUserRepaymentDto) {

		FinancingUserRepaymentEntity repaymentEntity = new FinancingUserRepaymentEntity();
		BeanHelper.copyProperties(repaymentEntity, financingUserRepaymentDto);
		return financingUserRepaymentMapper.updateFinancingRepaymentStatus(repaymentEntity);
	}

	@Override
	public int updateFinancingRemittance(FinancingUserRemittanceDto financingUserRemittanceDto) {
		FinancingUserRemittanceEntity repaymentEntity = new FinancingUserRemittanceEntity();
		BeanHelper.copyProperties(repaymentEntity, financingUserRemittanceDto);
		return financingUserRemittanceMapper.updateFinancingRemittanceStatus(repaymentEntity);
	}

}
