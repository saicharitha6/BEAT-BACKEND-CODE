package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.SlabCharges;
import org.accolite.db.repo.SlabChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SlabChargesService {
    @Autowired
    private SlabChargesRepository slabChargesRepository;

    public List<SlabCharges> getSlabChargesListByOrgId(long id) {
        List<SlabCharges> slabChargesList = slabChargesRepository.findAllByOrgId(id);
        return slabChargesList;
    }
}
