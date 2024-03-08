package org.kinocat.db_stats.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Document
public class SalesAndTrafficByAsin {

    private String parentAsin;

    private SalesByAsin salesByAsin;

    private TrafficByAsin trafficByAsin;

    @Setter
    @Getter
    public static class SalesByAsin {

        private Long unitsOrdered;

        private Long unitsOrderedB2B;

        private AmountAndCurrencyCode orderedProductSales;

        private AmountAndCurrencyCode orderedProductSalesB2B;

        private Long totalOrderItems;

        private Long totalOrderItemsB2B;
    }

    @Setter
    @Getter
    public static class TrafficByAsin {

        private Long browserSessions;

        private Long browserSessionsB2B;

        private Long mobileAppSessions;

        private Long mobileAppSessionsB2B;

        private Long sessions;

        private Long sessionsB2B;

        private Double browserSessionPercentage;

        private Long browserSessionPercentageB2B;

        private Double mobileAppSessionPercentage;

        private Long mobileAppSessionPercentageB2B;

        private Double sessionPercentage;

        private Long sessionPercentageB2B;

        private Long browserPageViews;

        private Long browserPageViewsB2B;

        private Long mobileAppPageViews;

        private Long mobileAppPageViewsB2B;

        private Long pageViews;

        private Long pageViewsB2B;

        private Double browserPageViewsPercentage;

        private Long browserPageViewsPercentageB2B;

        private Double mobileAppPageViewsPercentage;

        private Long mobileAppPageViewsPercentageB2B;

        private Double pageViewsPercentage;

        private Long pageViewsPercentageB2B;

        private Long buyBoxPercentage;

        private Long buyBoxPercentageB2B;

        private Double unitSessionPercentage;

        private Long unitSessionPercentageB2B;
    }
}