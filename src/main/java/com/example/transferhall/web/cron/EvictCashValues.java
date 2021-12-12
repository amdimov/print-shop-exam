package com.example.transferhall.web.cron;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EvictCashValues {

    @CacheEvict(value = "orders", allEntries = true)
    public void clearCacheOfUserOrders(){}
}
