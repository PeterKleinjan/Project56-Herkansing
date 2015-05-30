package com.BS4Project.Model;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by Peter on 14-1-2015.
 */
public class Euroformatter implements Comparable<Euroformatter>{

    final static NumberFormat formatter = NumberFormat.getNumberInstance();
    private final BigDecimal value;

    public Euroformatter(final double value) {
        String str = String.valueOf(value);
        this.value = new BigDecimal(str);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
    }

    public int compareTo(final Euroformatter o) {
        return value.compareTo(o.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        return value.equals(o);
    }

    @Override
    public String toString() {
        if (formatter == null) {
            return value.toString();
        }
        return "€ " + formatter.format(value);
    }
}
