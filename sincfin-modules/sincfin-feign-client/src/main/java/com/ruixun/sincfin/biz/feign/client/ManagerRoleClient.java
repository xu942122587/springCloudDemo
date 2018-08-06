package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ManagerRoleDto;
import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.query.ManagerRoleQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tiantiea on 2018/5/20.
 */
@FeignClient("${biz.feign.name}")
public interface ManagerRoleClient {

    @RequestMapping("/managerRole/insert")
    ManagerRoleDto insert(@RequestBody ManagerRoleDto managerRoleDto);

    @RequestMapping("/managerRole/update")
    ManagerRoleDto update(@RequestBody ManagerRoleDto managerRoleDto);

    @RequestMapping("/managerRole/updateStatus")
    ManagerRoleDto updateStatus(@RequestBody ManagerRoleDto managerRoleDto);

    @RequestMapping("/managerRole/delete")
    int delete(@RequestParam("id") Long id);

    @RequestMapping("/managerRole/getById")
    ManagerRoleDto getById(@RequestParam("id") Long id);

    @RequestMapping("/managerRole/listPageByCondition")
    Pagination<ManagerRoleDto> listPageByCondition(@RequestBody ManagerRoleQuery query);

    @RequestMapping("/managerRole/listByCondition")
    List<ManagerRoleDto> listByCondition(@RequestBody ManagerRoleQuery query);

}
