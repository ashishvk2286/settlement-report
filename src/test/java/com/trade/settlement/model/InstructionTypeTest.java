package com.trade.settlement.model;

import org.junit.Assert;
import org.junit.Test;

public class InstructionTypeTest {

    @Test
    public void testEnumValues() {

        Assert.assertEquals("Buy", InstructionType.B.getName());
        Assert.assertEquals("Outgoing", InstructionType.B.getLabel());

        Assert.assertEquals("Sell", InstructionType.S.getName());
        Assert.assertEquals("Incoming", InstructionType.S.getLabel());
    }

}
