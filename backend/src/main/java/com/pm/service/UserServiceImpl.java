package com.pm.service;

import com.pm.dto.UserDto;
import com.pm.entity.User;
import com.pm.repository.UserRepository;
import com.pm.utils.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserConverter userConverter;

    public UserDto createUser(UserDto userDto) {
        final User user = userConverter.createUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    public List<UserDto> findAllUser() {
        return userConverter.convertUserListToDtoList(userRepository.findAll());
    }

    public List<UserDto> findAllUserByInput(String input) {
        List<User> users;
        if ("default".equals(input)) {
            return findAllUser();
        }
        users = userRepository.findByFirstNameContaining(input);
        if (users != null && users.size() == 0) {
            users = userRepository.findByLastNameContaining(input);
        }
        if (users != null && users.size() == 0) {
            users = userRepository.findByEmployeeIdContaining(input);
        }
        return userConverter.convertUserListToDtoList(users);
    }

    public UserDto findById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            return userConverter.convertUserToDto(optUser.get());
        }
        return new UserDto();
    }

    public UserDto deleteUser(Long id) {
        final Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            userRepository.delete(optUser.get());
        }
        return new UserDto();
    }

    public List<UserDto> findUserByProjectId(Long id) {
        return userConverter.convertUserListToDtoList(userRepository.findByProjectId(id));
    }
}
