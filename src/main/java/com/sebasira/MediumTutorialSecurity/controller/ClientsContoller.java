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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sebas on 17/07/17.
 */
@Controller
public class ClientsContoller {

    @Autowired
    private ClientService clientService;

    private UserService userService;

    @Autowired
    public ClientsContoller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/admin/clients", method = RequestMethod.GET)
    public ModelAndView clients(){
        ModelAndView modelAndView = new ModelAndView();
        Client client = new Client();
        modelAndView.addObject("client", client);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Client> clientList = user.getClients();
        modelAndView.addObject("clientList", clientList);

        modelAndView.setViewName("admin/clients");
        return modelAndView;
    }

    @RequestMapping(value = "/client/add", method = RequestMethod.POST)
    public String createNewClient(@Valid Client client, BindingResult bindingResult) {
        //ModelAndView modelAndView = new ModelAndView();


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        client.setUser(user);
        clientService.saveClient(client);

        //modelAndView.addObject("successMessage", "Client has been saved");
        //modelAndView.addObject("user", new User());
        //modelAndView.setViewName("admin/clients");

        return "redirect:/clients";
    }


    @RequestMapping(value = "/delete_client/{client_id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable String client_id) {
        clientService.deleteClient(Long.parseLong(client_id));
        return "redirect:/clients";
    }


    @RequestMapping(value = "/disable_client/{client_id}", method = RequestMethod.GET)
    public String disableClient(@PathVariable String client_id) {
        clientService.disableClient(Long.parseLong(client_id));
        return "redirect:/clients";
    }


    @RequestMapping(value = "/enable_client/{client_id}", method = RequestMethod.GET)
    public String enableClient(@PathVariable String client_id) {
        clientService.enableClient(Long.parseLong(client_id));
        return "redirect:/clients";
    }
}
