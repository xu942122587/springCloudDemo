package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.entity.BankEntity;
import com.ruixun.sincfin.domain.query.BankQuery;

import java.util.List;

public interface BankMapper {
    int insert(BankEntity record);

    int insertSelective(BankEntity record);

    BankEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankEntity record);

    int updateByPrimaryKey(BankEntity record);

    List<BankDto> listByCondition(BankQuery query);

    /**
     * 根据银行卡名称，获取银行卡信息
     * @param bankBame
     * @return
     */
	List<BankEntity> getBankByBankName(String bankBame);

	BankEntity getBankByCode(String bankCode);

}