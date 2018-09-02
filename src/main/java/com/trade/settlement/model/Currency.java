package com.trade.settlement.model;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Currency {

    SDG(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
    USD(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
    AED(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
    SAR(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final List<DayOfWeek> weekends;

    Currency(DayOfWeek... weekends) {

        this.weekends = Collections.unmodifiableList(Arrays.asList(weekends));
    }

    public List<DayOfWeek> getWeekends() {

        return weekends;
    }

    public boolean isWeekend(DayOfWeek dayOfWeek) {

        return weekends.contains(dayOfWeek);
    }
}
