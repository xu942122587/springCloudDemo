package com.ruixun.sincfin.biz.module.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.ManagerUserClient;
import com.ruixun.sincfin.biz.module.manager.mapper.ManagerUserMapper;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.entity.ManagerUserEntity;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.ManagerUserQuery;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiantiea on 2018/5/20.
 */
@RestController
@RequestMapping("managerUser")
public class ManagerUserService implements ManagerUserClient {

    private static final String INIT_PASSWORD = "123456";

    @Resource
    private ManagerUserMapper managerUserMapper;

    @RequestMapping("listPageByCondition")
    public Pagination<ManagerUserDto> listPageByCondition(@RequestBody ManagerUserQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<ManagerUserDto> managerUserDtoList = managerUserMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(managerUserDtoList);

        return new Pagination(query, managerUserDtoList, (int) pageInfo.getTotal());


    }

    @RequestMapping("getByUserName")
    public ManagerUserDto getByUserName(@RequestParam String userName) {

        ManagerUserEntity managerUserEntity = managerUserMapper.getByUserName(userName);
        if (managerUserEntity == null) {
            return null;
        }

        ManagerUserDto managerUserDto = new ManagerUserDto();
        BeanHelper.copyProperties(managerUserDto, managerUserEntity);

        return managerUserDto;
    }

    @RequestMapping("insert")
    public ManagerUserDto insert(@RequestBody ManagerUserDto managerUserDto) {

        ManagerUserEntity managerUserEntity = new ManagerUserEntity();
        BeanHelper.copyProperties(managerUserEntity, managerUserDto);
        managerUserEntity.setPassword(DigestUtils.md5Hex(INIT_PASSWORD));
        managerUserMapper.insertSelective(managerUserEntity);
        managerUserDto.setId(managerUserEntity.getId());
        return managerUserDto;

    }

    @RequestMapping("update")
    public ManagerUserDto update(@RequestBody ManagerUserDto managerUserDto) {

        ManagerUserEntity managerUserEntity = new ManagerUserEntity();
        BeanHelper.copyProperties(managerUserEntity, managerUserDto);
        managerUserMapper.updateByPrimaryKeySelective(managerUserEntity);

        return managerUserDto;

    }

    @RequestMapping("updateStatus")
    public ManagerUserDto updateStatus(@RequestBody ManagerUserDto managerUserDto) {

        ManagerUserEntity managerUserEntity = new ManagerUserEntity();
        managerUserEntity.setId(managerUserDto.getId());
        managerUserEntity.setStatus(managerUserDto.getStatus());
        managerUserMapper.updateByPrimaryKeySelective(managerUserEntity);

        return managerUserDto;

    }

    @RequestMapping("delete")
    public int delete(@RequestParam("id") Long id) {
        return managerUserMapper.deleteByPrimaryKey(id);

    }

    @RequestMapping("initPassword")
    public int initPassword(@RequestParam("id") Long id) {
        ManagerUserEntity managerUserEntity = new ManagerUserEntity();
        managerUserEntity.setId(id);
        managerUserEntity.setPassword(DigestUtils.md5Hex(INIT_PASSWORD));
        return managerUserMapper.updateByPrimaryKeySelective(managerUserEntity);
    }

    @RequestMapping("changePassword")
    public int changePassword(@RequestParam("id") Long id,
                              @RequestParam("newPassword") String newPassword,
                              @RequestParam("oldPassword") String oldPassword) {
        ManagerUserEntity managerUserEntity = managerUserMapper.selectByPrimaryKey(id);
        if (managerUserEntity == null)
            throw new BizException(ApiStatusEnum.MANAGER_USER_ID_NOT_EXIST);
        if (!managerUserEntity.getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
            throw new BizException(ApiStatusEnum.MANAGER_PASSWORD_ERROR);
        }
        managerUserEntity = new ManagerUserEntity();
        managerUserEntity.setId(id);
        managerUserEntity.setPassword(DigestUtils.md5Hex(newPassword));
        return managerUserMapper.updateByPrimaryKeySelective(managerUserEntity);
    }


}
