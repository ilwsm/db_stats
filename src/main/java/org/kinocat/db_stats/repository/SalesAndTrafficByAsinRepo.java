package org.kinocat.db_stats.repository;

import org.kinocat.db_stats.entity.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesAndTrafficByAsinRepo extends MongoRepository<SalesAndTrafficByAsin, String> {

    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> asins);
}
