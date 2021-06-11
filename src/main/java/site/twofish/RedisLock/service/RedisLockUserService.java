package site.twofish.RedisLock.service;

import org.springframework.stereotype.Service;
import site.twofish.DBLock.optimistic.entity.User;

import java.util.List;

/**
 * @author:ganggang
 * @create:2021-06-11
 * @description:
 **/
public interface RedisLockUserService {

    List<User> updateUser(User user);
}
