package com.withmes.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserInfoDTO {
    private Integer id;

    private String username;

    private String name;

    private Integer organizationId;

    private String organizationName;

    private String introduction;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] roles;

    private Integer roleLevel;

    private String avatar;
}
