package com.ruixun.sincfin.mobile.api.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by tiea on 2018/5/28.
 */
@Configuration
public class CustomDateEditorConfiguration {

    /**
     * 对前台日期传 时间戳 反序列化
     * @return
     */
    @Autowired
    public void setWebBindingInitializer(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        requestMappingHandlerAdapter.setWebBindingInitializer(new WebBindingInitializer() {
            @Override
            public void initBinder(WebDataBinder webDataBinder) {

                webDataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport () {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        if (StringUtils.isEmpty(text)) {
                            setValue(null);
                        } else {
                            setValue(new Date(Long.decode(text)));
                        }
                    }

                    @Override
                    public String getAsText() {
                        Date value = (Date) getValue();
                        return (value != null ? String.valueOf(TimeUnit.MILLISECONDS.toSeconds(value.getTime())) : "");
                    }
                });
            }
        });
    }

}
