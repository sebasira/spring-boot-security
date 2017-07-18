package com.sebasira.MediumTutorialSecurity.service;

import com.sebasira.MediumTutorialSecurity.model.Client;
import com.sebasira.MediumTutorialSecurity.model.User;

import java.util.List;

/**
 * Created by sebas on 18/07/17.
 */
public interface ClientService {
    public void saveClient(Client client);

    public void deleteClient(long client_id);

    public void disableClient(long client_id);

    public void enableClient(long client_id);
}
