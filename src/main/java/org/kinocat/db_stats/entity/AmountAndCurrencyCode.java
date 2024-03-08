package org.kinocat.db_stats.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AmountAndCurrencyCode {

    private Double amount;

    private String currencyCode;
}