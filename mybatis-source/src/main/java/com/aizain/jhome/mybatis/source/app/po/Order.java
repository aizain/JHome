package com.aizain.jhome.mybatis.source.app.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Order
 *
 * @author Zain
 * @date 2019-05-18
 */
@Data
public class Order implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer status;
    private Integer number;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
