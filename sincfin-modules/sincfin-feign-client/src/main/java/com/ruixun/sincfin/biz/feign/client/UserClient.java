package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.OrderQuery;
import com.ruixun.sincfin.domain.query.UserQuery;

/**
 * Created by tiantiea on 2018/5/20.
 */
@FeignClient("${biz.feign.name}")
public interface UserClient {

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public UserDto add(@RequestBody UserDto userDto);

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public void register(@RequestBody UserDto userDto);

    @RequestMapping(value = "/user/getById", method = RequestMethod.GET)
    public UserDto getById(@RequestParam("id") Long id);

    @RequestMapping(value = "/user/getByMobile", method = RequestMethod.GET)
    public UserDto getByMobile(@RequestParam("mobile") String mobile);

    @RequestMapping(value = "/user/listManagerPage", method = RequestMethod.POST)
    public Pagination<UserDto> listManagerPage(@RequestBody UserQuery query);

    @RequestMapping(value = "/user/insertGhost", method = RequestMethod.POST)
    public UserDto insertGhost(UserDto userDto);
    
    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
	public int updatePassword(@RequestParam("userId") Long userId, @RequestParam("password") String password);

    @RequestMapping(value = "/user/updateMobile", method = RequestMethod.POST)
    int updateMobile(@RequestParam("userId") Long userId, @RequestParam("mobile") String mobile);

    @RequestMapping(value = "/user/updateStatus")

    public int updateStatus(@RequestParam("userId") Long userId, @RequestParam("status") String status);


    @RequestMapping(value = "/user/bindBankCode", method = RequestMethod.POST)
    public ApiResponse bindBankCode(@RequestParam("userId") Long userId, @RequestParam("realName") String realName, @RequestParam("idCardNo") String idCardNo, @RequestParam("bankCardNo") String bankCardNo, @RequestParam("mobile") String mobile,@RequestParam("isBind")Boolean isBind);


    @RequestMapping(value = "/user/investment/record", method = RequestMethod.POST)
    public Pagination<OrderDto> investmentRecord(@RequestBody OrderQuery query);
    
    @RequestMapping(value = "/user/update/riskTest", method = RequestMethod.POST)
    public int updateRiskTest(@RequestParam("userId") Long userId,@RequestParam("riskType") Integer riskType);

    @RequestMapping(value = "/user/isBindBankCode", method = RequestMethod.GET)
	public boolean isBindBankCode(@RequestParam("userId") Long userId);

    /**
     * 判断用户是否是新手
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/isNewUser", method = RequestMethod.GET)
	public boolean isNewUser(@RequestParam("userId") Long userId);
    
    /**
     * 查询邀友记录
     * @param query
     * @return
     */

	@RequestMapping(value = "/user/invitationRecord", method = RequestMethod.POST)
	public Pagination<InvitationRecordDto> invitationRecord(@RequestBody UserQuery query);


    @RequestMapping("/user/firstInvestmentFlagTask")
    int firstInvestmentFlagTask();
	
}

