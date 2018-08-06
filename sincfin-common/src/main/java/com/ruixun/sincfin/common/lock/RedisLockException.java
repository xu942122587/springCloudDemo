package com.ruixun.sincfin.common.lock;

public class RedisLockException extends RuntimeException {
    public RedisLockException(String message) {
        super(message);
    }
}
