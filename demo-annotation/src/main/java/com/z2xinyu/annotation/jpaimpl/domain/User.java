package com.z2xinyu.annotation.jpaimpl.domain;

import lombok.Data;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 20:56
 */
@Data
@Table("tb_user")
public class User {
    /**
     * 当前属性对应的列名，而且说明这个列是主键
     */
    @ID("u_id")
    private String uid;
    @Column("uname")
    private String username;
    @Column("pwd")
    private String password;
}
