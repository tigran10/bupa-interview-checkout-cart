
package com.bupa.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Checkout {

    @FunctionalInterface
    public interface DiscountRule {
        Money discount(Cart cart);
    }

    private final List<DiscountRule> rules = new ArrayList<>();

    public Checkout(List<DiscountRule> rules) {
        this.rules.addAll(Objects.requireNonNull(rules));
    }

    public Money total(Cart cart) {
        throw new UnsupportedOperationException("TODO total");
    }

    public static DiscountRule multiBuy(String sku, int n, int m) {
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("sku required");
        if (n <= 1 || m <= 0 || m >= n) throw new IllegalArgumentException("Require n>1 and 0<m<n");
        return cart -> {
            throw new UnsupportedOperationException("TODO multiBuy");
        };
    }

    public static DiscountRule thresholdPercent(Money threshold, int percent) {
        if (threshold == null) throw new IllegalArgumentException("threshold required");
        if (percent <= 0 || percent >= 100) throw new IllegalArgumentException("percent 1..99");
        return cart -> {
            throw new UnsupportedOperationException("TODO thresholdPercent");
        };
    }
}
