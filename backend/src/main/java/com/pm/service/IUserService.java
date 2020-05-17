package com.pm.service;

import com.pm.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> findAllUser();

    List<UserDto> findAllUserByInput(String input);

    UserDto findById(Long id);

    UserDto deleteUser(Long id);

    List<UserDto> findUserByProjectId(Long id);
}
