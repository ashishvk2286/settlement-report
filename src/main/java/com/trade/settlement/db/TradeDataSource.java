package com.trade.settlement.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.trade.settlement.model.Currency;
import com.trade.settlement.model.InstructionType;
import com.trade.settlement.model.Trade;
import com.trade.settlement.utils.Utility;

public class TradeDataSource {

    // insert,update,delete functionality can be defined here to manipulate external data source

    public List<Trade> queryAll() {

        // Here data can be retrieved from external data source or file
        List<Trade> trades = new ArrayList<>();

        // Populate trade data
        // 12th Aug falls on Sunday
        trades.add(new Trade("A Ltd", InstructionType.B, new BigDecimal(0.5), Currency.AED, Utility.toLocalDate("2018-08-12"), 200, new BigDecimal(100.25)));
        trades.add(new Trade("A Ltd", InstructionType.S, new BigDecimal(0.6), Currency.AED, Utility.toLocalDate("2018-08-12"), 200, new BigDecimal(100.60)));
        trades.add(new Trade("A Ltd", InstructionType.B, new BigDecimal(0.7), Currency.AED, Utility.toLocalDate("2018-08-13"), 150, new BigDecimal(100.30)));
        trades.add(new Trade("A Ltd", InstructionType.S, new BigDecimal(0.8), Currency.AED, Utility.toLocalDate("2018-08-13"), 150, new BigDecimal(100.35)));
        trades.add(new Trade("A Ltd", InstructionType.B, new BigDecimal(0.5), Currency.AED, Utility.toLocalDate("2018-08-14"), 150, new BigDecimal(100.40)));
        trades.add(new Trade("A Ltd", InstructionType.B, new BigDecimal(0.6), Currency.AED, Utility.toLocalDate("2018-08-14"), 150, new BigDecimal(100.50)));

        trades.add(new Trade("B Ltd", InstructionType.B, new BigDecimal(0.5), Currency.SDG, Utility.toLocalDate("2018-08-12"), 170, new BigDecimal(102.25)));
        trades.add(new Trade("B Ltd", InstructionType.S, new BigDecimal(0.6), Currency.SDG, Utility.toLocalDate("2018-08-12"), 170, new BigDecimal(102.20)));
        trades.add(new Trade("B Ltd", InstructionType.B, new BigDecimal(0.7), Currency.SDG, Utility.toLocalDate("2018-08-13"), 150, new BigDecimal(102.30)));
        trades.add(new Trade("B Ltd", InstructionType.S, new BigDecimal(0.8), Currency.SDG, Utility.toLocalDate("2018-08-13"), 150, new BigDecimal(102.35)));
        trades.add(new Trade("B Ltd", InstructionType.B, new BigDecimal(0.5), Currency.SDG, Utility.toLocalDate("2018-08-14"), 150, new BigDecimal(102.40)));
        trades.add(new Trade("B Ltd", InstructionType.B, new BigDecimal(0.6), Currency.SDG, Utility.toLocalDate("2018-08-14"), 150, new BigDecimal(102.50)));

        trades.add(new Trade("C Ltd", InstructionType.B, new BigDecimal(0.5), Currency.SAR, Utility.toLocalDate("2018-08-12"), 170, new BigDecimal(101.25)));
        trades.add(new Trade("C Ltd", InstructionType.S, new BigDecimal(0.6), Currency.SAR, Utility.toLocalDate("2018-08-12"), 170, new BigDecimal(101.20)));
        trades.add(new Trade("C Ltd", InstructionType.B, new BigDecimal(0.7), Currency.SAR, Utility.toLocalDate("2018-08-13"), 150, new BigDecimal(101.30)));
        trades.add(new Trade("C Ltd", InstructionType.S, new BigDecimal(0.8), Currency.SAR, Utility.toLocalDate("2018-08-13"), 150, new BigDecimal(101.35)));
        trades.add(new Trade("C Ltd", InstructionType.B, new BigDecimal(0.5), Currency.SAR, Utility.toLocalDate("2018-08-14"), 150, new BigDecimal(101.40)));
        trades.add(new Trade("C Ltd", InstructionType.B, new BigDecimal(0.6), Currency.SAR, Utility.toLocalDate("2018-08-14"), 150, new BigDecimal(101.50)));

        // Enriching trade data
        enrichData(trades);

        return trades;
    }

    private void enrichData(List<Trade> trades) {

        trades.forEach(trade -> {
            // Determine Trade settlement date
            trade.setSettlementDate(Utility.getSettlementDate(trade.getCurrency(), trade.getInstructionDate()));
            // Calculate trade amount in USD
            trade.setAmountInUSD(Utility.toUSD(trade.getPricePerUnit(), trade.getUnits(), trade.getAgreedFx()));
        });
    }
}
