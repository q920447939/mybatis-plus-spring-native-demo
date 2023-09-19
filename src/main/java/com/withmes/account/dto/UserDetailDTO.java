package com.withmes.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class UserDetailDTO {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String organizationName;

    private Integer organizationId;

    private String introduction;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] roles;

    private String avatar;

    private Boolean enabled;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date lastLoginTime;

}
