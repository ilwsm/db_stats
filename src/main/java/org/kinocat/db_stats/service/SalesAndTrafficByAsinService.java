package org.kinocat.db_stats.service;

import lombok.AllArgsConstructor;
import org.kinocat.db_stats.entity.SalesAndTrafficByAsin;
import org.kinocat.db_stats.repository.SalesAndTrafficByAsinRepo;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = "byAsinData")
public class SalesAndTrafficByAsinService {

    final SalesAndTrafficByAsinRepo repository;

    @Cacheable
    public List<SalesAndTrafficByAsin> readAll() {
        return repository.findAll();
    }

    @Cacheable
    public List<SalesAndTrafficByAsin> readByAsins(String asinsStr) {
        List<String> asins = Arrays.asList(asinsStr.split("\\s*,\\s*"));
        return repository.findByParentAsinIn(asins);
    }

}
