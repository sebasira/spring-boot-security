package com.sebasira.MediumTutorialSecurity.controller;

import com.sebasira.MediumTutorialSecurity.model.Client;
import com.sebasira.MediumTutorialSecurity.model.User;
import com.sebasira.MediumTutorialSecurity.service.ClientService;
import com.sebasira.MediumTutorialSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by sebas on 17/07/17.
 */
@Controller
public class ClientsContoller {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/clients", method = RequestMethod.GET)
    public ModelAndView clients(){
        ModelAndView modelAndView = new ModelAndView();
        Client client = new Client();
        modelAndView.addObject("client", client);
        modelAndView.setViewName("admin/clients");
        return modelAndView;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ModelAndView createNewClient(@Valid Client client, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        clientService.saveClient(client);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("successMessage", "Client has been saved");
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("admin/clients");

        return modelAndView;
    }
}
