package com.example.medicalservice.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final int JWT_CACHE_EXPIRE_TIME_IN_MINUTES = 30;

    @Bean(name = "jwtServiceRequestCacheManager")
    public CacheManager jwtServiceRequestCacheManager() {
        var cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(JWT_CACHE_EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES)
        );
        return cacheManager;
    }
}
