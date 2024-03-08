package org.kinocat.db_stats.repository;

import org.kinocat.db_stats.entity.ReportSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportSpecificationRepo extends MongoRepository<ReportSpecification, String> {
}
