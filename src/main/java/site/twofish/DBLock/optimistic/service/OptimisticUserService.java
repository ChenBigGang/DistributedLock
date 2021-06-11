package site.twofish.DBLock.optimistic.service;

import site.twofish.DBLock.optimistic.entity.User;

/**
 * @author:ganggang
 * @create:2021-06-11
 * @description:
 **/
public interface OptimisticUserService {

    int updateUser(User user);
}
