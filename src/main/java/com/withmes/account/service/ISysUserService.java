package com.withmes.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.withmes.account.dto.UserDetailDTO;
import com.withmes.account.dto.UserParamDTO;
import com.withmes.account.entity.SysUser;

import java.util.List;


public interface ISysUserService {
    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户
     */
    SysUser getById(Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    SysUser getByUsername(String username);

    /**
     * 查询用户列表
     *
     * @param organizationId 组织ID
     * @param userParamDTO   查询参数
     * @return 用户列表
     */
    IPage<UserDetailDTO> listUsers(Integer organizationId, UserParamDTO userParamDTO);

    /**
     * 创建用户
     *
     * @return 用户
     */
    int createUser();


    /**
     * 更新用户，部分关键字段不会更新
     *
     * @param organizationId 组织ID
     * @param user           用户
     * @return 用户
     */
    UserDetailDTO updateUser(Integer organizationId, UserDetailDTO user);


    /**
     * 查询用户列表
     *
     * @param organizationId 组织ID
     * @param userParamDTO   查询参数
     * @return 用户列表
     */
    List<SysUser> listUsersByWrapper(Integer organizationId, UserParamDTO userParamDTO);
}
