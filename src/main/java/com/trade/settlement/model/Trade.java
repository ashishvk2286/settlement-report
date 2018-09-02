package com.trade.settlement.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trade {

    private String entity;
    private InstructionType type;
    private BigDecimal agreedFx;
    private Currency currency;
    private LocalDate instructionDate;
    private long units;
    private BigDecimal pricePerUnit;

    // Enriched values
    private LocalDate settlementDate;
    private BigDecimal amountInUSD;

    public Trade(String entity, InstructionType type, BigDecimal agreedFx, Currency currency, LocalDate instructionDate, long units, BigDecimal pricePerUnit) {
        this.entity = entity;
        this.type = type;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public String getEntity() {
        return entity;
    }

    public InstructionType getType() {
        return type;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public long getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getAmountInUSD() {
        return amountInUSD;
    }

    public void setAmountInUSD(BigDecimal amountInUSD) {
        this.amountInUSD = amountInUSD;
    }
}
