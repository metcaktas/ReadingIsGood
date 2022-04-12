package com.getir.intw.books.statistics;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;


@Getter @Setter
@NoArgsConstructor
public class MonthlyOrderStat {
    private int month;
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchasedAmount;

}
