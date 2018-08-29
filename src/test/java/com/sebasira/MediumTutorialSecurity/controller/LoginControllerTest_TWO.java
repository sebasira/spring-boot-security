package com.sebasira.MediumTutorialSecurity.controller;

import com.sebasira.MediumTutorialSecurity.config.CustomAuthenticationProvider;
import com.sebasira.MediumTutorialSecurity.config.SecurityConfiguration;
import com.sebasira.MediumTutorialSecurity.model.User;
import com.sebasira.MediumTutorialSecurity.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoginController.class)
@Import(SecurityConfiguration.class)
public class LoginControllerTest_TWO {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    CustomAuthenticationProvider customAuthenticationProvider;

    @Test
    @WithMockUser(username = "sebas", password = "nopass", authorities = "ADMIN")
    public void adminAccess() throws Exception {

        User testUser = new User();
        testUser.setName("seba");
        testUser.setLastName("ira");
        testUser.setEmail("my@mail.com");

        // SAME USERNAME AS MOCKUSER
        when(userService.findUserByEmail("sebas")).thenReturn(testUser);

        mockMvc.perform(get("/admin/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/home"));
    }
}