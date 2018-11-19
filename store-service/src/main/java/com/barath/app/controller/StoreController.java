package com.barath.app.controller;

import com.barath.app.entity.Store;
import com.barath.app.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/stores",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping(value="/create",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Store saveStore(@RequestBody  Store store){
        if(logger.isInfoEnabled()) { logger.info("creating new store {}",store); }
        return this.storeService.createStore(store);
    }


    @GetMapping(value="/{storeId}")
    public Store getStore(@PathVariable Long storeId){
        if(logger.isInfoEnabled()) { logger.info("finding store with storeId {}",storeId); }
        return this.storeService.getStore(storeId);
    }

    @GetMapping
    public List<Store> getStores(){
        if(logger.isInfoEnabled()) { logger.info("retrieving all stores"); }
        return this.storeService.getStores();
    }
}
