package com.trade.settlement.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.trade.settlement.exception.InvalidInputException;
import com.trade.settlement.model.Currency;

public final class Utility {

    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();

    private Utility() {
        // private to avoid instantiation
    }

    /**
     * Calculates amount in USD
     *
     * @param pricePerUnit
     * @param units
     * @param foreignExchange
     * @return amount in USD
     * @throws InvalidInputException
     *             on invalid input
     */
    public static BigDecimal toUSD(BigDecimal pricePerUnit, long units, BigDecimal foreignExchange) {

        if (pricePerUnit.compareTo(BigDecimal.ZERO) <= 0 || units <= 0 || foreignExchange.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Invalid input passed.");
        }

        return pricePerUnit.multiply(foreignExchange).multiply(new BigDecimal(units));
    }

    /**
     * Determines settlement date based on currency and instruction date
     *
     * @param currency
     * @param instructionDate
     * @return settlementDate
     * @throws InvalidInputException
     *             on invalid input
     */
    public static LocalDate getSettlementDate(Currency currency, LocalDate instructionDate) {

        if (currency == null || instructionDate == null) {
            throw new InvalidInputException("Invalid input passed.");
        }

        // Calculating settlement date
        LocalDate settlementDate = LocalDate.ofEpochDay(instructionDate.toEpochDay());
        do {
            settlementDate = settlementDate.plusDays(1l);
        } while (currency.isWeekend(settlementDate.getDayOfWeek()));

        return settlementDate;
    }

    public static LocalDate toLocalDate(String dateStr) {

        return LocalDate.parse(dateStr, DATE_FORMAT);
    }
}
