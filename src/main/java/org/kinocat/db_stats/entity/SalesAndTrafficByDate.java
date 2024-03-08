package org.kinocat.db_stats.entity;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Setter
@Getter
@Document
public class SalesAndTrafficByDate {
    @Id
    private String id;

    private String date;

    private SalesByDate salesByDate;

    private TrafficByDate trafficByDate;

    @Setter
    @Getter
    public static class SalesByDate {

        private AmountAndCurrencyCode orderedProductSales;

        private AmountAndCurrencyCode orderedProductSalesB2B;

        private Long unitsOrdered;

        private Long unitsOrderedB2B;

        private Long totalOrderItems;

        private Long totalOrderItemsB2B;

        private AmountAndCurrencyCode averageSalesPerOrderItem;

        private AmountAndCurrencyCode averageSalesPerOrderItemB2B;

        private Double averageUnitsPerOrderItem;

        private Double averageUnitsPerOrderItemB2B;

        private AmountAndCurrencyCode averageSellingPrice;

        private AmountAndCurrencyCode averageSellingPriceB2B;

        private Long unitsRefunded;

        private Double refundRate;

        private Long claimsGranted;

        private AmountAndCurrencyCode claimsAmount;

        private AmountAndCurrencyCode shippedProductSales;

        private Long unitsShipped;

        private Long ordersShipped;


    }

    @Setter
    @Getter
    public static class TrafficByDate {

        private Long browserPageViews;

        private Long browserPageViewsB2B;

        private Long mobileAppPageViews;

        private Long mobileAppPageViewsB2B;

        private Long pageViews;

        private Long pageViewsB2B;

        private Long browserSessions;

        private Long browserSessionsB2B;

        private Long mobileAppSessions;

        private Long mobileAppSessionsB2B;

        private Long sessions;

        private Long sessionsB2B;

        private Double buyBoxPercentage;

        private Double buyBoxPercentageB2B;

        private Double orderItemSessionPercentage;

        private Double orderItemSessionPercentageB2B;

        private Double unitSessionPercentage;

        private Long unitSessionPercentageB2B;

        private Long averageOfferCount;

        private Long averageParentItems;

        private Long feedbackReceived;

        private Long negativeFeedbackReceived;

        private Long receivedNegativeFeedbackRate;
    }
}