package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.ExpSlabs;
import org.accolite.db.entities.SlabCharges;
import org.accolite.db.repo.ExpSlabsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpSlabsService {
    @Autowired
    private ExpSlabsRepository expSlabsRepository;

    public String getSlabById(long id) {
        Optional<ExpSlabs> expSlabs = expSlabsRepository.findById(id);
        if (expSlabs.isPresent()) {
            return expSlabs.get().getSlab();
        }
        else {
            log.info("Slab with slab ID:" + id + " is not present");
            return "";
        }
    }
}
