package com.sebasira.MediumTutorialSecurity.controller;

import com.sebasira.MediumTutorialSecurity.model.User;
import com.sebasira.MediumTutorialSecurity.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest_ONE {


    private MockMvc mockMvc;


    @MockBean
    UserService userService;

    @MockBean
    Authentication authentication;

    @MockBean
    SecurityContext securityContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userService)).build();
    }


    @Test
    public void adminAccess() throws Exception {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("asda");
        when(userService.findUserByEmail("asda")).thenReturn(new User());

        mockMvc.perform(get("/admin/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/home"));
    }
}