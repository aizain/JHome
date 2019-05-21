package com.aizain.jhome.mybatis.source.vo;

import com.aizain.jhome.mybatis.source.po.User;
import lombok.Data;

import java.util.List;

/**
 * UserQueryVo
 *
 * @author Zain
 * @date 2019-05-21
 */
@Data
public class UserQueryVo {

    private User user;
    private List<Integer> ids;

}
