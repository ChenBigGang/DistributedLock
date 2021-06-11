package site.twofish.RedisLock.service;

/**
 * @author ganggang
 * @since 2021/06/11
 */
public interface RedisLockService {

    Boolean lockUser(Integer id);

    Boolean unlockUser(Integer id);
}
