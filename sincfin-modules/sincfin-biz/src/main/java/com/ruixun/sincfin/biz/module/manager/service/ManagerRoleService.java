package com.ruixun.sincfin.biz.module.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.ManagerRoleClient;
import com.ruixun.sincfin.biz.module.manager.mapper.ManagerRoleMapper;
import com.ruixun.sincfin.biz.module.manager.mapper.ManagerUserMapper;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ManagerRoleDto;
import com.ruixun.sincfin.domain.entity.ManagerRoleEntity;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.ManagerRoleQuery;
import com.ruixun.sincfin.domain.query.ManagerUserQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiantiea on 2018/5/25.
 */
@RestController
@RequestMapping("managerRole")
public class ManagerRoleService implements ManagerRoleClient {

    @Resource
    private ManagerRoleMapper managerRoleMapper;

    @Resource
    private ManagerUserMapper managerUserMapper;

    @RequestMapping("insert")
    public ManagerRoleDto insert(@RequestBody ManagerRoleDto managerRoleDto) {

        ManagerRoleEntity managerRoleEntity = new ManagerRoleEntity();
        BeanHelper.copyProperties(managerRoleEntity, managerRoleDto);
        managerRoleMapper.insertSelective(managerRoleEntity);

        return managerRoleDto;
    }

    @RequestMapping("update")
    public ManagerRoleDto update(@RequestBody ManagerRoleDto managerRoleDto) {

        ManagerRoleEntity managerRoleEntity = new ManagerRoleEntity();
        managerRoleEntity.setId(managerRoleDto.getId());
        managerRoleEntity.setName(managerRoleDto.getName());
        managerRoleMapper.updateByPrimaryKeySelective(managerRoleEntity);

        return managerRoleDto;
    }

    @RequestMapping("updateStatus")
    public ManagerRoleDto updateStatus(@RequestBody ManagerRoleDto managerRoleDto) {

        ManagerRoleEntity managerRoleEntity = new ManagerRoleEntity();
        managerRoleEntity.setId(managerRoleDto.getId());
        managerRoleEntity.setStatus(managerRoleDto.getStatus());
        managerRoleMapper.updateByPrimaryKeySelective(managerRoleEntity);
        return managerRoleDto;
    }

    @RequestMapping("delete")
    public int delete(@RequestParam("id") Long id) {
        ManagerUserQuery managerUserQuery = new ManagerUserQuery();
        managerUserQuery.setRoleId(id);
        int count = managerUserMapper.countByCondition(managerUserQuery);
        if (count > 0) {
            throw new BizException(ApiStatusEnum.MANAGER_ROLE_EXIST_USER_NOT_DELETE);
        }

        return managerRoleMapper.deleteById(id);
    }

    @RequestMapping("getById")
    public ManagerRoleDto getById(@RequestParam("id") Long id) {
        ManagerRoleDto managerRoleDto = new ManagerRoleDto();
        ManagerRoleEntity entity = managerRoleMapper.selectByPrimaryKey(id);
        if (entity == null) {
            return null;
        }
        BeanHelper.copyProperties(managerRoleDto, entity);
        return managerRoleDto;
    }

    @RequestMapping("listPageByCondition")
    public Pagination<ManagerRoleDto> listPageByCondition(@RequestBody ManagerRoleQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<ManagerRoleEntity> managerRoleEntityList = managerRoleMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(managerRoleEntityList);

        List<ManagerRoleDto> managerRoleDtoList = Lists.newArrayList();

        if (CollectionUtils.isEmpty(managerRoleEntityList)) {
            return new Pagination(query, managerRoleDtoList, 0);
        }

        managerRoleEntityList.forEach( entity -> {
            ManagerRoleDto managerRoleDto = new ManagerRoleDto();
            BeanHelper.copyProperties(managerRoleDto, entity);
            managerRoleDtoList.add(managerRoleDto);
        });

        return new Pagination(query, managerRoleDtoList, (int) pageInfo.getTotal());

    }

    @RequestMapping("listByCondition")
    public List<ManagerRoleDto> listByCondition(@RequestBody ManagerRoleQuery query) {

        List<ManagerRoleEntity> managerRoleEntityList = managerRoleMapper.listByCondition(query);

        List<ManagerRoleDto> managerRoleDtoList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(managerRoleEntityList)) {
            return managerRoleDtoList;
        }

        managerRoleEntityList.forEach( entity -> {
            ManagerRoleDto managerRoleDto = new ManagerRoleDto();
            BeanHelper.copyProperties(managerRoleDto, entity);
            managerRoleDtoList.add(managerRoleDto);
        });

        return managerRoleDtoList;


    }


}
