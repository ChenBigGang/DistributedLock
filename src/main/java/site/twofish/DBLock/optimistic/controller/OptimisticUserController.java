package site.twofish.DBLock.optimistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.twofish.DBLock.optimistic.entity.User;
import site.twofish.DBLock.optimistic.mapper.OptimisticUserMapper;
import site.twofish.DBLock.optimistic.service.OptimisticUserService;
import site.twofish.DBLock.pessimistic.service.PessimisticUserService;

import java.util.List;

/**
 * @author gang.chen01@hand-china.com
 * @since 2021/06/10
 */

@RestController
@RequestMapping(value = "/user/optimistic")
public class OptimisticUserController {
    @Autowired
    OptimisticUserMapper optimisticUserMapper;

    @Autowired
    OptimisticUserService optimisticUserService;

    @GetMapping
    public List<User> getUser() {
        return optimisticUserMapper.getUser();
    }


    @PostMapping
    public List<User> addUser() {
        optimisticUserMapper.addUser(User.createInstance());
        return optimisticUserMapper.getUser();
    }

    @PutMapping
    public List<User> updateUser(@Validated @RequestBody User user) {
        optimisticUserService.updateUser(user);
        return optimisticUserMapper.getUser();
    }

}
