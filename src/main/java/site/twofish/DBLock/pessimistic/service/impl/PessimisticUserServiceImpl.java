package site.twofish.DBLock.pessimistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.DBLock.pessimistic.entity.Lock;
import site.twofish.DBLock.pessimistic.mapper.PessimisticUserMapper;
import site.twofish.DBLock.pessimistic.service.PessimisticUserService;

/**
 * @author ganggang
 * @since 2021/06/10
 */
@Service
public class PessimisticUserServiceImpl implements PessimisticUserService {
    @Autowired
    PessimisticUserMapper pessimisticUserMapper;


    /**
     * 基于数据库的排他锁实现分布式锁
     *
     * @param user
     * @return
     */
    @Override
    // 开启事务
    @Transactional(rollbackFor = Exception.class)
    public int updateUserLockWithDbLock(User user) {
        // 更新之前先加锁，锁无需手动释放，因为事务托管给了spring，所以service方法执行完后，事务会自动提交，锁也会跟着释放。
        pessimisticUserMapper.lockUser(user.getId());
        // 更新数据
        int res = pessimisticUserMapper.updateUser(user);
        // 等待10s，方便看锁的效果
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int updateUserWithConstraint(User user) {
        Lock lock = new Lock();
        lock.setTableName("user");
        lock.setDataId(user.getId());
        //加锁。
        try {
            pessimisticUserMapper.lockWithConstraint(lock);
        } catch (Exception e) {
            // 因为有唯一约束，所以如果已经有其他终端加了锁，这里再次加锁就会失败、违反约束。
            throw new RuntimeException("获取锁失败，请重试");
        }
        //加锁成功就继续更新数据。
        int res = pessimisticUserMapper.updateUser(user);
        //等待10s，延长锁释放的时间
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //解锁，操作完成后释放锁。
        pessimisticUserMapper.unlockWithConstraint(lock);
        return res;
    }
}
