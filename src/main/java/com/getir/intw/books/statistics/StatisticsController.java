package com.getir.intw.books.statistics;


import com.getir.intw.books.exceptions.ExceptionMessages;
import com.getir.intw.books.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/customer-monthly")
    public Response getMonthlyOrderStats(@RequestParam(value = "email") String email) {
        if (email.isBlank()) {
            return Response.fail(ExceptionMessages.EMPTY_EMAIL);
        }

        List<MonthlyOrderStat> allCustomerOrders = statisticsService.getMonthlyOrderStats(email);
        return Response.success(allCustomerOrders);
    }
}
