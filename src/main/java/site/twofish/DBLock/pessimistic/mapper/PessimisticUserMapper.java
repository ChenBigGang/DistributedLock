package site.twofish.DBLock.pessimistic.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.DBLock.pessimistic.entity.Lock;

/**
 * @author:gang.chen01@hand-china.com
 * @create:2021-06-10
 * @description:
 **/
@Mapper
public interface PessimisticUserMapper {

    int updateUser(User user);

    User lockUser(Integer id);

    int lockWithConstraint(Lock lock);

    int unlockWithConstraint(Lock lock);
}
