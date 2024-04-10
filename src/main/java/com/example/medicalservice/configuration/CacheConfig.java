package com.example.medicalservice.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    @Value("${jwt.cacheExpireTime}")
    private Integer cacheExpireTime;

    @Bean(name = "jwtServiceRequestCacheManager")
    public CacheManager jwtServiceRequestCacheManager() {
        var cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(cacheExpireTime, TimeUnit.MINUTES)
        );
        return cacheManager;
    }
}
