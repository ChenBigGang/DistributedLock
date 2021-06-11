package site.twofish.DBLock.pessimistic.service;

import site.twofish.DBLock.optimistic.entity.User;

/**
 * @author:ganggang
 * @create:2021-06-10
 * @description:
 **/
public interface PessimisticUserService {

    int updateUserLockWithDbLock(User user);

    int updateUserWithConstraint(User user);
}
