package com.sebasira.MediumTutorialSecurity.service;

import com.sebasira.MediumTutorialSecurity.model.Client;
import com.sebasira.MediumTutorialSecurity.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sebas on 18/07/17.
 */
@Service("clientService")
public class ClientServiceImplementation implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}
