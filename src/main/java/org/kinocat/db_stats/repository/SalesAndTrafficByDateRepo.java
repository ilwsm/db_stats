package org.kinocat.db_stats.repository;

import org.kinocat.db_stats.entity.SalesAndTrafficByDate;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SalesAndTrafficByDateRepo extends MongoRepository<SalesAndTrafficByDate, String> {
    List<SalesAndTrafficByDate> findByDate(String date);

    @SuppressWarnings("SpringDataMongoDBJsonFieldInspection")
    @Query(value = "{'date':{ $gte: ?0, $lte: ?1}}")
    List<SalesAndTrafficByDate> findByDateBetween(String date1, String date2);
}
