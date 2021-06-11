package site.twofish.DBLock.optimistic.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.twofish.DBLock.optimistic.entity.User;

import java.util.List;

/**
 * @author:gang.chen01@hand-china.com
 * @create:2021-06-10
 * @description:用户Mapper
 **/
@Mapper
public interface OptimisticUserMapper {

    List<User> getUser();

    int addUser(User user);

    int updateUser(User user);


}
