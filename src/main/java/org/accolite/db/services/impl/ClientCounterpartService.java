package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.ClientCounterpart;
import org.accolite.db.repo.ClientCounterpartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClientCounterpartService {
    @Autowired
    ClientCounterpartRepository clientCounterpartRepository;

    public String getClientCounterpartName(long clientCounterpartId){
        Optional<ClientCounterpart> clientCounterpartFromDbObj = clientCounterpartRepository.findById(clientCounterpartId);
        if (clientCounterpartFromDbObj.isPresent()) {
            ClientCounterpart clientCounterpartFromDb = clientCounterpartFromDbObj.get();
            return clientCounterpartFromDb.getName();
        }
        else {
            log.info("This employee is not related to any Client counterpart");
            return null;
        }
    }
}
