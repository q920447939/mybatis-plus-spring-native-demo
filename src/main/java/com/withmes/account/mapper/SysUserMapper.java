package com.withmes.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.withmes.account.dto.UserDetailDTO;
import com.withmes.account.dto.UserParamDTO;
import com.withmes.account.entity.SysUser;
import org.apache.ibatis.annotations.Param;


public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    SysUser selectByUsername(String username);

    /**
     * 查询用户列表
     *
     * @param page 分页参数
     * @param userParamDTO 查询参数
     * @return 用户列表
     */
    IPage<UserDetailDTO> listUsers(Page<?> page, @Param("paramDTO") UserParamDTO userParamDTO);
}
