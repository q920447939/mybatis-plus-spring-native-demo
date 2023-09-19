package com.withmes.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

/**
 * 用户实体类
 *
 * @author CGM
 */
@Data
@NoArgsConstructor
public class SysUser implements  Serializable {

    @TableId(type = IdType.AUTO)
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer organizationId;

    private String username;

    private String password;

    private String name;

    private String nickname;

    /**
     * 防止与lombok注解冲突
     */
    @TableField(value = "is_enabled")
    @JsonProperty("enabled")
    private Boolean enable;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date lastLoginTime;

    private Integer updatedBy;

    private String avatar;


}
