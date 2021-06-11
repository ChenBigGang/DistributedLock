package site.twofish.RedisLock.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.RedisLock.service.RedisLockUserService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ganggang
 * @since 2021/06/11
 */
@RestController
@RequestMapping(value = "/user/redis-lock")
public class RedisLockUserController {
    @Autowired
    RedisLockUserService redisLockUserService;

    @PutMapping
    public List<User> updateUser(@RequestBody User user) {
        return redisLockUserService.updateUser(user);
    }
}
