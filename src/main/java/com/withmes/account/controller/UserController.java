package com.withmes.account.controller;

import com.withmes.account.dto.UserDetailDTO;
import com.withmes.account.dto.UserParamDTO;
import com.withmes.account.service.ISysUserService;
import com.withmes.base.Result;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final ISysUserService sysUserService;

    public UserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @GetMapping("/findById")
    public Result findById(
            @RequestParam Integer id) {
        return new Result(sysUserService.getById(id));
    }
    @GetMapping
    public Result listUsers(
          @PathVariable Integer organizationId,
            UserParamDTO userParamDTO) {
        return new Result(sysUserService.listUsers(organizationId, userParamDTO));
    }


    @GetMapping("/createUser")
    public Result createUser() {
        return new Result(sysUserService.createUser());
    }


    @PutMapping
    public Result updateUser(
            @PathVariable Integer organizationId,
            @RequestBody UserDetailDTO user) {
        return new Result(sysUserService.updateUser(organizationId, user));
    }

    @GetMapping("/wrapper-example")
    public Result listUsersByWrapper(
            @PathVariable Integer organizationId,
            UserParamDTO userParamDTO) {
        return new Result(sysUserService.listUsersByWrapper(organizationId, userParamDTO));
    }
}
