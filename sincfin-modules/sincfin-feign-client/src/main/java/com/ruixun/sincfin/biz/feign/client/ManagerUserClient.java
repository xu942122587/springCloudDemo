package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.query.ManagerUserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiantiea on 2018/5/20.
 */
@FeignClient("${biz.feign.name}")
public interface ManagerUserClient {

    @RequestMapping("/managerUser/getByUserName")
    ManagerUserDto getByUserName(@RequestParam("userName") String userName);

    @RequestMapping("/managerUser/listPageByCondition")
    Pagination<ManagerUserDto> listPageByCondition(@RequestBody ManagerUserQuery query);

    @RequestMapping("/managerUser/insert")
    ManagerUserDto insert(@RequestBody ManagerUserDto managerUserDto);

    @RequestMapping("/managerUser/update")
    ManagerUserDto update(@RequestBody ManagerUserDto managerUserDto);

    @RequestMapping("/managerUser/updateStatus")
    ManagerUserDto updateStatus(@RequestBody ManagerUserDto managerUserDto);

    @RequestMapping("/managerUser/delete")
    int delete(@RequestParam("id") Long id);

    @RequestMapping("/managerUser/initPassword")
    int initPassword(@RequestParam("id") Long id);


    @RequestMapping("/managerUser/changePassword")
    int changePassword(@RequestParam("id") Long id,
                        @RequestParam("newPassword") String newPassword,
                        @RequestParam("oldPassword") String oldPassword);

}
