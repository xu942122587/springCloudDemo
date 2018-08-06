package com.ruixun.sincfin.common.util;

import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.exception.ParamException;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by t on 2017/1/6.
 */
public abstract class Assert {

	public static void assertNotNull(Object param) {
		Assert.assertNotNull(Arrays.asList(param), ApiStatusEnum.COMMON_PARAM_ERROR.getStatus(),
				ApiStatusEnum.COMMON_PARAM_ERROR.getMsg());
	}

	public static void assertNotNull(Object param, String msg) {
		Assert.assertNotNull(Arrays.asList(param), ApiStatusEnum.COMMON_PARAM_ERROR.getStatus(), msg);
	}

	public static void assertNotNull(Object param, ApiStatusEnum statusEnum) {
		Assert.assertNotNull(Arrays.asList(param), statusEnum.getStatus(), statusEnum.getMsg());
	}

	public static void assertNotEquals(String paramOne, String paramTwo, String msg) {
		if (!StringUtils.equals(paramOne, paramTwo))
			throw new BizException(ApiStatusEnum.COMMON_PARAM_ERROR.getStatus(), msg);
	}

	public static void assertNotEquals(String paramOne, String paramTwo, ApiStatusEnum statusEnum) {
		if (!StringUtils.equals(paramOne, paramTwo))
			throw new BizException(statusEnum.getStatus(), statusEnum.getMsg());
	}

	public static void assertNotNull(Object param, int code, String msg) {
		Assert.assertNotNull(Arrays.asList(param), code, msg);
	}

	public static void assertNotNull(List<Object> paramList) {
		assertNotNull(paramList, ApiStatusEnum.COMMON_PARAM_ERROR.getStatus(),
				ApiStatusEnum.COMMON_PARAM_ERROR.getMsg());
	}

	public static void assertNotNull(List<Object> paramList, String msg) {
		assertNotNull(paramList, ApiStatusEnum.COMMON_PARAM_ERROR.getStatus(), msg);
	}

	public static void notTrue(Boolean express, ApiStatusEnum statusEnum) {
		if (express) {
			throw new BizException(statusEnum.getStatus(), statusEnum.getMsg());
		}
	}

	public static void isTrue(Boolean express, ApiStatusEnum statusEnum) {
		if (!express) {
			throw new BizException(statusEnum.getStatus(), statusEnum.getMsg());
		}
	}

	public static void assertNotNull(List<Object> paramList, int code, String msg) {
		Optional.ofNullable(paramList).ifPresent(list -> {
			list.forEach(item -> {
				if (item instanceof String) {
					String str = (String) item;
					if (StringUtils.isEmpty(str))
						throw new ParamException(code, msg);
				} else if (item instanceof Collection) {
					Collection<?> collections = (Collection<?>) item;
					if (CollectionUtils.isEmpty(collections))
						throw new ParamException(code, msg);
				} else {
					Optional.ofNullable(item).orElseThrow(() -> {
						return new ParamException(code, msg);
					});
				}
			});
		});
	}
}
