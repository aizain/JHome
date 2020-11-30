package com.bheternal.jhome.mybatis.app.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * OrderWithUser
 *
 * @author Zain
 * @date 2019-05-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderWithUser extends Order implements Serializable {

    private User user;

    @Override
    public String toString() {
        return "OrderWithUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
