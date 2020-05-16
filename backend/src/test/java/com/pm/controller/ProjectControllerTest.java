package com.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.dto.ProjectDto;
import com.pm.service.IProjectService;
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
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProjectService projectServiceImpl;

    @Test
    public void createProject() throws Exception {
        when(projectServiceImpl.createProject(any())).thenReturn(new ProjectDto());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new ProjectDto());
        final MvcResult mvcResult = mockMvc.perform(
                post("/project/create")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).createProject(any());
        assertNotNull(content);
    }

    @Test
    public void findAllProjects() throws Exception {
        when(projectServiceImpl.findAllProjects()).thenReturn(Arrays.asList(new ProjectDto()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(new ProjectDto()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findAllProjects")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findAllProjects();
        assertNotNull(content);
    }

    @Test
    public void findAllProjectByInput() throws Exception {
        when(projectServiceImpl.findAllProjectByInput(anyString())).thenReturn(Arrays.asList(new ProjectDto()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(new ProjectDto()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findAllProjectByInput/input")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findAllProjectByInput(anyString());
        assertNotNull(content);
    }

    @Test
    public void findProjectById() throws Exception {
        when(projectServiceImpl.findProjectById(anyLong())).thenReturn((new ProjectDto()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new ProjectDto());
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findProjectById/10")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findProjectById(anyLong());
        assertNotNull(content);
    }

    @Test
    public void deleteProject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(projectServiceImpl.deleteProject(anyLong())).thenReturn(new ProjectDto());
        String requestJson = mapper.writeValueAsString(100);
        final MvcResult mvcResult = mockMvc.perform(
                post("/project/delete")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).deleteProject(anyLong());
        assertNotNull(content);
    }

}
