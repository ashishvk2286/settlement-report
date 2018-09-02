package com.trade.settlement.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.trade.settlement.model.InstructionType;
import com.trade.settlement.model.Trade;

public class TradeReportServiceTest {

    private TradeReportService tradeReport;

    @Before
    public void setUp() {

        tradeReport = new TradeReportService();
    }

    @After
    public void tearDown() {

        tradeReport = null;
    }

    /**
     * Amount in USD settled outgoing everyday
     */
    @Test
    public void testGetTotalSettlementAmountsByDayForOutgoingInstructions() {

        // Get total settlements for Outgoing instructions in USD
        Map<LocalDate, Double> tradesPerDay = tradeReport.getTotalSettlementAmountsByDay(InstructionType.B);

        Assert.assertNotNull(tradesPerDay);
        Assert.assertTrue(tradesPerDay.size() > 0);

        Map<LocalDate, Double> tradesPerDaySorted = new TreeMap<>(tradesPerDay);

        System.out.println("===============================================================");
        System.out.println("Total settlements for Outgoing instructions in USD");
        System.out.println("Date \t\t Amount in USD");
        tradesPerDaySorted.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " \t " + new BigDecimal(entry.getValue()).setScale(2, RoundingMode.HALF_EVEN));
        });
        System.out.println("===============================================================");
    }

    /**
     * Amount in USD settled incoming everyday
     */
    @Test
    public void testGetTotalSettlementAmountsByDayForIncomingInstructions() {

        // Get total settlements for Incoming instructions in USD
        Map<LocalDate, Double> tradesPerDay = tradeReport.getTotalSettlementAmountsByDay(InstructionType.S);

        Assert.assertNotNull(tradesPerDay);
        Assert.assertTrue(tradesPerDay.size() > 0);

        Map<LocalDate, Double> tradesPerDaySorted = new TreeMap<>(tradesPerDay);

        System.out.println("===============================================================");
        System.out.println("Total settlements for Incoming instructions in USD");
        System.out.println("Date \t\t Amount in USD");
        tradesPerDaySorted.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " \t " + new BigDecimal(entry.getValue()).setScale(2, RoundingMode.HALF_EVEN));
        });
        System.out.println("===============================================================");
    }

    /**
     * Ranking of entities based on incoming amount
     */
    @Test
    public void testGetRankingsByAmountsForIncomingInstructions() {

        // Get total settlements for Incoming instructions in USD
        List<Trade> trades = tradeReport.getRankingsByAmounts(InstructionType.S);

        Assert.assertNotNull(trades);
        Assert.assertTrue(trades.size() > 0);

        final AtomicInteger rank = new AtomicInteger(1);

        System.out.println("===============================================================");
        System.out.println("Rankings by settlement amounts for Incoming instructions in USD");
        System.out.println("Rank \t Entity \t Amount in USD \t As on");
        trades.forEach(trade -> {
            System.out.println(rank.getAndIncrement() + " \t " + trade.getEntity() + " \t\t " + trade.getAmountInUSD().setScale(2, RoundingMode.HALF_EVEN) + " \t " + trade.getSettlementDate());
        });
        System.out.println("===============================================================");
    }

    /**
     * Ranking of entities based on outgoing amount
     */
    @Test
    public void testGetRankingsByAmountsForOutgoingInstructions() {

        // Get total settlements for Outgoing instructions in USD
        List<Trade> trades = tradeReport.getRankingsByAmounts(InstructionType.B);

        Assert.assertNotNull(trades);
        Assert.assertTrue(trades.size() > 0);

        final AtomicInteger rank = new AtomicInteger(1);

        System.out.println("===============================================================");
        System.out.println("Rankings by settlement amounts for Outgoing instructions in USD");
        System.out.println("Rank \t Entity \t Amount in USD \t As on");
        trades.forEach(trade -> {
            System.out.println(rank.getAndIncrement() + " \t " + trade.getEntity() + " \t\t " + trade.getAmountInUSD().setScale(2, RoundingMode.HALF_EVEN) + " \t " + trade.getSettlementDate());
        });
        System.out.println("===============================================================");
    }

}
