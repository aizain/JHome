package com.bheternal.jhome.mybatis.app.vo;

import com.bheternal.jhome.mybatis.app.po.User;

import java.util.List;

/**
 * UserQueryVo
 *
 * @author Zain
 * @date 2019-05-21
 */
public class UserQueryVo {

    private User user;
    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
