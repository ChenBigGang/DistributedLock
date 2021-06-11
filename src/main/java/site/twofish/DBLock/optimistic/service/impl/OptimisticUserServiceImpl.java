package site.twofish.DBLock.optimistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.DBLock.optimistic.mapper.OptimisticUserMapper;
import site.twofish.DBLock.optimistic.service.OptimisticUserService;

/**
 * @author ganggang
 * @since 2021/06/11
 */
@Service
public class OptimisticUserServiceImpl implements OptimisticUserService {
    @Autowired
    OptimisticUserMapper optimisticUserMapper;

    @Override
    public int updateUser(User user) {
        int res = optimisticUserMapper.updateUser(user);
        if (res == 0) {
            throw new RuntimeException("乐观锁异常，请重试");
        }
        return res;
    }
}
