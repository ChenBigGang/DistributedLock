package site.twofish.RedisLock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import site.twofish.RedisLock.service.RedisLockService;

import java.util.concurrent.TimeUnit;

/**
 * @author ganggang
 * @since 2021/06/11
 */
@Service
public class RedisLockServiceImpl implements RedisLockService {

    private static final String USER_LOCK_KEY_PREFIX = "lock:user:";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean lockUser(Integer id) {
        return redisTemplate.opsForValue().setIfAbsent(getKey(id), "1", 1000 * 10, TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean unlockUser(Integer id) {
        return redisTemplate.delete(getKey(id));

    }

    public static String getKey(Integer id) {
        return USER_LOCK_KEY_PREFIX + id;
    }
}
