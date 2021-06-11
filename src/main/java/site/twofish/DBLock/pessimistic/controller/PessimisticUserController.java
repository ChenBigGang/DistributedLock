package site.twofish.DBLock.pessimistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.DBLock.optimistic.mapper.OptimisticUserMapper;
import site.twofish.DBLock.pessimistic.service.PessimisticUserService;

import java.util.List;

/**
 * @author gang.chen01@hand-china.com
 * @since 2021/06/10
 */
@RestController
@RequestMapping(value = "/user/pessimistic")
public class PessimisticUserController {

    @Autowired
    PessimisticUserService pessimisticUserService;

    @Autowired
    OptimisticUserMapper optimisticUserMapper;

    /**
     * 基于数据库的锁实现分布式锁。
     */
    @PutMapping(value = "/with-lock")
    public String updateUser(@Validated @RequestBody User user) {
        Long startTime = System.currentTimeMillis();
        pessimisticUserService.updateUserLockWithDbLock(user);
        Long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        return "更新消耗时间为: " + elapsedTime + "毫秒";
    }

    /**
     * 基于数据库的锁实现分布式锁。
     */
    @PutMapping(value = "/with-constraint-lock")
    public List<User> updateUserWithConstraint(@Validated @RequestBody User user) {
        pessimisticUserService.updateUserWithConstraint(user);
        return optimisticUserMapper.getUser();
    }

}
