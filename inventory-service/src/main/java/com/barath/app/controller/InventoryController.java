package com.barath.app.controller;

import com.barath.app.entity.Inventory;
import com.barath.app.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Inventory saveInventory(@RequestBody  Inventory inventory){
        if(logger.isInfoEnabled()) { logger.info("creating new inventory {}",inventory); }
        return this.inventoryService.createInventory(inventory);
    }


    @GetMapping(value="/{inventoryId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Inventory getInventory(@PathVariable Long inventoryId){
        if(logger.isInfoEnabled()) { logger.info("finding inventory with inventoryId {}",inventoryId); }
        return this.inventoryService.getInventory(inventoryId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Inventory> getInventories(){
        if(logger.isInfoEnabled()) { logger.info("retrieving all inventories"); }
        return this.inventoryService.getInventories();
    }
}
