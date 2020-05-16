package com.pm.controller;

import com.pm.dto.UserDto;
import com.pm.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userServiceImpl;


    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userServiceImpl.createUser(userDto);
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUser() {
        return userServiceImpl.findAllUser();
    }

    @GetMapping("/findAllUserByInput/{input}")
    public List<UserDto> findAllUserByInput(@PathVariable("input") String input) {
        return userServiceImpl.findAllUserByInput(input);
    }

    @GetMapping("/findUserById/{id}")
    public UserDto findUser(@PathVariable("id") Long id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping("/delete")
    public UserDto deleteUser(@RequestBody Long id) {
        return userServiceImpl.deleteUser(id);
    }

    @GetMapping("/findUserByProjectId/{id}")
    public List<UserDto> findUserByProjectId(@PathVariable("id") Long id) {
        return userServiceImpl.findUserByProjectId(id);
    }

}
