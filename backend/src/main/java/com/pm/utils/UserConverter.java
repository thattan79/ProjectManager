package com.pm.utils;


import com.pm.dto.UserDto;
import com.pm.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public List<UserDto> convertUserListToDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.stream().map(user -> {
            final UserDto userDto = new UserDto();
            userDto.setEmployeeId(user.getEmployeeId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setUserId(user.getUserId());
            userDtos.add(userDto);
            return userDtos;
        }).collect(Collectors.toList());
        return userDtos;
    }

    public UserDto convertUserToDto(User user) {
        final UserDto userDto = new UserDto();
        userDto.setEmployeeId(user.getEmployeeId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserId(user.getUserId());
        return userDto;
    }

    public User createUser(UserDto userDto) {
        final User user = new User();
        if (userDto.getUserId() > 0) {
            user.setUserId(userDto.getUserId());
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmployeeId(userDto.getEmployeeId());
        return user;
    }
}
