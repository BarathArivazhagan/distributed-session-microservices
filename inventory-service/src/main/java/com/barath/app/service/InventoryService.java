package com.barath.app.service;


import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.app.entity.Inventory;
import com.barath.app.entity.InventoryId;
import com.barath.app.exception.InventoryNotFoundException;
import com.barath.app.repository.InventoryRepository;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(Inventory inventory){

        return this.inventoryRepository.save(inventory);
    }

    public Inventory getInventory(Long inventoryId){

        Optional<Inventory> inventoryOptional= this.inventoryRepository.findById(inventoryId);
        if(inventoryOptional.isPresent()){
            return inventoryOptional.get();
        }
        throw new InventoryNotFoundException("Inventory with inventory id "+inventoryId+" not found");
    }

    public List<Inventory> getInventories(){

        List<Inventory> inventorys = this.inventoryRepository.findAll();
        if(logger.isInfoEnabled()){
            inventorys.forEach(inventory -> logger.info(inventory.toString()));
        }
        return inventorys;
    }



    @PostConstruct
    public void init(){
    	
    	
    	InventoryId id1= new InventoryId(1L, 1L);
    	InventoryId id2= new InventoryId(2L, 2L);
    	InventoryId id3= new InventoryId(3L, 3L);
        Inventory inventory1 = new Inventory(id1,"TV","CHENNAI",10);
        Inventory inventory2 = new Inventory(id2,"TV","BANGALORE",10);
        Inventory inventory3 = new Inventory(id3,"TV","DELHI",10);
        Inventory inventory4 = new Inventory(id1,"TV","BANGALORE",10);
        Inventory inventory5 = new Inventory(id2,"TV","DELHI",10);
        Inventory inventory6 = new Inventory(id3,"TV","CHENNAI",10);
        Inventory inventory7 = new Inventory(id1,"TV","DELHI",10);
        Inventory inventory8 = new Inventory(id2,"TV","CHENNAI",10);
        Inventory inventory9 = new Inventory(id3,"TV","BANGALORE",10);
        Arrays.asList(inventory1,inventory2,inventory3,inventory4,inventory5,inventory6,inventory7,inventory8,inventory9)
                .forEach(this::createInventory);

    }
}
