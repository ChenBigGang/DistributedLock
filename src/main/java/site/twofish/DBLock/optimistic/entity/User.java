package site.twofish.DBLock.optimistic.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.NotNull;

/**
 * @author gang.chen01@hand-china.com
 * @since 2021/06/10
 */
@Getter
@Setter
public class User {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private Integer age;
    @NotNull
    private Integer objectVersionNumber;

    public static User createInstance() {
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(4));
        user.setAge(RandomUtils.nextInt());
        return user;
    }
}
