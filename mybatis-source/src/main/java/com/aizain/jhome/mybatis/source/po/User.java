package com.aizain.jhome.mybatis.source.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User
 *
 * @author Zain
 * @date 2019-05-16
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Order> orders;

}
