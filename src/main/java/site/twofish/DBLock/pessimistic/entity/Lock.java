package site.twofish.DBLock.pessimistic.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ganggang
 * @since 2021/06/11
 */
@Getter
@Setter
public class Lock {
    private Integer id;
    private String tableName;
    private Integer dataId;
}
