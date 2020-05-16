package com.pm.utils;

import com.pm.dto.UserDto;
import com.pm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    public void convertUserListToDtoList() {
        assertNotNull(userConverter.convertUserListToDtoList(Arrays.asList(mockUser())));
    }

    @Test
    public void convertUserToDto() {
        assertNotNull(userConverter.convertUserToDto(mockUser()));
    }

    @Test
    public void convertUserDtoToUser() {
        assertNotNull(userConverter.convertUserDtoToUser(mockUserDto()));
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId("123");
        return user;
    }

    private UserDto mockUserDto() {
        final UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmployeeId("123");
        return userDto;
    }
}
