package com.withmes.account.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.withmes.account.dto.UserDetailDTO;
import com.withmes.account.dto.UserParamDTO;
import com.withmes.account.entity.SysUser;
import com.withmes.account.mapper.SysUserMapper;
import com.withmes.account.service.ISysUserService;
import com.withmes.config.ExtraConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Service
@Slf4j
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ExtraConfig extraConfig;


    @Override
    public SysUser getById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser getByUsername(String username) {
        List<SysUser> sysUserList = sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().like(SysUser::getUsername, username));
        if (null == sysUserList || sysUserList.isEmpty()){
            return null;
        }
        return sysUserList.get(0);
    }


    @Override
    public IPage<UserDetailDTO> listUsers(Integer organizationId, UserParamDTO userParamDTO) {

        Page<Object> page = new Page<>(userParamDTO.getPage(), userParamDTO.getLimit());
        IPage<UserDetailDTO> userPage = sysUserMapper.listUsers(page, userParamDTO);


        return userPage;
    }

    @Override
    public int createUser() {
        SysUser info = new SysUser();
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        info.setId(randomInt);
        info.setOrganizationId(0);
        info.setUsername("username"+ randomInt);
        info.setPassword("");
        info.setName("");
        info.setNickname("");
        info.setEnable(false);
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setLastLoginTime(new Date());
        info.setUpdatedBy(0);
        info.setAvatar("");
        return sysUserMapper.insert(info);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailDTO updateUser(Integer organizationId, UserDetailDTO dto) {
        SysUser targetUser = new SysUser();
        sysUserMapper.updateById(targetUser);
        return dto;
    }

    @Override
    public List<SysUser> listUsersByWrapper(Integer organizationId, UserParamDTO userParamDTO) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.like("name", userParamDTO.getName());
        wrapper.eq("organization_id", organizationId);
        // 普通查询可以使用 new QueryWrapper<>(user), 为 user 赋值即可作为查询参数
        return sysUserMapper.selectList(wrapper);
    }
}
