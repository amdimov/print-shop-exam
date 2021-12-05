package com.example.transferhall.cashing;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EvictCashValues {

    @CacheEvict(value = "images")
    public void clearCacheOfImages(){}
}
