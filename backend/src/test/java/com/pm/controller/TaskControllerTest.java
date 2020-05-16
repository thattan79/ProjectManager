package com.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.dto.ParentTaskDto;
import com.pm.dto.ProjectDto;
import com.pm.dto.TaskDto;
import com.pm.entity.ParentTask;
import com.pm.service.ITaskService;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITaskService taskServiceImpl;

    @Test
    public void createTask() throws Exception {
        when(taskServiceImpl.createTask(any())).thenReturn(new TaskDto());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new TaskDto());
        final MvcResult mvcResult = mockMvc.perform(
                post("/task/create")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(taskServiceImpl, times(1)).createTask(any());
        assertNotNull(content);
    }

    @Test
    public void updateTask() throws Exception {
        when(taskServiceImpl.updateTask(any())).thenReturn(new TaskDto());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new TaskDto());
        final MvcResult mvcResult = mockMvc.perform(
                post("/task/update")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(taskServiceImpl, times(1)).updateTask(any());
        assertNotNull(content);
    }

    @Test
    public void updateTaskStatus() throws Exception {
        when(taskServiceImpl.updateTaskStatus(any())).thenReturn(new TaskDto());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new TaskDto());
        final MvcResult mvcResult = mockMvc.perform(
                post("/task/updateTaskStatus")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(taskServiceImpl, times(1)).updateTaskStatus(any());
        assertNotNull(content);
    }

    @Test
    public void createParentTask() throws Exception {
        when(taskServiceImpl.createParentTask(new ParentTaskDto())).thenReturn(new ParentTask());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new ParentTaskDto());
        final MvcResult mvcResult = mockMvc.perform(
                post("/task/createParent")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(taskServiceImpl, times(1)).createParentTask(any());
        assertNotNull(content);
    }

    @Test
    public void findAllParentTasks() throws Exception {
        when(taskServiceImpl.findAllParentTasks()).thenReturn(Arrays.asList(new ParentTask()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(new ProjectDto()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/task/findAllParent")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(taskServiceImpl, times(1)).findAllParentTasks();
        assertNotNull(content);
    }

    @Test
    public void findAllParentTasksByInput() throws Exception {
        when(taskServiceImpl.findAllParentTasksByInput(anyString())).thenReturn(Arrays.asList(new ParentTask()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(new ProjectDto()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/task/findAllParentTasksByInput/input")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(taskServiceImpl, times(1)).findAllParentTasksByInput(anyString());
        assertNotNull(content);
    }
}
