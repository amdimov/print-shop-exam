package com.example.transferhall.cashing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronOperations {
    //VARIANT 2
    private CacheManager cacheManager;

    //VARIANT 1
    private final EvictCashValues evictCashValues;

    private static Logger LOGGER = LoggerFactory.getLogger(CronOperations.class);

    public CronOperations(CacheManager cacheManager, EvictCashValues evictCashValues) {
        this.cacheManager = cacheManager;
        this.evictCashValues = evictCashValues;
    }

    @Scheduled(fixedRate = 80000)
    public void getImages(){
        LOGGER.info("New CRON Scheduled");
        //VARIANT 2
//        clearCache();

        //VARIANT 1
        evictCashValues.clearCacheOfImages();
    }

    //VARIANT 2
//    public void clearCache(){
//        cacheManager.getCache("images").clear();
//    }
}
