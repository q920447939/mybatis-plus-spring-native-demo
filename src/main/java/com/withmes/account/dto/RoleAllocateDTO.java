package com.withmes.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAllocateDTO {
    Integer userId;

    List<String> roleCodeList;

    Boolean add;

    public RoleAllocateDTO(Integer userId, String[] roleCodes, boolean add) {
        this.userId = userId;
        this.roleCodeList = Arrays.asList(roleCodes);
        this.add = add;
    }
}
