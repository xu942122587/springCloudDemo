package com.ruixun.sincfin.biz.module.basic.service;

import com.ruixun.sincfin.biz.feign.client.ChannelClient;
import com.ruixun.sincfin.biz.module.basic.mapper.ChannelMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.dto.ChannelDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.ChannelEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("channel")
public class ChannelService implements ChannelClient {

    @Resource
    private ChannelMapper channelMapper;

    @RequestMapping("list")
    public List<ChannelDto> listByCondition() {
        return channelMapper.listByCondition();
    }
    
    @RequestMapping("getById")
    public ChannelDto getById(Long id){
    	ChannelEntity channelEntity = channelMapper.selectByPrimaryKey(id);
        if (channelEntity == null) {
            return null;
        }
        ChannelDto channelDto = new ChannelDto();
        BeanHelper.copyProperties(channelDto, channelEntity);

        return channelDto;
    }
}
