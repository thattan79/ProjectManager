package com.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.dto.UserDto;
import com.pm.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userServiceImpl;

    @Test
    public void createUser() throws Exception {
        final UserDto userDto = mockUserDto();
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.createUser(any())).thenReturn(userDto);
        String requestJson = mapper.writeValueAsString(userDto);
        final MvcResult mvcResult = mockMvc.perform(
                post("/user/create")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).createUser(any());
        assertNotNull(content);
    }

    @Test
    public void findAllUser() throws Exception {
        final UserDto userDto = mockUserDto();
        List<UserDto> userDtos = Arrays.asList(userDto, userDto);
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findAllUser()).thenReturn(userDtos);
        String requestJson = mapper.writeValueAsString(userDtos);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findAll")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).findAllUser();
        assertNotNull(content);
    }

    @Test
    public void findAllUserByInput() throws Exception {
        final UserDto userDto = mockUserDto();
        List<UserDto> userDtos = Arrays.asList(userDto, userDto);
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findAllUserByInput(anyString())).thenReturn(userDtos);
        String requestJson = mapper.writeValueAsString(userDtos);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findAllUserByInput/test")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).findAllUserByInput(anyString());
        assertNotNull(content);
    }

    @Test
    public void findUser() throws Exception {
        final UserDto userDto = mockUserDto();
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findById(anyLong())).thenReturn(userDto);
        String requestJson = mapper.writeValueAsString(userDto);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findUserById/10")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).findById(anyLong());
        assertNotNull(content);
    }

    @Test
    public void deleteUser() throws Exception {
        final UserDto userDto = mockUserDto();
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.deleteUser(anyLong())).thenReturn(userDto);
        String requestJson = mapper.writeValueAsString(100);
        final MvcResult mvcResult = mockMvc.perform(
                post("/user/delete")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).deleteUser(anyLong());
        assertNotNull(content);
    }

    @Test
    public void findUserByProjectId() throws Exception {
        final UserDto userDto = mockUserDto();
        List<UserDto> userDtos = Arrays.asList(userDto, userDto);
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findUserByProjectId(anyLong())).thenReturn(userDtos);
        String requestJson = mapper.writeValueAsString(userDtos);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findUserByProjectId/10")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).findUserByProjectId(anyLong());
        assertNotNull(content);
    }


    private UserDto mockUserDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmployeeId(123l);
        return userDto;
    }
}
