package com.getir.intw.books.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatisticsService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<MonthlyOrderStat> getMonthlyOrderStats(String email) {
        MatchOperation matchToCustomer = Aggregation.match(Criteria.where("email").is(email));
        ProjectionOperation projectSums = Aggregation.project()
                .and(DateOperators.Month.month("$date")).as("order_month")
                .and(ArrayOperators.Size.lengthOfArray("$orderedBooks")).as("book_count")
                .and(AccumulatorOperators.Sum.sumOf("$orderedBooks.price")).as("order_price");
        GroupOperation groupByMonth = Aggregation.group("order_month")
                .first("order_month").as("month")
                .count().as("totalOrderCount")
                .sum("book_count").as("totalBookCount")
                .sum("order_price").as("totalPurchasedAmount");

        Aggregation aggregation = Aggregation.newAggregation(matchToCustomer, projectSums, groupByMonth);
        AggregationResults<MonthlyOrderStat> result = mongoTemplate.aggregate(aggregation, "order", MonthlyOrderStat.class);

        return result.getMappedResults();
    }

}
