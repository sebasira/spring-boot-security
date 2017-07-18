package com.sebasira.MediumTutorialSecurity.repository;

import com.sebasira.MediumTutorialSecurity.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by sebas on 18/07/17.
 */
@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUserId(long user_id);
}
