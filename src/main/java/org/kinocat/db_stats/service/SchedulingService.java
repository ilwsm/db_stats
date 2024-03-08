package org.kinocat.db_stats.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kinocat.db_stats.util.ImportUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

@AllArgsConstructor
@Service
public class SchedulingService {

    private final Logger log = LogManager.getLogger(this.getClass());
    private final CacheManager cacheManager;
    private final ImportJsonService importService;
    private byte[] md5Digest;

    @Scheduled(fixedRate = 30000) // 3 minutes = 180000
    @SneakyThrows
    public void updateJob() {
        ImportUtils.TextAndHash data = ImportUtils.textFromResourceWithMD5("static/test_report.json");

        if (!Arrays.equals(md5Digest, data.md5Digest)) {
            md5Digest = data.md5Digest;
            int inserts = importService.importTo(data.text);
            evictAllCaches();
            log.info("Inserted " + inserts + " rows");
        } else {
            log.info("File the same, not need to update");
        }
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }

    @SuppressWarnings("unchecked")
    public String debugCaching() {
        StringBuilder sb = new StringBuilder("Cache:\n");
        for (String name : cacheManager.getCacheNames()) {
            sb.append("Name: ").append(name).append(":").append("\n");
            Cache cache = cacheManager.getCache(name);

            Object nativeCache = null;
            if (cache != null) {
                nativeCache = cache.getNativeCache();
            }

            if (nativeCache instanceof ConcurrentMap<?, ?>) {
                ConcurrentMap<Object, Object> nc = (ConcurrentMap<Object, Object>) nativeCache;
                nc.forEach((key, value) -> {
                    sb.append("\tkey: ").append(key).append(", data: <data>").append("\n");
                });
            }
        }
        return sb.toString();
    }
}
