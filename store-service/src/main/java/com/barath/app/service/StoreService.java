package com.barath.app.service;


import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.app.entity.Store;
import com.barath.app.exception.StoreNotFoundException;
import com.barath.app.repository.StoreRepository;

@Service
public class StoreService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store createStore(Store store){

        return this.storeRepository.save(store);
    }

    public Store getStore(Long storeId){

        Optional<Store> storeOptional= this.storeRepository.findById(storeId);
        if(storeOptional.isPresent()){
            return storeOptional.get();
        }
        throw new StoreNotFoundException("Store with store id "+storeId+" not found");
    }

    public List<Store> getStores(){

        List<Store> stores = this.storeRepository.findAll();
        if(logger.isInfoEnabled()){
            stores.forEach(store -> logger.info(store.toString()));
        }
        return stores;
    }



    @PostConstruct
    public void init(){

        Store store1 = new Store(1L,"COSTCO STORE");
        Store store2 = new Store(2L,"WALMART");
        Store store3 = new Store(3L,"AMAZON");
        Store store4 = new Store(4L,"MYNTRA");
        Store store5 = new Store(5L,"ROSE FANCY");
        Store store6 = new Store(6L,"IKEA");
        Store store7 = new Store(7L,"SKEA");
        Store store8 = new Store(8L,"HAMLEYS");
        Arrays.asList(store1,store2,store3,store4,store5,store6,store7,store8)
                .forEach(this::createStore);

    }
}
