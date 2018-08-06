package com.ruixun.sincfin.biz.module.user.mapper;

import com.ruixun.sincfin.domain.dto.UserBankChangeDto;
import com.ruixun.sincfin.domain.entity.UserBankChangeEntity;
import com.ruixun.sincfin.domain.query.UserBankChangeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBankChangeMapper {
    int insert(UserBankChangeEntity record);

    int insertSelective(UserBankChangeEntity record);

    UserBankChangeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBankChangeEntity record);

    int updateByPrimaryKey(UserBankChangeEntity record);

    List<UserBankChangeDto> listByCondition(UserBankChangeQuery query);

    /**
     * 审核通过
     * @param id
     * @param auditorId
     * @return
     */
    int updateAuditPass(@Param("id") Long id, @Param("auditorId") Long auditorId);

    /**
     * 审核拒绝
     * @param id
     * @param auditorId
     * @return
     */
    int updateAuditReject(@Param("id") Long id, @Param("auditorId") Long auditorId,
                          @Param("remark") String remark);
}