package com.aizain.jhome.mybatis.source.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OrderWithUser
 *
 * @author Zain
 * @date 2019-05-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderWithUser extends Order {

    private User user;

    @Override
    public String toString() {
        return "OrderWithUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
