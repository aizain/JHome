package com.aizain.jhome.mybatis.source.mapper;

import com.aizain.jhome.mybatis.source.po.OrderWithUser;
import com.aizain.jhome.mybatis.source.po.User;

import java.util.List;

/**
 * UserMapper
 *
 * @author Zain
 * @date 2019-05-16
 */
public interface UserMapper {

    /**
     * 查询用户 根据ID
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 查询用户 根据ID 存在别名
     *
     * @param id
     * @return
     */
    User findUserByIdWithAlias(Integer id);

    /**
     * 查询用户及订单 关联查询
     *
     * @return
     */
    List<User> findUserAndOrder();

    /**
     * 查询订单及用户 延迟加载
     *
     * @return
     */
    List<OrderWithUser> findOrderAndUser();

}
