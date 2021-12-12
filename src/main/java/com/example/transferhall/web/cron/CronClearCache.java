package com.example.transferhall.web.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronClearCache {
    private final EvictCashValues evictCashValues;

    private static final Logger LOGGER = LoggerFactory.getLogger(CronClearCache.class);

    public CronClearCache(EvictCashValues evictCashValues) {
        this.evictCashValues = evictCashValues;
    }

    //TODO reduce time for exam
    @Scheduled(fixedRate = 80000)
    public void clearOrdersCache(){
        LOGGER.info("User orders cache evict by cron method getImages()");
        evictCashValues.clearCacheOfUserOrders();
    }
}
