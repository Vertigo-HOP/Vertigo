package org.example.backend.service;

import org.example.backend.model.PackingList;
import org.example.backend.repository.IPackingListRepository;
import org.springframework.stereotype.Service;

@Service
public class PackingListService {

    private final IPackingListRepository packingListRepository;

    public PackingListService(IPackingListRepository packingListRepository) {
        this.packingListRepository = packingListRepository;
    }

    public PackingList addPackingList(PackingList packingList) {
        return packingListRepository.save(packingList);
    }
}
