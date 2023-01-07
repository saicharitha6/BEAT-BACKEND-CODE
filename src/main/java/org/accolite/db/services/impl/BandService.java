package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Band;
import org.accolite.db.repo.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BandService {
    @Autowired
    private BandRepository bandRepository;

    public String getBandName(long id){
        Optional<Band> bandFromDbObj = bandRepository.findById(id);
        if (bandFromDbObj.isPresent()) {
            Band bandFromDb = bandFromDbObj.get();
            return bandFromDb.getBand();
        }
        else {
            log.info("This employee is not related to any band");
            return null;
        }

    }
}
