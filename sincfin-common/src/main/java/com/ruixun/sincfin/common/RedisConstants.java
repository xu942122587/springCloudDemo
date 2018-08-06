package com.ruixun.sincfin.common;

public class RedisConstants {

	public static final String MANAGER_USER_TOKEN_PREFIX = "manager:user:token:";
	// 单位 s
	public static final int MANAGER_USER_TOKEN_EXPIRE_TIME = 30;

	public static final String MANAGER_USER_PERMISSION_PREFIX = "manager:user:permission:";

	public static final int MANAGER_USER_PERMISSION_EXPIRE_TIME = 60 * 60 * 24 * 7;

	public static final String USER_TOKEN_PREFIX = "user:token:";
	// 单位 s
	public static final int USER_TOKEN_EXPIRE_TIME = 60 * 60 * 24;

	public static final String SMSCODE_PREFIX = "smsCode:";
	// 单位 s
	public static final int SMSCODE_EXPIRE_TIME = 60 * 10;

	public static String getManagerUserPermissionKey(Long managerUserId) {
		return MANAGER_USER_PERMISSION_PREFIX + managerUserId;
	}

	public static String getUserManagerTokenKey(String token) {
		return MANAGER_USER_TOKEN_PREFIX + token;
	}

	public static String getUserKey(Long userId) {
		return USER_TOKEN_PREFIX + userId;
	}

	public static String getUserTokenKey(String token) {
		return USER_TOKEN_PREFIX + token;
	}

	public static String getsmsCodeKey(String mobile, String type) {
		return USER_TOKEN_PREFIX + mobile + ":" + type;
	}

}
