package com.withmes.account.dto;

import com.withmes.base.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(of = {"name", "organizationId"}, callSuper = false)
public class UserParamDTO extends PageParam {
    private String name;

    private Integer organizationId;

    private String organizationName;

    private Boolean enabled;

}
