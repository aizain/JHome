package com.bheternal.jhome.mybatis.app.mapper;

import com.bheternal.jhome.mybatis.app.po.OrderWithUser;
import com.bheternal.jhome.mybatis.app.po.User;
import com.bheternal.jhome.mybatis.app.vo.UserQueryVo;

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

    /**
     * 查询用户 根据查询条件
     *
     * @param vo
     * @return
     */
    List<User> findUserByVo(UserQueryVo vo);

    /**
     * 统计用户 根据查询条件
     *
     * @param vo
     * @return
     */
    Integer countUserByVo(UserQueryVo vo);

    /**
     * 统计用户 根据ids
     *
     * @param list
     * @return
     */
    List<User> findUserByIds(List<Integer> list);

}
