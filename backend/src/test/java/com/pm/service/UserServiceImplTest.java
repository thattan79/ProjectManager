package com.pm.service;

import com.pm.dto.UserDto;
import com.pm.entity.User;
import com.pm.exception.UserException;
import com.pm.repository.UserRepository;
import com.pm.utils.UserConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;

    @Test
    public void createUserEmployeeIdExist() {
        final UserDto userDto = mockUserDto();
        final User user = mockUser();
        when(userRepository.findByEmployeeId(anyLong())).thenReturn(null);
        when(userConverter.convertUserDtoToUser(userDto)).thenReturn(user);
        when(userRepository.save(any())).thenReturn(mockUser());
        userServiceImpl.createUser(userDto);
        verify(userRepository, times(1)).save(any());
        verify(userConverter, times(1)).convertUserDtoToUser(any());
    }

    @Test(expected = UserException.class)
    public void createUser() {
        final UserDto userDto = mockUserDto();
        final User user = mockUser();
        when(userRepository.findByEmployeeId(anyLong())).thenReturn(user);
        userServiceImpl.createUser(userDto);
    }

    @Test
    public void findAllUser() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(mockUser()));
        userServiceImpl.findAllUser();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void findAllUserByInputDefault() {
        when(userConverter.convertUserListToDtoList(anyList())).thenReturn(Arrays.asList(mockUserDto()));
        assertNotNull(userServiceImpl.findAllUserByInput("default"));
    }

    @Test
    public void findAllUserByInputFirstName() {
        when(userRepository.findByFirstNameContaining(anyString())).thenReturn(Arrays.asList(mockUser()));
        when(userConverter.convertUserListToDtoList(anyList())).thenReturn(Arrays.asList(mockUserDto()));
        assertNotNull(userServiceImpl.findAllUserByInput("test"));
    }

    @Test
    public void findAllUserByInputLastName() {
        when(userRepository.findByFirstNameContaining(anyString())).thenReturn(Arrays.asList());
        when(userRepository.findByLastNameContaining(anyString())).thenReturn(Arrays.asList(mockUser()));
        when(userConverter.convertUserListToDtoList(anyList())).thenReturn(Arrays.asList(mockUserDto()));
        assertNotNull(userServiceImpl.findAllUserByInput("test"));
    }

    @Test
    public void findAllUserByInputEmployeeId() {
        when(userRepository.findByFirstNameContaining(anyString())).thenReturn(Arrays.asList());
        when(userRepository.findByLastNameContaining(anyString())).thenReturn(Arrays.asList());
        when(userRepository.findByEmployeeIdContaining(anyString())).thenReturn(Arrays.asList(mockUser()));
        when(userConverter.convertUserListToDtoList(anyList())).thenReturn(Arrays.asList(mockUserDto()));
        assertNotNull(userServiceImpl.findAllUserByInput("test"));
    }

    @Test
    public void findByIdReturnEmpty() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNotNull(userServiceImpl.findById(10L));
    }

    @Test
    public void findByIdReturnValue() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser()));
        userServiceImpl.findById(10L);
        verify(userConverter, times(1)).convertUserToDto(any());
    }

    @Test
    public void deleteUserIdNotExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNotNull(userServiceImpl.deleteUser(10L));
    }

    @Test
    public void deleteUserIdExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser()));
        doNothing().when(userRepository).delete(isA(User.class));
        assertNotNull(userServiceImpl.deleteUser(10L));
    }

    @Test
    public void findUserByProjectId() {
        when(userRepository.findByProjectId(anyLong())).thenReturn(Arrays.asList(mockUser()));
        when(userConverter.convertUserListToDtoList(anyList())).thenReturn(Arrays.asList(mockUserDto()));
        assertNotNull(userServiceImpl.findUserByProjectId(anyLong()));
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId(123l);
        return user;
    }

    private UserDto mockUserDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmployeeId(123l);
        return userDto;
    }

}
