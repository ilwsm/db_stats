package org.kinocat.db_stats.service;

import lombok.AllArgsConstructor;
import org.kinocat.db_stats.entity.SalesAndTrafficByDate;
import org.kinocat.db_stats.repository.SalesAndTrafficByDateRepo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = "byDateData")
public class SalesAndTrafficByDateService {

    final SalesAndTrafficByDateRepo repository;

    @Cacheable
    public List<SalesAndTrafficByDate> readAll() {
        return repository.findAll();
    }

    @Cacheable
    public List<SalesAndTrafficByDate> readByDateBetween(String date1, String date2) {
        return repository.findByDateBetween(date1, date2);
    }

    @Cacheable
    public List<SalesAndTrafficByDate> readByDate(String date) {
        return repository.findByDate(date);
    }
}
