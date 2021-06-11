package site.twofish.RedisLock.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.DBLock.optimistic.mapper.OptimisticUserMapper;
import site.twofish.DBLock.pessimistic.mapper.PessimisticUserMapper;
import site.twofish.RedisLock.service.RedisLockService;
import site.twofish.RedisLock.service.RedisLockUserService;

import java.util.List;

/**
 * @author ganggang
 * @since 2021/06/11
 */
@Service
public class RedisLockUserServiceImpl implements RedisLockUserService {
    @Autowired
    RedisLockService redisLockService;

    @Autowired
    PessimisticUserMapper pessimisticUserMapper;

    @Autowired
    OptimisticUserMapper optimisticUserMapper;


    @Override
    public List<User> updateUser(User user) {
        Boolean res = redisLockService.lockUser(user.getId());
        if (res) {
            pessimisticUserMapper.updateUser(user);
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisLockService.unlockUser(user.getId());
            return optimisticUserMapper.getUser();
        } else {
            throw new RuntimeException("获取锁失败，请重试");
        }
    }
}
