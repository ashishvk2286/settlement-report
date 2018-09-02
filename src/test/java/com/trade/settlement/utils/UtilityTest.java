package com.trade.settlement.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.trade.settlement.exception.InvalidInputException;
import com.trade.settlement.model.Currency;

public class UtilityTest {

    @Test(expected = InvalidInputException.class)
    public void testCurrencyConversionInvalidPrice() throws InvalidInputException {

        Utility.toUSD(BigDecimal.ZERO, 1l, BigDecimal.ONE);
    }

    @Test(expected = InvalidInputException.class)
    public void testCurrencyConversionInvalidUnits() throws InvalidInputException {

        Utility.toUSD(BigDecimal.ONE, 0, BigDecimal.ONE);
    }

    @Test(expected = InvalidInputException.class)
    public void testCurrencyConversionInvalidFx() throws InvalidInputException {

        Utility.toUSD(BigDecimal.ONE, 1l, BigDecimal.ZERO);
    }

    @Test
    public void testCurrencyConversion() throws InvalidInputException {

        Assert.assertEquals(0, new BigDecimal(10025.0).compareTo(Utility.toUSD(new BigDecimal(100.25), 200l, new BigDecimal(0.5))));
    }

    @Test(expected = InvalidInputException.class)
    public void testGetSettlementDateNullCurrency() throws InvalidInputException {

        Utility.getSettlementDate(null, LocalDate.now());
    }

    @Test(expected = InvalidInputException.class)
    public void testGetSettlementDateNullInstructionDate() throws InvalidInputException {

        Utility.getSettlementDate(Currency.AED, null);
    }

    @Test
    public void testGetSettlementDateCurrentDateIsOnWorkingDay() throws InvalidInputException {

        // 31st Aug 18 falls on Friday (last working day for SDG) -> next working date
        // -> 3rd Sept 18
        Assert.assertTrue(LocalDate.of(2018, 9, 3).isEqual(Utility.getSettlementDate(Currency.SDG, LocalDate.of(2018, 8, 31))));

        // 30st Aug 18 falls on Thursday (last working day for AED) -> next working date
        // -> 2nd Sept 18
        Assert.assertTrue(LocalDate.of(2018, 9, 2).isEqual(Utility.getSettlementDate(Currency.AED, LocalDate.of(2018, 8, 30))));
    }
}
