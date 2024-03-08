package org.kinocat.db_stats.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Document
public class ReportSpecification {
    @Id
    private String id;

    private String reportType;

    private ReportOptions reportOptions;

    private LocalDate dataStartTime;

    private LocalDate dataEndTime;

    private List<String> marketplaceIds;

    @Setter
    @Getter
    public static class ReportOptions {
        private String dateGranularity;
        private String asinGranularity;
    }
}