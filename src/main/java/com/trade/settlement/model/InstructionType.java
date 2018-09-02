package com.trade.settlement.model;

public enum InstructionType {

    B("Buy", "Outgoing"),
    S("Sell", "Incoming");

    private final String name;
    private final String label;

    InstructionType(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }
}
