package org.example.backend.controller;

import org.example.backend.model.PackingList;
import org.example.backend.service.PackingListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packinglist")
public class PackingListController {

    private final PackingListService packingListService;

    public PackingListController(PackingListService packingListService) {
        this.packingListService = packingListService;
    }

    @PostMapping("/add")
    public PackingList packingList(@RequestBody PackingList packingList) {
        return packingListService.addPackingList(packingList);
    }
}
