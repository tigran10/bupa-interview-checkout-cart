
package com.bupa.simple;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Cart {
    // sku -> [unitPrice, qty]
    private final Map<String, Line> lines = new LinkedHashMap<>();

    public void add(String sku, Money unitPrice, Integer quantity) {
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("sku required");
        if (unitPrice == null) throw new IllegalArgumentException("unitPrice required");

        // TODO: null quantity -> 1; reject <= 0
        int qty = 0; // TODO
        // merge lines by SKU
        Line existing = lines.get(sku);
        if (existing == null) {
            lines.put(sku, new Line(unitPrice, qty));
        } else {
            // Optional gotcha: unit price mismatch decision
            if (!existing.unitPrice.equals(unitPrice)) {
                throw new IllegalArgumentException("unit price mismatch for SKU " + sku);
            }
            lines.put(sku, new Line(unitPrice, existing.quantity + qty));
        }
    }

    public Map<String, Line> items() {
        return Collections.unmodifiableMap(lines);
    }

    public Money subtotal() {
        // TODO: sum unitPrice * qty
        throw new UnsupportedOperationException("TODO subtotal");
    }

    public record Line(Money unitPrice, int quantity) {
        public Line {
            if (unitPrice == null) throw new IllegalArgumentException("unitPrice required");
            if (quantity <= 0) throw new IllegalArgumentException("quantity must be positive");
        }
    }
}
