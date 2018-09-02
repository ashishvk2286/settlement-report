package com.trade.settlement.model;

import java.time.DayOfWeek;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyTest {

    @Test
    public void testWeekends() {

        Assert.assertTrue(Currency.AED.getWeekends().contains(DayOfWeek.FRIDAY));
        Assert.assertTrue(Currency.AED.getWeekends().contains(DayOfWeek.SATURDAY));
        Assert.assertTrue(Currency.AED.isWeekend(DayOfWeek.FRIDAY));
        Assert.assertFalse(Currency.AED.isWeekend(DayOfWeek.SUNDAY));
        Assert.assertEquals(2, Currency.AED.getWeekends().size());
    }
}
