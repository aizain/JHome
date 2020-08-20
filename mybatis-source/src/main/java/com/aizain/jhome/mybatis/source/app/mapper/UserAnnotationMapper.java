package com.aizain.jhome.mybatis.source.app.mapper;

import com.aizain.jhome.mybatis.source.app.po.OrderWithUser;
import com.aizain.jhome.mybatis.source.app.po.User;
import com.aizain.jhome.mybatis.source.app.vo.UserQueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * UserAnnotationMapper
 *
 * @author Zain
 * @date 2019-05-16
 */
public interface UserAnnotationMapper {

    /**
     * 查询用户 根据id
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findUserById(Integer id);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    @Insert("insert into user (name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    Integer insertUser(User user);

    /**
     * 查询用户 根据v
     *
     * @param vo
     * @return
     */
    @SelectProvider(type = FindUserByVoSqlBuilder.class, method = "findUserByVo")
    List<User> findUserByVo(UserQueryVo vo);

    /**
     * 查询用户 根据id 通过resultMap组装数据
     *
     * @param id
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "create_time", property = "createTime"),
    })
    @Select("select * from user where id = #{id}")
    User findUserByIdWithMap(Integer id);

    /**
     * 查询订单 延迟加载用户信息
     *
     * @return
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "number", property = "number"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "user_id",
                    property = "user",
                    javaType = User.class,
                    one = @One(select = "com.aizain.jhome.mybatis.source.app.mapper.UserAnnotationMapper.findUserById",
                            fetchType = FetchType.LAZY
                    )),
    })
    @Select("select * from `order`")
    List<OrderWithUser> findOrderLazyLoadUser();


    class FindUserByVoSqlBuilder {
        public String findUserByVo(final UserQueryVo vo) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("user");
                    User user = vo.getUser();
                    if (user != null && user.getName() != null && "".equals(user.getName())) {
                        WHERE("username like CONCAT('%', #{name}, '%')");
                    }
                    ORDER_BY("id");
                }
            }.toString();
        }
    }

}
