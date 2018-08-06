package com.ruixun.sincfin.common.util;

import com.google.common.collect.Lists;
import com.ruixun.sincfin.common.exception.SystemException;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by t on 2017/1/9.
 */
public class BeanHelper {

    public static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    public static void copyProperties(Object desc, Object src) {
        try {
            BeanUtils.copyProperties(desc, src);
        } catch (IllegalAccessException e) {
            throw new SystemException("copy异常!");
        } catch (InvocationTargetException e) {
            throw new SystemException("copy异常!");
        }
    }

}
